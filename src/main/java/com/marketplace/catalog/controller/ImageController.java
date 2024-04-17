package com.marketplace.catalog.controller;

import com.marketplace.catalog.model.Image;
import com.marketplace.catalog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id){
        try{
            Image image = imageService.getImageById(id);
            return new ResponseEntity<>(image, HttpStatus.OK);
        }catch (IllegalArgumentException exception){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
