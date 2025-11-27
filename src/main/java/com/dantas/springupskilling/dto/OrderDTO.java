package com.dantas.springupskilling.dto;

import com.dantas.springupskilling.entities.Order;
import com.dantas.springupskilling.entities.OrderItem;
import com.dantas.springupskilling.entities.OrderStatus;
import com.dantas.springupskilling.entities.Payment;
import com.dantas.springupskilling.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private UserMinDTO client;
    private PaymentDTO payment;
    private List<OrderItemDTO> items = new ArrayList<>();

    public Double getTotal() {
        return items.stream().mapToDouble(OrderItemDTO::getSubTotal).sum();
    }

    public OrderDTO (Order orderEntity){
        this.id = orderEntity.getId();
        this.moment = orderEntity.getMoment();
        this.status = orderEntity.getStatus();

        this.client = new UserMinDTO(orderEntity.getClient());
        this.payment = orderEntity.getPayment() != null
                ? new PaymentDTO(orderEntity.getPayment())
                : null ;

        orderEntity.getItems()
                .forEach(orderItemEntity -> this.items.add(new OrderItemDTO(orderItemEntity)));
    }


    @Data
    private static class UserMinDTO {
        private Long id;
        private String name;

        public UserMinDTO(User userEntity) {
            this.id = userEntity.getId();
            this.name = userEntity.getName();
        }
    }

    @Data
    private static class PaymentDTO {
        private Long id;
        private Instant moment;

        public PaymentDTO(Payment paymentEntity) {
            this.id = paymentEntity.getId();
            this.moment = paymentEntity.getMoment();
        }
    }

    @Data
    private static class OrderItemDTO {
        private Long productId;
        private String name;
        private Double price;
        private Integer quantity;
        private Double subTotal;

        public OrderItemDTO(OrderItem orderItemEntity) {
            this.productId = orderItemEntity.getProduct().getId();
            this.name = orderItemEntity.getProduct().getName();
            this.price = orderItemEntity.getProduct().getPrice();
            this.quantity = orderItemEntity.getQuantity();
            this.subTotal = orderItemEntity.getPrice() * orderItemEntity.getQuantity();
        }
    }

}
