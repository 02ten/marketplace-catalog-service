package com.marketplace.catalog.controller;

import com.marketplace.catalog.model.Category;
import com.marketplace.catalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id){
        try{
           Category category = categoryService.getCategoryById(id);
           return ResponseEntity.ok(category);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.ok(null);
        }
    }
}
