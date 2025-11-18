package com.dantas.springupskilling.services;

import com.dantas.springupskilling.dto.OrderDTO;
import com.dantas.springupskilling.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO findOrderById(Long orderId) {
        return new OrderDTO( orderRepository.findOrderById(orderId));
    }
}
