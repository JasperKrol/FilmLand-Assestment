package com.filmland.assestment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String username;

    private String password;

    private double credit;

    private LocalDate registrationDate;

    private LocalDate freeTrailPeriod;

    private boolean subscriber;

    @OneToMany(mappedBy = "customer")
    private List<Subscription> subscriptions;

    @Override
    public boolean isSubscriber() {

        if (!subscriptions.isEmpty()) {
            this.subscriber = true;
        }

        return subscriber;
    }

}
