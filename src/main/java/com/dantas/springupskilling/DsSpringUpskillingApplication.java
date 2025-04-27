package com.dantas.springupskilling;

import com.dantas.springupskilling.entity.Order;
import com.dantas.springupskilling.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.NumberFormatter;

@SpringBootApplication
public class DsSpringUpskillingApplication implements CommandLineRunner {


    @Autowired
    OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(DsSpringUpskillingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Order firstOrder = Order.builder().code(1034).basic(150D).discount(20D).build();
        var firstOrderTotal = orderService.calcTotal(firstOrder);
        System.out.println("Order Code ".concat(firstOrder.getCode().toString()).concat("\nOrder Total: R$" + firstOrderTotal));

        Order secondOrder = Order.builder().code(2282).basic(800D).discount(10D).build();
        var secondOrderTotal = orderService.calcTotal(secondOrder);
        System.out.println("Order Code ".concat(secondOrder.getCode().toString()).concat("\nOrder Total: R$" + secondOrderTotal));

        Order thirdOrder = Order.builder().code(1309).basic(95.90D).discount(0D).build();
        var thirdOrderTotal = orderService.calcTotal(thirdOrder);
        System.out.println("Order Code ".concat(thirdOrder.getCode().toString()).concat("\nOrder Total: R$" + thirdOrderTotal));
    }
}
