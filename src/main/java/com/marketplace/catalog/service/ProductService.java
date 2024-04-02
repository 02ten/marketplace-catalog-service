package com.marketplace.catalog.service;

import com.marketplace.catalog.model.Product;
import com.marketplace.catalog.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public List<Product> getAllProducts(){
        log.info("Getting all products");
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        log.info("Getting product by id = " + id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            log.error("No product with such id");
            throw new IllegalArgumentException("Такого товара нет");
        }
        log.info("Successful getting products by id = " + id);
        return optionalProduct.get();
    }
    public List<Product> getProductsByCategory(Long id){
        log.info("Getting all products by category id = " + id);
        if(!categoryService.existCategoryById(id)){
            log.error("Такой категории нет");
            throw new IllegalArgumentException("Такой категории нет");
        }
        log.info("Successful getting products by category id");
        return productRepository.getProductsByCategory_Id(id);
    }


}
