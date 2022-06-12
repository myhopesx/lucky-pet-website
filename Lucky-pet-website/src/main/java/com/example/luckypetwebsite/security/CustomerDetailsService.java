package com.example.luckypetwebsite.security;

import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer= customerService.getCustomerByEmail(email);

        if(!customer.isPresent()){
            throw new UsernameNotFoundException("customer not found");
        }

        return customer.get();
    }
}

