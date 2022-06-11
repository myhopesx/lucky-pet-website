package com.example.luckypetwebsite.repository;

import com.example.luckypetwebsite.model.Account;
import com.example.luckypetwebsite.model.ClinicOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {

     Optional<Account> findByEmail(String email);

}
