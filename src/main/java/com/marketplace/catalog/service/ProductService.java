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
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Такого продукта нет"));
    }
    public List<Product> getProductsByCategory(Long id){
        if(!categoryService.existCategoryById(id)){
            throw new IllegalArgumentException("Такой категории нет");
        }
        return productRepository.getProductsByCategory_Id(id);
    }


}
