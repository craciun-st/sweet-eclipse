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



        if (maybeMetaData != null) {
        maybeMetaData.forEach(
                (key, value) -> {
                    if (key.equals("Content-Type")) { fileObjMetaData.setContentType(value); }
                    else {
                        fileObjMetaData.addUserMetadata(key, value);
                    }
                }
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
        return download(fileKey);
    }

    public byte[] download(String fileKey) throws IOException {
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


    public String generateUri(String forFolder, String forFilename) {
        String uri = String.format(
                "https://%s.s3.%s.amazonaws.com/%s",
                s3BucketName,
                s3Interface.getRegion().toString(),
                getFileKey(forFolder, forFilename, DEFAULT_UPLOAD_FOLDER_NAME)
        );
        return uri;
    }

    public String getFileKeyFromUrl(String amazonS3Url) {
        String parsedUri = amazonS3Url.replaceAll("^https:\\/\\/\\w+.s3.[a-z0-9-]+.amazonaws.com\\/", "");
        return parsedUri;
    }



}
