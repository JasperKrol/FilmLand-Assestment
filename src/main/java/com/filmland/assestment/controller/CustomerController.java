package com.filmland.assestment.controller;

import com.filmland.assestment.dto.CategoryResponseDto;
import com.filmland.assestment.service.CategoryService;
import com.filmland.assestment.service.CustomerService;
import com.filmland.assestment.util.DefaultResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<DefaultResponseMessage> getCustomer() {


        DefaultResponseMessage defaultResponseMessage = DefaultResponseMessage.create("Login successful", "enjoy watching");

        return ResponseEntity.ok(defaultResponseMessage);
    }
}
