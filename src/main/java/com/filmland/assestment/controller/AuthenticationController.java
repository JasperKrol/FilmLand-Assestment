package com.filmland.assestment.controller;

import com.filmland.assestment.dto.LoginDTO;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.repository.CustomerRepository;
import com.filmland.assestment.service.CustomerService;
import com.filmland.assestment.util.DefaultResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping()
    public ResponseEntity<DefaultResponseMessage> login(@RequestBody LoginDTO loginDTO) {

        DefaultResponseMessage defaultResponseMessage = DefaultResponseMessage.create("Login successful", "enjoy watching");

        return ResponseEntity.ok(defaultResponseMessage);


    }
}
