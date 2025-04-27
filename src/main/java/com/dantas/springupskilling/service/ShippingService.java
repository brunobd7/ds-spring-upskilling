package com.dantas.springupskilling.service;

import com.dantas.springupskilling.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double shipment(Order order) {

       if (order.getBasic() < 100)
           return 20D;
       if (order.getBasic() >= 100 && order.getBasic() < 200)
           return 12D;

       return 0d;
    }

}
