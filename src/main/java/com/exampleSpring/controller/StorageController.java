package com.exampleSpring.controller;

import com.exampleSpring.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("images")MultipartFile file) throws IOException {

        String uploadImage = storageService.uploadImage(file);
        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadImage(@PathVariable String filename){
        byte[] imageData= storageService.downloadImage(filename);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }


}
