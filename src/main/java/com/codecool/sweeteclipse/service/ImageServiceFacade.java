package com.codecool.sweeteclipse.service;

import com.codecool.sweeteclipse.model.ImageData;
import com.codecool.sweeteclipse.service.exceptions.ImproperFileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageServiceFacade {


    String getUriAfterSavingProperFile(MultipartFile properImageFile, String forProjectName) throws IOException;

    String getUriStringAfterSavingOrElseThrow(
            MultipartFile multipartFile,
            String forProject
    ) throws ImproperFileException, IOException;

    void persistImageData(List<ImageData> imageDataList);

    byte[] getRawImageById(Long id) throws IOException;





    default void throwIfFileIsEmpty(MultipartFile multipartFile) throws ImproperFileException {
        if (multipartFile.isEmpty()) {
            throw new ImproperFileException("Failed to upload empty file");
        }
    }

    default void throwIfNotAnImageType(MultipartFile multipartFile) throws ImproperFileException {

        if (!IMAGE_MIME_TYPES.contains( multipartFile.getContentType() )) {
            throw new ImproperFileException("Will not upload a file without a proper image MIME-type");
        }
    }

    List<String> IMAGE_MIME_TYPES = List.of(
            "image/bmp",
            "image/jpeg",
            "image/png",
            "image/svg+xml",
            "image/tiff",
            "image/webp"
    );

}
