package com.dantas.springupskilling;

import com.dantas.springupskilling.dto.OrderDTO;
import com.dantas.springupskilling.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrdersById(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }
}
