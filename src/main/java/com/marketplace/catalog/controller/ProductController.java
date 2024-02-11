package com.marketplace.catalog.controller;

import com.marketplace.catalog.model.Product;
import com.marketplace.catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        try{
            return ResponseEntity.ok(productService.getProductById(id));
        }catch (Exception exception){
            return ResponseEntity.ok(null);
        }
    }
}
