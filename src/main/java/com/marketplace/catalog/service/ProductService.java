package com.marketplace.catalog.service;

import com.marketplace.catalog.model.Product;
import com.marketplace.catalog.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("Not found"));
    }
}
