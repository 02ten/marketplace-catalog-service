package com.marketplace.catalog.controller;

import com.marketplace.catalog.model.Product;
import com.marketplace.catalog.service.ProductService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductService productService;
    private final Tracer tracer;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        Span span = tracer.buildSpan("Getting all products").start();
        Tags.HTTP_METHOD.set(span, "GET");
        Tags.HTTP_URL.set(span, "/api/product");
        span.finish();
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Span span = tracer.buildSpan("Getting product by id").start();
        Tags.HTTP_METHOD.set(span, "GET");
        Tags.HTTP_URL.set(span, "/api/product/" + id);
        try {
            Product product = productService.getProductById(id);
            span.finish();
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException exception) {
            span.finish();
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable Long id) {
        Span span = tracer.buildSpan("Getting all products").start();
        Tags.HTTP_METHOD.set(span, "GET");
        Tags.HTTP_URL.set(span, "/api/product/category/"+id);
        try {
            List<Product> products = productService.getProductsByCategory(id);
            span.finish();
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException exception) {
            span.finish();
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
