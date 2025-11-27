package com.dantas.springupskilling.services;

import com.dantas.springupskilling.dto.OrderDTO;
import com.dantas.springupskilling.entities.Order;
import com.dantas.springupskilling.repositories.OrderRepository;
import com.dantas.springupskilling.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
        return new OrderDTO(order);
    }
}
