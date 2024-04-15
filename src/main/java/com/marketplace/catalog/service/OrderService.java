package com.marketplace.catalog.service;

import com.marketplace.catalog.model.Cart;
import com.marketplace.catalog.model.Order;
import com.marketplace.catalog.model.OrderProducts;
import com.marketplace.catalog.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    public void createOrder(Order newOrder, Long userId){
        List<Cart> cartList = cartService.getCart(userId);
        newOrder.setCreationDate(new Date());
        newOrder.setUserId(userId);
        double total = 0;
        List<OrderProducts> orderProductsList = new ArrayList<>();
        for(Cart cart : cartList){
            total += cart.getProduct().getPrice() * cart.getQuantity();
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setQuantity(cart.getQuantity());
            orderProducts.setUserId(userId);
            orderProducts.setProduct(cart.getProduct());
            orderProductsList.add(orderProducts);
        }
        newOrder.setSummary(total);
        newOrder.setActive(true);
        newOrder.setProductsList(orderProductsList);
        cartService.deleteCartByUserId(userId);
        orderRepository.save(newOrder);
    }
}
