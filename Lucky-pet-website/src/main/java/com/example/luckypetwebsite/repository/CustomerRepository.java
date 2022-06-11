package com.example.luckypetwebsite.repository;

import com.example.luckypetwebsite.model.Appointment;
import com.example.luckypetwebsite.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByEmail(String email);
}
