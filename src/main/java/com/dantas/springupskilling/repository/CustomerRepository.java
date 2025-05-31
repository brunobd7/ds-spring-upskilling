package com.dantas.springupskilling.repository;

import com.dantas.springupskilling.dto.CustomerInfoDTO;
import com.dantas.springupskilling.entity.Customer;
import com.dantas.springupskilling.projection.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true,
            value = "SELECT name FROM customers WHERE state = :nameFilter")
    public List<CustomerInfo>  searchProjection1(String nameFilter);


    @Query("SELECT new com.dantas.springupskilling.dto.CustomerInfoDTO(c.name)  " +
            "FROM Customer c WHERE upper(c.state) = UPPER(:nameFilter) ")
    public List<CustomerInfoDTO>  searchProjection2(String nameFilter);

}
