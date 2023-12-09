package com.filmland.assestment.repository;

import com.filmland.assestment.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllByCustomerId(Long id);
}
