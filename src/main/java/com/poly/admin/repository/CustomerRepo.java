package com.poly.admin.repository;

import com.poly.admin.model.Customer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
//    @Override
//    Optional<Customer> findCustomerById(Integer integer);

}
