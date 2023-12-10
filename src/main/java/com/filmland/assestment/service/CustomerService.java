package com.filmland.assestment.service;

import com.filmland.assestment.entity.Category;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.filmland.assestment.util.AppDefaultConstants.LOCAL_DATE_OF_NOW;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findCustomer(String username) {

        return customerRepository.findByEmail(username)
                .orElseThrow(
                        () -> new RuntimeException("cannot find user with id: %s {username}"));
    }

    public void updateCustomerAfterSubscription(Customer customer, Category category) {

        if (!freeTrailPeriod(customer)) {

            double newCredit = customer.getCredit() - category.getPrice();
            customer.setCredit(newCredit);

            customerRepository.save(customer);
        }

        if (!customer.isSubscriber()){
            customer.setSubscriber(true);
            customerRepository.save(customer);
        }
    }

    public List<Customer> getAllSubscribedCustomers() {

        return customerRepository.findBySubscriber(true);

    }

    public void creditCustomer(Customer customer, double totalSubscriptionPrice) {

        customer.setCredit(customer.getCredit() - totalSubscriptionPrice);

        customerRepository.save(customer);
    }

    private boolean freeTrailPeriod(Customer customer) {
        return customer.getFreeTrailPeriod().isAfter(LOCAL_DATE_OF_NOW);
    }
}
