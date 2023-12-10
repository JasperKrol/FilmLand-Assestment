package com.filmland.assestment.repository;

import com.filmland.assestment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String customer);

    List<Customer> findBySubscriber(boolean subscriber);

    Optional<Object> findByUsername(String username);
}
