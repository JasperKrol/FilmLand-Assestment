package com.filmland.assestment.facade;

import com.filmland.assestment.dto.CategoryDto;
import com.filmland.assestment.dto.CategoryResponseDto;
import com.filmland.assestment.dto.SubscriptionDto;
import com.filmland.assestment.dto.SubscriptionInputDto;
import com.filmland.assestment.entity.Category;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.Subscription;
import com.filmland.assestment.service.CategoryService;
import com.filmland.assestment.service.CustomerService;
import com.filmland.assestment.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        List<Subscription> subscriptions = customer.getSubscriptions();


        for (Subscription subscription : subscriptions) {
            if (subscription.getCategory().getName().equals(requestedCategory)) {
                throw new RuntimeException("already subscribed to");
            }
        }

        Category category = categoryService.getCategory(requestedCategory).orElseThrow(()-> new RuntimeException("type not found"));
        
        boolean userHasSufficientCredit = validatesCredit(customer, category);

        if (userHasSufficientCredit){

            Subscription subscription = subscriptionService.createSubscription(customer, category);

            customerService.updateCustomerAfterSubscription(customer,category);
        }
        



    }

    private boolean validatesCredit(Customer customer, Category category) {
        return customer.getCredit() >= category.getPrice();
    }


    private List<CategoryDto> filterAvailableCategories(List<CategoryDto> availableCategories, List<SubscriptionDto> subscriptionList) {

        List<CategoryDto> filteredResults = new ArrayList<>(availableCategories);

//      Iterator, hence you cannot remove item while looping over
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

