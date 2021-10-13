package com.codecool.sweeteclipse.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageServiceFacade {

    String getUriAfterSavingFile(MultipartFile fileFromMultipartForm, String forProjectName) throws IOException;

    byte[] getRawImageById(Long id) throws IOException;
}
