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

    private final SharedSubscriptionRepository subscriptionRepository;

    public void shareSubscription(Customer sharingCustomer, Customer customerWhoReceivesSubscription, Subscription subscription) {

        SharedSubscription sharedSubscription = new SharedSubscription();
        sharedSubscription.setCategory(subscription.getCategory());
        sharedSubscription.setRemainingContent(subscription.getRemainingContent());
        sharedSubscription.setSubscriptionDate(subscription.getSubscriptionDate());
        sharedSubscription.setPaymentDate(subscription.getPaymentDate());
        sharedSubscription.setSharingCustomer(sharingCustomer);
        sharedSubscription.setReceivingCustomer(customerWhoReceivesSubscription);

        subscriptionRepository.save(sharedSubscription);
        log.info("Subscription shared with {} and {}, category: {}", sharingCustomer.getEmail(),customerWhoReceivesSubscription.getEmail(), subscription.getCategory().getName());
    }
}
