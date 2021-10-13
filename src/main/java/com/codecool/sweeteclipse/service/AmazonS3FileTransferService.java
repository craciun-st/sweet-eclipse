package com.codecool.sweeteclipse.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import static com.codecool.sweeteclipse.util.StringUtils.getFileKey;

@Service
public class AmazonS3FileTransferService {

    public static final String DEFAULT_UPLOAD_FOLDER_NAME = "000_REDUNDANT";

    @Value("${amazon.s3.bucketName}")
    private String s3BucketName;

    private final AmazonS3 s3Interface;

    @Autowired
    public AmazonS3FileTransferService(AmazonS3 s3Interface) {
        this.s3Interface = s3Interface;
    }

    public void upload(
            InputStream fileStream,
            String toFolder,
            String toFilename,
            Map<String, String> maybeMetaData,
            Long maybeContentLength
    ) throws IOException {
        ObjectMetadata fileObjMetaData = new ObjectMetadata();

        // !!! Note that this must be accurate. Get it from the HTTP headers of the upload request!!
        if (maybeContentLength != null && maybeContentLength > 0) {
            fileObjMetaData.setContentLength(maybeContentLength);
        }
        ;

        if (maybeMetaData != null) {
        maybeMetaData.forEach(
                (key, value) -> fileObjMetaData.addUserMetadata(key, value)
        );
    }

        String fileKey = getFileKey(toFolder, toFilename, DEFAULT_UPLOAD_FOLDER_NAME);
        try {
            s3Interface.putObject(s3BucketName, fileKey, fileStream, fileObjMetaData);
        }
        // the SdkClientException is super for AmazonServiceException as well
        catch (SdkClientException exception) {
            throw new IOException(
                    String.format(
                            "Failed to upload file to: %s/%s with ContentLength of: %d",
                            s3BucketName, fileKey, fileObjMetaData.getContentLength()
                    )
            );
        }
    }

    public byte[] download(String fromFolder, @NotBlank String fromFilename) throws IOException {
        String fileKey = getFileKey(fromFolder, fromFilename, DEFAULT_UPLOAD_FOLDER_NAME);
        try {
            S3Object fileObj = s3Interface.getObject(s3BucketName, fileKey);


            try ( S3ObjectInputStream downloadStream = fileObj.getObjectContent() ) {

                byte[] fileInMemory = IOUtils.toByteArray(downloadStream);
                return fileInMemory;

            } catch (SdkClientException | IOException exception) {
                throw new IOException(
                        String.format(
                                "Failed to complete download of: %s/%s",
                                s3BucketName, fileKey
                        )
                );
            }


        } catch (SdkClientException exception) {
            throw new IOException(
                    String.format(
                            "Failed to get reference to file from: %s/%s",
                            s3BucketName, fileKey
                    )
            );
        }
    }






}
