package com.marketplace.catalog.controller;

import com.marketplace.catalog.model.Order;
import com.marketplace.catalog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/{userId}")
    public ResponseEntity<?> createOrder(@RequestBody Order orderDTO, @PathVariable Long userId){
        orderService.createOrder(orderDTO, userId);
        return ResponseEntity.ok("Заказ создан");

    }
}
