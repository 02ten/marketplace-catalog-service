package com.marketplace.catalog;

import com.marketplace.catalog.model.Category;
import com.marketplace.catalog.model.Product;
import com.marketplace.catalog.repositories.ProductRepository;
import com.marketplace.catalog.service.CategoryService;
import com.marketplace.catalog.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.CollectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;

@ExtendWith(MockitoExtension.class)
class CatalogServiceApplicationTests {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private ProductService productService;


    @Test
    void getProduct_validUrl_ReturnsAllProducts() {
        Category category = new Category(1, "Семена", null);
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product(1L, "Помидоры", 299.99,  "Семейство помидоров", 1L,
                        category),
                new Product(2L, "Помидоры", 299.99,  "Семейство помидоров", 1L,
                        category)
        ));
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        Assertions.assertArrayEquals(productList.toArray(), productService.getAllProducts().toArray());
    }
    @Test
    void getProductById_validId_ReturnsAllProducts() throws Exception {
        Category category = new Category(1, "Семена", null);
        Product product = new Product(1L, "Помидоры", 299.99,  "Семейство помидоров", 1L,
                category);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Assertions.assertEquals(product, productService.getProductById(1L));
    }
    @Test
    void getProductById_invalidId_ReturnsException(){
        Category category = new Category(1, "Семена", null);
        Product product = new Product(1L, "Помидоры", 299.99,  "Семейство помидоров", 1L,
                category);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Assertions.assertThrows(Exception.class,()-> productService.getProductById(2L));
    }
    @Test
    void getProductsByCategoryId_ValidId_ReturnsProducts() throws Exception {
        Category category = new Category(1, "Семена", null);
        Product product = new Product(1L, "Помидоры", 299.99,  "Семейство помидоров", 1L,
                category);
        Product product1 = new Product(2L, "Помидоры", 299.99,  "Семейство помидоров", 1L,
                category);

        List<Product> productList = new ArrayList<>(Arrays.asList(product, product1));
        Mockito.when(categoryService.existCategoryById(1L)).thenReturn(true);
        Mockito.when(productRepository.getProductsByCategory_Id(1L)).
                thenReturn(productList);
        Assertions.assertIterableEquals(productList, productService.getProductsByCategory(1L));
    }
}
