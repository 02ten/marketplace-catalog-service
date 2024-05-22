package com.marketplace.catalog.controller;

import com.marketplace.catalog.model.Order;
import com.marketplace.catalog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getAllOrdersByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getAllOrdersByUser(userId));
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @PutMapping("/status")
    public ResponseEntity<String> updateOrderStatus(@RequestBody Long id, @RequestBody String status){
        System.out.println(id + " " + status);
        try{
            orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok("Статус успешно изменен");
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
