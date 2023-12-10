package com.filmland.assestment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shared_subscriptions")
public class SharedSubscription extends Subscription{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sharing_customer_id")
    Customer sharingCustomer;

    @ManyToOne
    @JoinColumn(name = "receiving_customer_id")
    Customer receivingCustomer;

}
