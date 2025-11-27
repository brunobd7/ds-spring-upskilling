package com.dantas.springupskilling.repositories;

import com.dantas.springupskilling.entities.OrderItem;
import com.dantas.springupskilling.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
