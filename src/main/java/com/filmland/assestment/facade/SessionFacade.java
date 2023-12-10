package com.filmland.assestment.facade;

import com.filmland.assestment.dto.*;
import com.filmland.assestment.entity.Category;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.Subscription;
import com.filmland.assestment.service.CategoryService;
import com.filmland.assestment.service.CustomerService;
import com.filmland.assestment.service.SharedSubscriptionService;
import com.filmland.assestment.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessionFacade {

    private final CategoryService categoryService;
    private final SubscriptionService subscriptionService;
    private final CustomerService customerService;
    private final SharedSubscriptionService sharedSubscriptionService;


    public CategoryResponseDto getAllCategories(String username) {

        Customer customer = getCustomer(username);

        List<CategoryDto> allCategories = categoryService.getAllCategories();

        List<SubscriptionDto> subscriptionList = subscriptionService.getSubscriptions(customer);

        List<CategoryDto> filtered = filterAvailableCategories(allCategories, subscriptionList);

        CategoryResponseDto response = new CategoryResponseDto();
        response.setAvailableCategories(filtered);
        response.setSubscribedCategories(subscriptionList);

        return response;
    }

    public void subscribeToCategory(SubscriptionInputDto subscriptionInputDto) {

        Customer customer = getCustomer(subscriptionInputDto.getEmail());
        String requestedCategory = subscriptionInputDto.getAvailableCategory();

        if (isSubscribedToCategory(customer, requestedCategory)) {
            throw new RuntimeException("already subscribed to");
        }

        Category category = categoryService.getCategory(requestedCategory).orElseThrow(() -> new RuntimeException("type not found"));

        boolean userHasSufficientCredit = validatesCredit(customer, category);

        if (userHasSufficientCredit) {

            subscriptionService.createSubscription(customer, category);

            customerService.updateCustomerAfterSubscription(customer, category);

        } else {
            throw new RuntimeException("not enough credit to subscribe");
        }
    }

    @Transactional
    public void shareSubscription(ShareSubscriptionDto shareSubscriptionDto) {

        Customer sharingCustomer = customerService.findCustomer(shareSubscriptionDto.getEmail());
        Customer customerWhoReceivesSubscription = customerService.findCustomer(shareSubscriptionDto.getCustomer());
        String categoryName = shareSubscriptionDto.getSubscribedCategory();

        List<Subscription> subscriptions = sharingCustomer.getSubscriptions();
        long subId = getSubId(subscriptions, categoryName);
        Subscription subscription = subscriptionService.getSubscription(subId);


        Category category = categoryService.getCategory(categoryName).orElseThrow(() -> new RuntimeException("Category not found"));
        double sharedCosts = category.getPrice() / 2;

        boolean sharingUserIsSubscribed = isSubscribedToCategory(sharingCustomer, categoryName);
        boolean receivingUserIsSubscribed = isSubscribedToCategory(customerWhoReceivesSubscription, categoryName);
        boolean sharedSubscriptionExists = sharedSubscriptionService.haveSharedSubscription(sharingCustomer, customerWhoReceivesSubscription);

        boolean sharingUserHasSufficientCredit = validatesCredit(customerWhoReceivesSubscription, category);
        boolean receivingUserHasSufficientCredit = validatesCredit(customerWhoReceivesSubscription, category);

        if (sharingUserIsSubscribed && sharingUserHasSufficientCredit
                && receivingUserHasSufficientCredit && !receivingUserIsSubscribed
                && !sharedSubscriptionExists) {

                customerService.creditCustomer(sharingCustomer, sharedCosts);
                customerService.creditCustomer(customerWhoReceivesSubscription, sharedCosts);
                sharedSubscriptionService.shareSubscription(sharingCustomer, customerWhoReceivesSubscription, subscription);



        } else {
            throw new RuntimeException("Could not share");
        }
    }

    private long getSubId(List<Subscription> subscriptions, String categoryName) {
        long subId = 0; // Initialize subId before the loop

        for (Subscription sub : subscriptions) {
            if (sub.getCategory().getName().equals(categoryName)) {
                subId = sub.getId();
                break;
            }
        }

        return subId;
    }

    private static boolean isSubscribedToCategory(Customer customer, String category) {
        return customer.getSubscriptions().stream().anyMatch(subscription -> subscription.getCategory().getName().equals(category));
    }

    private boolean validatesCredit(Customer customer, Category category) {
        return customer.getCredit() >= category.getPrice();
    }

    private List<CategoryDto> filterAvailableCategories(List<CategoryDto> availableCategories, List<SubscriptionDto> subscriptionList) {

        List<CategoryDto> filteredResults = new ArrayList<>(availableCategories);

        Iterator<CategoryDto> iterator = filteredResults.iterator();

        while (iterator.hasNext()) {
            CategoryDto availableCategory = iterator.next();

            for (SubscriptionDto subscription : subscriptionList) {

                if (availableCategory.getName().equals(subscription.getName())) {
                    iterator.remove();
                    break;
                }
            }
        }
        return filteredResults;
    }

    private Customer getCustomer(String username) {
        return customerService.findCustomer(username);
    }

}