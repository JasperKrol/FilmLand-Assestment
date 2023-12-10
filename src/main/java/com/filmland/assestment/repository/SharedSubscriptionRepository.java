package com.filmland.assestment.repository;

import com.filmland.assestment.entity.Customer;
import com.filmland.assestment.entity.SharedSubscription;
import com.filmland.assestment.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedSubscriptionRepository extends JpaRepository<SharedSubscription, Long> {

    boolean existsBySharingCustomerAndReceivingCustomer(Customer customer1, Customer customer2);
}
