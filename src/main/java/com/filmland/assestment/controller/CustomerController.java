package com.filmland.assestment.controller;

import com.filmland.assestment.dto.CategoryResponseDto;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.repository.CustomerRepository;
import com.filmland.assestment.service.CategoryService;
import com.filmland.assestment.service.CustomerService;
import com.filmland.assestment.util.DefaultResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer() {

// TODO: 09-12-2023 fix all
        List<Customer> all = customerRepository.findAll();
//        DefaultResponseMessage defaultResponseMessage = DefaultResponseMessage.create("Login successful", "enjoy watching");

        return ResponseEntity.ok(all);
    }
}
