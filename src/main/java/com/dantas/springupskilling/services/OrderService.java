package com.dantas.springupskilling.services;

import com.dantas.springupskilling.dto.OrderDTO;
import com.dantas.springupskilling.entities.Order;
import com.dantas.springupskilling.entities.OrderItem;
import com.dantas.springupskilling.entities.OrderStatus;
import com.dantas.springupskilling.entities.Product;
import com.dantas.springupskilling.repositories.OrderItemRepository;
import com.dantas.springupskilling.repositories.OrderRepository;
import com.dantas.springupskilling.repositories.ProductRepository;
import com.dantas.springupskilling.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthService authService;


    @Transactional(readOnly = true)
    public OrderDTO findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
        authService.selfOrAdminValidation(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO){

        Order newOrder = new Order();
        newOrder.setMoment(Instant.now());
        newOrder.setStatus(OrderStatus.WAITING_PAYMENT);
        newOrder.setClient(userService.authenticated()); // FROM SECURITY CONTEXT

        orderDTO.getItems().forEach(orderItemDTO -> {
            Product product = productRepository.findById(orderItemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
            OrderItem orderItem = new OrderItem(newOrder,product,orderItemDTO.getQuantity(),product.getPrice());

            newOrder.getItems().add(orderItem);
        });

        orderRepository.save(newOrder);
        orderItemRepository.saveAll(newOrder.getItems());

        return new OrderDTO(newOrder);
    }
}
