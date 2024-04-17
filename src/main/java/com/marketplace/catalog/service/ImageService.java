package com.marketplace.catalog.service;

import com.marketplace.catalog.model.Image;
import com.marketplace.catalog.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j
public class ImageService {
    private final ImageRepository imageRepository;

    public Image getImageById(Long id){
        log.info("Getting image by id "+ id);
        return imageRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No image with such id"));
    }
}
