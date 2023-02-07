package com.example.moviezone.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
@Entity
@Getter
@Setter
@ToString
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id_customer")
public class Customer extends User{


    Integer points;

    public Customer(String password, String first_name, String last_name, String address, String contact_number, String username) {
        super(password, first_name, last_name, address, contact_number, username);
    }

    public Customer() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id_user!=null && Objects.equals(id_user,customer.id_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(Role.ROLE_USER);
    }

}
