package com.example.luckypetwebsite.repository;


import com.example.luckypetwebsite.model.Admin;
import com.example.luckypetwebsite.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByEmail(String email);
}
