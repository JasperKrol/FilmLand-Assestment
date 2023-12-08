package com.filmland.assestment.bootstrap;

import com.filmland.assestment.entity.Category;
import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.repository.CategoryRepository;
import com.filmland.assestment.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.filmland.assestment.util.AppDefaultConstants.*;

@AllArgsConstructor
@Component
public class DefaultFilmlandLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        loadCustomerData();
        loadCategoryData();
    }

    private void loadCustomerData() {

        Customer customerOne = new Customer();
        customerOne.setId(1);
        customerOne.setUsername("info@filmland-assessment.nl");
        customerOne.setEmail("info@filmland-assessment.nl");
//        customerOne.setPassword(passwordEncoder.encode("password"));
        customerOne.setPassword("password");
        customerOne.setCredit(20);
        customerOne.setRegistrationDate(LocalDate.now());

        Customer customerTwo = new Customer();
        customerTwo.setId(2);
        customerTwo.setUsername("client@filmland-assessment.nl");
        customerTwo.setEmail("client@filmland-assessment.nl");
//        customerTwo.setPassword(passwordEncoder.encode("password"));
        customerTwo.setPassword("password");
        customerTwo.setCredit(20);
        customerTwo.setRegistrationDate(LocalDate.now());

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
            internationalMovies.setName("Dutch Movies");
            internationalMovies.setAvailableContent(INTERNATIONAL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE);
            internationalMovies.setPrice(8.0);

            categoryRepository.save(internationalMovies);
        }
    }
}
