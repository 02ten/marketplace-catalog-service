package com.marketplace.catalog.repositories;

import com.marketplace.catalog.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findCartsByUserId(Long id);
    Optional<Cart> findCartByUserIdAndProductId(Long userId, Long productIdl);
}
