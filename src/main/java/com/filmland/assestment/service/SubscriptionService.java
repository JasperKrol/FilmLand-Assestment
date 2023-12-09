package com.filmland.assestment.service;

import com.filmland.assestment.dto.SubscriptionDto;
import com.filmland.assestment.entity.Category;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.Subscription;
import com.filmland.assestment.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.filmland.assestment.util.AppDefaultConstants.*;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;


    public List<SubscriptionDto> getSubscriptions(Customer customer) {

        Long id = customer.getId();

        List<Subscription> list = subscriptionRepository.findAllByCustomerId(id);

        return list.stream().map(subscription ->
                        new SubscriptionDto(subscription.getCategory().getName(),
                                subscription.getRemainingContent(),
                                subscription.getCategory().getPrice(),
                                subscription.getSubscriptionDate())).
                collect(Collectors.toList());
    }

    public Subscription createSubscription(Customer customer, Category category) {

        int defaultRemainingContent = checkTypeToCalculate(category.getName());

        Subscription addSubscription = new Subscription();
        addSubscription.setRemainingContent(defaultRemainingContent);
        addSubscription.setSubscriptionDate(LOCAL_DATE_OF_NOW);
        addSubscription.setPaymentDate(LOCAL_DATE_OF_NOW);
        addSubscription.setCategory(category);
        addSubscription.setCustomer(customer);


        subscriptionRepository.save(addSubscription);

        return addSubscription;
    }

    public void renewSubscription(Subscription subscription) {

        subscription.setPaymentDate(LOCAL_DATE_OF_NOW);
        subscription.setSubscriptionDate(LOCAL_DATE_OF_NOW);
        subscription.setRemainingContent(determineDefaultValue(subscription.getCategory().getName()));

        subscriptionRepository.save(subscription);
    }

    public void removeSubscriptions(List<Subscription> subscriptions) {

        subscriptionRepository.deleteInBatch(subscriptions);
    }

    private int checkTypeToCalculate(String requestedCategory) {

        int defaultValue;

        if ("Dutch Movie".equals(requestedCategory)) {
            defaultValue = NL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE;
        } else if ("Dutch Series".equals(requestedCategory)) {
            defaultValue = NL_SERIES_AVAILABLE_CONTENT_DEFAULT_VALUE;
        } else if ("International Movies".equals(requestedCategory)) {
            defaultValue = INTERNATIONAL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE;
        } else {
            defaultValue = 10;
        }

        return defaultValue;
    }

    private int determineDefaultValue(String category) {
        switch (category) {
            case "Dutch Movies":
                return NL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE;
            case "Dutch Series":
                return NL_SERIES_AVAILABLE_CONTENT_DEFAULT_VALUE;
            case "International Movies":
                return INTERNATIONAL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE;
            default:
                // Handle other cases or throw an exception for unknown categories
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}

