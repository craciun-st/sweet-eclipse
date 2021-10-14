package com.codecool.sweeteclipse.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageServiceFacade {

    String getUriAfterSavingProperFile(MultipartFile properImageFile, String forProjectName) throws IOException;

    byte[] getRawImageById(Long id) throws IOException;
}
