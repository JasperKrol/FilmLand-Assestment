package com.filmland.assestment.service;

import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.Subscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RenewalService {

    private final CustomerService customerService;
    private final SubscriptionService subscriptionService;

    @Transactional
    public void renewSubscribers() {

        try {
            List<Customer> allSubscribedCustomers = customerService.getAllSubscribedCustomers();

            for (Customer customer : allSubscribedCustomers) {
                renewCustomerSubscriptions(customer);
            }
        } catch (Exception e) {
            log.error("Error occurred during subscription renewal: {}", e.getMessage(), e);
        }

    }

    private void renewCustomerSubscriptions(Customer customer) {
        List<Subscription> subscriptions = customer.getSubscriptions();

        double totalSubscriptionPrice = subscriptions.stream().mapToDouble(subscription -> subscription.getCategory().getPrice()).sum();

        if (customer.getCredit() >= totalSubscriptionPrice) {
            renewValidSubscriptions(customer, subscriptions, totalSubscriptionPrice);
        } else {
            handleInsufficientCredit(customer, subscriptions);
        }
    }

    private void renewValidSubscriptions(Customer customer, List<Subscription> subscriptions, double totalSubscriptionPrice) {
        for (Subscription subscription : subscriptions) {
            if (customer.getCredit() >= subscription.getCategory().getPrice()) {
                subscriptionService.renewSubscription(subscription);
                customerService.creditCustomer(customer, subscription.getCategory().getPrice());
                log.info("Renewed subscription for user {}, with subscription {}", customer.getEmail(), subscription.getCategory().getName());
            }
        }
    }

    private void handleInsufficientCredit(Customer customer, List<Subscription> subscriptions) {
        subscriptionService.removeSubscriptions(subscriptions);
        log.info("Not enough credit to renew subscriptions for user {}", customer.getEmail());
    }
}
