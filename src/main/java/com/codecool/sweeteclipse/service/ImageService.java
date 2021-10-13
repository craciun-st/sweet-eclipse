package com.codecool.sweeteclipse.service;

import com.codecool.sweeteclipse.model.ImageData;
import com.codecool.sweeteclipse.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
    public String getUriAfterSavingFile(MultipartFile fileFromMultipartForm, String forProjectName) throws IOException {
        if (fileFromMultipartForm.isEmpty()) {
            throw new IllegalArgumentException("Failed to upload empty file");
        }

        List<String> imageTypes = List.of(
                "image/bmp",
                "image/jpeg",
                "image/png",
                "image/svg+xml",
                "image/tiff",
                "image/webp"
        );

        if (!imageTypes.contains( fileFromMultipartForm.getContentType() )) {
            throw new IllegalArgumentException("Will not upload a file without a proper image MIME-type");
        }

        Map<String, String> metadata = addCustomImageTags(forProjectName);
        metadata.put("Content-Type", fileFromMultipartForm.getContentType());
        long fileLength = fileFromMultipartForm.getSize();
        String projectFolder = String.format("project_%s", forProjectName.hashCode());
        String fileName = UUID.randomUUID().toString();
        InputStream uploadStream;
        try {
            uploadStream = fileFromMultipartForm.getInputStream();
        } catch (IOException e) {
            String newMessage = String.format(
                    "Internal error: Temp storage of multipart file of size %.3f MB failed",
                    fileLength / (1024.0*1024.0)
            );
            throw new IOException(newMessage);
        }


        awsFileTransfer.upload(uploadStream, projectFolder, fileName, metadata, fileLength);
        return awsFileTransfer.generateUri(projectFolder, fileName);
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
