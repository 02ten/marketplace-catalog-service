package com.marketplace.catalog.service;

import com.marketplace.catalog.model.Cart;
import com.marketplace.catalog.model.Product;
import com.marketplace.catalog.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public List<Cart> getCart(Long id){
        return cartRepository.findCartsByUserId(id);
    }
    public Cart addToCart(Long userId, Long productId, Long quantity){
        Optional<Cart> cartOptional = cartRepository.findCartByUserIdAndProductId(userId, productId);
        log.info("Adding to cart");
        if(cartOptional.isEmpty()){
            log.info("No such element in cart. Creating new");
            Cart cart = new Cart();
            cart.setQuantity(quantity);
            cart.setUserId(userId);
            Product product = productService.getProductById(productId);
            cart.setProduct(product);
            return cartRepository.save(cart);
        }
        log.info("Updating cart");
        Cart cart = cartOptional.get();
        cart.setQuantity(cart.getQuantity()+quantity);
        return cartRepository.save(cart);
    }
    public Cart updateCart(Cart cart){
        log.info("Updating cart");
        Optional<Cart> cartOptional = cartRepository.findById(cart.getId());
        if(cartOptional.isEmpty()){
            log.error("No such product in cart");
            throw new IllegalArgumentException("У вас нет такого товара");
        }
        Cart updatedCart = cartOptional.get();
        updatedCart.setQuantity(cart.getQuantity());
        if(updatedCart.getQuantity() <= 0){
            cartRepository.deleteById(updatedCart.getId());
            return null;
        }
        return cartRepository.save(updatedCart);
    }
    public void deleteCart(Long cartId){
        log.info("Deleting cart");
        if(!cartRepository.existsById(cartId)){
            log.error("No such cart");
            throw new IllegalArgumentException("У вас нет такого товара в корзине");
        }
        cartRepository.deleteById(cartId);

    }
}
