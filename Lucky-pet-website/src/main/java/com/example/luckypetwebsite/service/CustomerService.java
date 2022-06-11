package com.example.luckypetwebsite.service;


import com.example.luckypetwebsite.model.Account;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.repository.AccountRepository;
import com.example.luckypetwebsite.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public void addCustomer(Customer customer)throws IllegalAccessException {
        // create account for customer
        Account account = accountService.addAccount(new Account(null,customer.getEmail(),customer, null, null,"customer"));

        // encrypt password
        customer.setPassword(
                new BCryptPasswordEncoder().encode(customer.getPassword()));

        // set account for customer
        customer.setAccount(account);
        customerRepository.save(customer);
    }

    public boolean updateCustomer(Customer customer){
        Optional<Customer> currentCustomer=getCustomerById(customer.getId());
        if (!currentCustomer.isPresent()){
            return false;
        }

        currentCustomer.get().setEmail(customer.getEmail());
        currentCustomer.get().setName(customer.getName());
        currentCustomer.get().setPhoneNumber(customer.getPhoneNumber());
//        currentCustomer.get().setRole(customer.getRole());
        currentCustomer.get().setAge(customer.getAge());

        customerRepository.save(currentCustomer.get());

        return true;
    }

    public boolean deleteCustomer(Integer id){
        Optional<Customer> currentCustomer=getCustomerById(id);
        if (!currentCustomer.isPresent()){
            return false;
        }
        customerRepository.delete(currentCustomer.get());
        return true;
    }



}
