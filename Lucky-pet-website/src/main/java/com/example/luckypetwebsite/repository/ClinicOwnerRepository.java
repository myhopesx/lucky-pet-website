package com.example.luckypetwebsite.repository;

import com.example.luckypetwebsite.model.ClinicOwner;
import com.example.luckypetwebsite.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicOwnerRepository extends JpaRepository<ClinicOwner,Integer> {

    Optional<ClinicOwner> findByEmail(String email);
}
