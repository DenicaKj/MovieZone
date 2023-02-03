package com.example.moviezone.model.manytomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "`customer_is_interested_in_event`")
@IdClass(CustomerIsInterestedInEventId.class)
public class CustomerIsInterestedInEvent {
    @Id
    @Column(name = "id_customer")
    Integer id_customer;
    @Column(name = "id_event")
    @Id
    Integer id_event;
}
