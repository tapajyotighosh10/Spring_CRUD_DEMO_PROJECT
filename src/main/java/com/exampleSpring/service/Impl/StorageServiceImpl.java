package com.exampleSpring.service.Impl;

import com.exampleSpring.entity.ImageData;
import com.exampleSpring.repository.StorageRepository;
import com.exampleSpring.service.StorageService;
import com.exampleSpring.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;


    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder().name(file.getOriginalFilename()).type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully:" + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImage(String file) {

        Optional<ImageData> dbImage = storageRepository.findByName(file);

        byte[] images = ImageUtils.decompressImage(dbImage.get().getImageData());
        return images;
    }
}
