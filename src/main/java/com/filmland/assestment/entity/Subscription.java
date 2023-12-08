package com.filmland.assestment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int remainingContent;
    private LocalDate subscriptionDate;
    private LocalDate paymentDate;

    // TODO: 08-12-2023 Relations
//    private Customer customer;
//    private Category category;
//    @ManyToOne
//    @JoinColumn(name = "subscriber_id")
//    private Subscriber subscriber;

}
