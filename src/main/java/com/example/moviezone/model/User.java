package com.example.moviezone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_user;
    String password;
    String first_name;
    String last_name;
    String address;
    String contact_number;
    String username;
    LocalDateTime date_created;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(Integer id_user, String password, String first_name, String last_name, String address, String contact_number, String username, LocalDateTime date_created, Role role) {
        this.id_user = id_user;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.contact_number = contact_number;
        this.username = username;
        this.date_created = date_created;
        this.role = role;
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
