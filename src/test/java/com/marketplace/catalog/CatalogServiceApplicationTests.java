package com.marketplace.catalog;

import com.marketplace.catalog.model.Category;
import com.marketplace.catalog.model.Product;
import com.marketplace.catalog.repositories.ProductRepository;
import com.marketplace.catalog.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CatalogServiceApplicationTests {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void getProduct_validUrl_ReturnsAllProducts() {
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product(1, "Помидоры", "Семейство помидоров", 299.99,
                        new Category(1, "Семена")),
                new Product(2, "Огурцы", "Семейство огурцовых", 399.99,
                        new Category(1, "Семена"))
        ));
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        Assertions.assertArrayEquals(productList.toArray(), productService.getAllProducts().toArray());
    }
    @Test
    void getProductById_validId_ReturnsAllProducts() throws Exception {
        Product product = new Product(1, "Помидоры", "Семейство помидоров", 299.99,
                new Category(1, "Семена"));
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Assertions.assertEquals(product, productService.getProductById(1L));
    }
    @Test
    void getProductById_invalidId_ReturnsException(){
        Product product = new Product(1, "Помидоры", "Семейство помидоров", 299.99,
                new Category(1, "Семена"));
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Assertions.assertThrows(Exception.class,()-> productService.getProductById(2L));
    }
}
