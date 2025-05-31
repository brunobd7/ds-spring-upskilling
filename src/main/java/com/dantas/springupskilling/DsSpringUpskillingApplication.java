package com.dantas.springupskilling;

import com.dantas.springupskilling.dto.CustomerInfoDTO;
import com.dantas.springupskilling.projection.CustomerInfo;
import com.dantas.springupskilling.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DsSpringUpskillingApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(DsSpringUpskillingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<CustomerInfo> customers = customerRepository.searchProjection1("RS");
        List<CustomerInfoDTO> customersDTO = customerRepository.searchProjection2("rs");

        System.out.println("*********** Projection using native QUERY  *********** \n");
        customers.stream().map(CustomerInfo::getName).forEach(System.out::println);


        System.out.println("\n\n*********** Projection using JPQL *********** \n");
        customersDTO.forEach(System.out::println);
    }
}
