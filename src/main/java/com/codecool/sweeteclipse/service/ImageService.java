package com.codecool.sweeteclipse.service;

import com.codecool.sweeteclipse.model.ImageData;
import com.codecool.sweeteclipse.repository.ImageDataRepository;
import com.codecool.sweeteclipse.service.exceptions.ImproperFileException;
import com.codecool.sweeteclipse.service.exceptions.TempStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.codecool.sweeteclipse.util.StringUtils.generateRandomAlphaNumericString;


@Service
public class ImageService implements ImageServiceFacade {

    private AmazonS3FileTransferService awsFileTransfer;

    private ImageDataRepository imageDataRepo;


    @Autowired
    public ImageService(AmazonS3FileTransferService awsFileTransfer, ImageDataRepository imageDataRepo) {
        this.awsFileTransfer = awsFileTransfer;
        this.imageDataRepo = imageDataRepo;
    }

    @Override
    public String getUriAfterSavingProperFile(MultipartFile properImageFile, String forProjectName) throws IOException {

        Map<String, String> metadata = addCustomImageTags(forProjectName);
        MimeType fileMimeType = updateMetadataWithContentTypeAndReturnIt(metadata, properImageFile);
        String fileName = UUID.randomUUID().toString() + "." + fileMimeType.getSubtype();

        String codedProjectName = generateRandomAlphaNumericString((byte) 16, forProjectName.hashCode());
        String projectFolder = String.format("project_%s", codedProjectName);


        long fileLength = properImageFile.getSize();

        InputStream uploadStream;
        try {
            uploadStream = properImageFile.getInputStream();
        } catch (IOException e) {
            String newMessage = String.format(
                    "Internal error: Could not access temp storage for file of size %.3f MB",
                    fileLength / (1024.0*1024.0)
            );
            throw new TempStorageException(newMessage);
        }


        awsFileTransfer.upload(uploadStream, projectFolder, fileName, metadata, fileLength);
        return awsFileTransfer.generateUri(projectFolder, fileName);
    }

    @Override
    public String getUriStringAfterSavingOrElseThrow(MultipartFile multipartFile, String forProject)
            throws ImproperFileException, IOException {
        throwIfFileIsEmpty(multipartFile);
        throwIfNotAnImageType(multipartFile);
        String uriString = getUriAfterSavingProperFile(multipartFile, forProject);

        return uriString;
    }

    private MimeType updateMetadataWithContentTypeAndReturnIt(
            Map<String, String> metadata,
            MultipartFile multipartFile
    ) {
        String fileMimeTypeString = multipartFile.getContentType();
        MimeType fileMimeType = MimeType.valueOf(
                fileMimeTypeString != null ? fileMimeTypeString : "application/octet-stream"
        );
        metadata.put("Content-Type", fileMimeTypeString);
        return fileMimeType;
    }


    @Override
    public byte[] getRawImageById(Long id) throws IOException {
        ImageData maybeImage = imageDataRepo.findById(id).orElseThrow();
        String imageUri = maybeImage.getUri();
        String imageFileKey = awsFileTransfer.getFileKeyFromUrl(imageUri);
        return awsFileTransfer.download(imageFileKey);
    }

    public Map<String, String> addCustomImageTags(String forProjectName) {
        return new HashMap<>();
    }
}
