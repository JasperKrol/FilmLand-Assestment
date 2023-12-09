package com.filmland.assestment.service;

import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.Subscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RenewalService {

    private final CustomerService customerService;
    private final SubscriptionService subscriptionService;

    public void renewSubscribers() {

        List<Customer> allSubscribedCustomers = customerService.getAllSubscribedCustomers();

        for (Customer customer : allSubscribedCustomers) {
            List<Subscription> subscriptions = customer.getSubscriptions();

            double totalSubscriptionPrice = subscriptions.stream().mapToDouble(subscription -> subscription.getCategory().getPrice()).sum();

            if (customer.getCredit() >= totalSubscriptionPrice) {

                for (Subscription subscription : subscriptions) {

                    if (customer.getCredit() >= subscription.getCategory().getPrice()) {

                        subscriptionService.renewSubscription(subscription);
                        log.info("Renewed subscription for user {}, with subscription {}", customer.getEmail(), subscription.getCategory());

                    }
                }
            } else {

//                subscriptionService.removeSubscriptions(subscriptions);
                log.info("Not enough credit to renew,");

            }
        }
    }
}
