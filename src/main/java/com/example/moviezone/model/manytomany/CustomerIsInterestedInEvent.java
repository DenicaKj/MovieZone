package com.example.moviezone.model.manytomany;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;


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
    Integer idcustomer;
    @Column(name = "id_event")
    @Id
    Integer idevent;

    public CustomerIsInterestedInEvent(Integer id_customer, Integer id_event) {
        this.idcustomer = id_customer;
        this.idevent = id_event;
    }
}
