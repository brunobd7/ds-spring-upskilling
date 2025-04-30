package com.dantas.springupskilling.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderItemPK {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof OrderItemPK that)) return false;

        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(order);
        result = 31 * result + Objects.hashCode(product);
        return result;
    }
}
