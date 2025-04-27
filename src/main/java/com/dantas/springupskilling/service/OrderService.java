package com.dantas.springupskilling.service;

import com.dantas.springupskilling.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private ShippingService shippingService;

    public OrderService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public Double calcTotal(Order order) {

        Double discountValue = ( order.getDiscount()/ 100) * order.getBasic();
        order.setBasic(order.getBasic() - discountValue);

        return order.getBasic() + shippingService.shipment(order);
    }

}
