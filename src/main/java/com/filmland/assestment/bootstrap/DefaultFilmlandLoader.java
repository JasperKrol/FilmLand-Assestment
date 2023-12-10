package com.filmland.assestment.bootstrap;

import com.filmland.assestment.entity.Category;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.Subscription;
import com.filmland.assestment.repository.CategoryRepository;
import com.filmland.assestment.repository.CustomerRepository;
import com.filmland.assestment.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static com.filmland.assestment.util.AppDefaultConstants.*;

@AllArgsConstructor
@Configuration
public class DefaultFilmlandLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final SubscriptionRepository subscriptionRepository;


    @Override
    public void run(String... args) throws Exception {

        loadCustomerData();
        loadCategoryData();
        loadSubscriptionData();
    }

    private void loadCustomerData() {

        Customer customerOne = new Customer();
        customerOne.setId(1);
        customerOne.setUsername("info@filmland-assessment.nl");
        customerOne.setEmail("info@filmland-assessment.nl");
        customerOne.setSubscriber(true);
//        customerOne.setPassword(passwordEncoder.encode("Javaiscool90"));
        customerOne.setPassword("Javaiscool90");
        customerOne.setCredit(20);
        customerOne.setRegistrationDate(LocalDate.now());
        customerOne.setFreeTrailPeriod(LocalDate.now().plusMonths(1));

        Customer customerTwo = new Customer();
        customerTwo.setId(2);
        customerTwo.setUsername("client@filmland-assessment.nl");
        customerTwo.setEmail("client@filmland-assessment.nl");
        customerOne.setSubscriber(true);
        customerTwo.setPassword("password");
        customerTwo.setCredit(20);
        customerTwo.setRegistrationDate(LocalDate.now());
        customerTwo.setFreeTrailPeriod(LocalDate.now().plusMonths(1));


        customerRepository.save(customerOne);
        customerRepository.save(customerTwo);
    }

    private void loadCategoryData() {

        if (categoryRepository.count() == 0) {

            Category dutchSeries = new Category();
            dutchSeries.setName("Dutch Series");
            dutchSeries.setAvailableContent(NL_SERIES_AVAILABLE_CONTENT_DEFAULT_VALUE);
            dutchSeries.setPrice(6.0);
            categoryRepository.save(dutchSeries);

            Category dutchMovies = new Category();
            dutchMovies.setName("Dutch Movies");
            dutchMovies.setAvailableContent(NL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE);
            dutchMovies.setPrice(4.0);
            categoryRepository.save(dutchMovies);

            Category internationalMovies = new Category();
            internationalMovies.setName("International Movies");
            internationalMovies.setAvailableContent(INTERNATIONAL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE);
            internationalMovies.setPrice(8.0);

            categoryRepository.save(internationalMovies);
        }
    }

    private void loadSubscriptionData() {

        Customer customer = customerRepository.findByEmail("info@filmland-assessment.nl").orElseThrow();
        Category category = categoryRepository.findById(3L).orElseThrow();

        Subscription subscription = new Subscription();
        subscription.setId(1);
        subscription.setRemainingContent(5);
        subscription.setSubscriptionDate(LocalDate.of(2022, 12, 9));
        subscription.setPaymentDate(LocalDate.of(2022, 12, 9));
        subscription.setCategory(category);
        subscription.setCustomer(customer);

        List<Subscription> subscriptions = List.of(subscription);
        customer.setSubscriptions(subscriptions);

        subscriptionRepository.save(subscription);
        customerRepository.save(customer);
    }
}
