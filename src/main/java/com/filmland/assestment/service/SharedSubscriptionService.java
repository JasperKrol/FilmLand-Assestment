package com.filmland.assestment.service;

import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.SharedSubscription;
import com.filmland.assestment.entity.Subscription;
import com.filmland.assestment.repository.SharedSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SharedSubscriptionService {

    private final SharedSubscriptionRepository sharedSubscriptionRepository;

    public void shareSubscription(Customer sharingCustomer, Customer customerWhoReceivesSubscription, Subscription subscription) {

        SharedSubscription sharedSubscription = new SharedSubscription();
        sharedSubscription.setCategory(subscription.getCategory());
        sharedSubscription.setRemainingContent(subscription.getRemainingContent());
        sharedSubscription.setSubscriptionDate(subscription.getSubscriptionDate());
        sharedSubscription.setPaymentDate(subscription.getPaymentDate());
        sharedSubscription.setSharingCustomer(sharingCustomer);
        sharedSubscription.setReceivingCustomer(customerWhoReceivesSubscription);

        sharedSubscriptionRepository.save(sharedSubscription);
        log.info("Subscription shared with {} and {}, category: {}", sharingCustomer.getEmail(),customerWhoReceivesSubscription.getEmail(), subscription.getCategory().getName());
    }

    public boolean haveSharedSubscription(Customer customer1, Customer customer2) {
        return sharedSubscriptionRepository.existsBySharingCustomerAndReceivingCustomer(customer1, customer2)
                || sharedSubscriptionRepository.existsBySharingCustomerAndReceivingCustomer(customer2, customer1);
    }
}
