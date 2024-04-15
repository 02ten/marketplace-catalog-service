package com.marketplace.catalog.controller;

import com.marketplace.catalog.model.Cart;
import com.marketplace.catalog.service.CartService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {
    private final CartService cartService;
    private final Tracer tracer;
    @GetMapping("/{id}")
    public ResponseEntity<List<Cart>> getCart(@PathVariable Long id){
        Span span = tracer.buildSpan("Getting user's cart").start();
        Tags.HTTP_METHOD.set(span, "GET");
        Tags.HTTP_URL.set(span, "/api/cart/"+id);
        List<Cart> carts = cartService.getCart(id);
        span.finish();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<Cart> addProductAtCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam Long quantity){
        Span span = tracer.buildSpan("Adding product at cart").start();
        Tags.HTTP_METHOD.set(span, "GET");
        Tags.HTTP_URL.set(span, "/api/cart/"+userId+"/"+productId);
        Cart cart = cartService.addToCart(userId,productId, quantity);
        span.finish();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<?> updateCart(@RequestBody Cart cartToUpdate){
        Span span = tracer.buildSpan("Updating product in cart").start();
        Tags.HTTP_METHOD.set(span, "PUT");
        Tags.HTTP_URL.set(span, "/api/cart/");
        try {
            Cart cart = cartService.updateCart(cartToUpdate);
            span.finish();
            return ResponseEntity.ok(cart);
        }catch (IllegalArgumentException ex){
            span.finish();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId){
        Span span = tracer.buildSpan("Deleting product in cart").start();
        Tags.HTTP_METHOD.set(span, "DELETE");
        Tags.HTTP_URL.set(span, "/api/cart/");
        try {
            cartService.deleteCart(cartId);
            span.finish();
            return ResponseEntity.ok("Товар успешно удален");
        }catch (IllegalArgumentException ex){
            span.finish();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

