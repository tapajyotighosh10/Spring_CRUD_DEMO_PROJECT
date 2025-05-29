package com.exampleSpring.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    public String uploadImage(MultipartFile file) throws IOException;

    public byte[] downloadImage(String file);
}
