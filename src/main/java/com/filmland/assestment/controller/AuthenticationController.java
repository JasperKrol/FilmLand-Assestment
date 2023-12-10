package com.filmland.assestment.controller;

import com.filmland.assestment.dto.LoginDTO;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.repository.CustomerRepository;
import com.filmland.assestment.service.CustomerService;
import com.filmland.assestment.util.DefaultResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.filmland.assestment.util.AppDefaultConstants.DEFAULT_FAILURE_STATUS;
import static com.filmland.assestment.util.AppDefaultConstants.DEFAULT_SUCCES_STATUS;

@RestController()
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final CustomerRepository customerRepository;

    @PostMapping()
    public ResponseEntity<DefaultResponseMessage> login( @Validated @RequestBody LoginDTO loginDTO) {

        if (loginDTO.getPassword().isEmpty() || loginDTO.getEmail().isEmpty()) {

            DefaultResponseMessage message = DefaultResponseMessage.create(DEFAULT_FAILURE_STATUS, "Login failed. Please try again.");

            return ResponseEntity.badRequest().body(message);
        }

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(loginDTO.getEmail());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Check if password matches
            if (customer.getPassword().equals(loginDTO.getPassword())) {

                DefaultResponseMessage successMessage = DefaultResponseMessage.create(DEFAULT_SUCCES_STATUS, "Enjoy watching!");

                return ResponseEntity.ok(successMessage);
            }
        }

        DefaultResponseMessage failureMessage = DefaultResponseMessage.create(DEFAULT_FAILURE_STATUS, "Login failed. Please check your email and password.");

        return ResponseEntity.badRequest().body(failureMessage);

    }
}
