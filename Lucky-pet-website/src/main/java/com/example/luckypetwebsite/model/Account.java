package com.example.luckypetwebsite.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account implements UserDetails {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @NotEmpty(message = "email cannot be empty")
     @Email(message = "must be valid email")
     @Column(unique = true)
     private String email;

     @OneToOne(mappedBy = "account", cascade = CascadeType.ALL,
             fetch = FetchType.LAZY, optional = false)
     @JsonIgnore
     private Customer customer;

     @JsonIgnore
     @OneToOne(mappedBy = "account", cascade = CascadeType.ALL,
             fetch = FetchType.LAZY, optional = false)
     private ClinicOwner clinicOwner;

     @OneToOne(mappedBy = "account", cascade = CascadeType.ALL,
             fetch = FetchType.LAZY, optional = false)
     @JsonIgnore
     private Admin admin;

     @NotEmpty
     @Column(nullable = false)
     @JsonIgnore
     private String roles;

     public Collection<? extends GrantedAuthority> getAuthorities() {
          return Collections.singleton(new SimpleGrantedAuthority(roles));
     }

     @Override
     @JsonIgnore
     public String getPassword() {
          return null;
     }

     @Override
     @JsonIgnore
     public String getUsername() {
          return null;
     }

     @Override
     @JsonIgnore
     public boolean isAccountNonExpired() {
          return true;
     }

     @Override
     @JsonIgnore
     public boolean isAccountNonLocked() {
          return true;
     }

     @Override
     @JsonIgnore
     public boolean isCredentialsNonExpired() {
          return true;
     }

     @Override
     @JsonIgnore
     public boolean isEnabled() {
          return true;
     }
}
