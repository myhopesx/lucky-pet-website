package com.example.luckypetwebsite.service;

import com.example.luckypetwebsite.model.Account;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

     private final AccountRepository accountRepository;

     public Optional<Account> getAccountById(Integer id) {
          return accountRepository.findById(id);
     }

     public Optional<Account> getAccountByEmail(String email) {
          return accountRepository.findByEmail(email);

     }

     public Account addAccount(Account account)throws IllegalAccessException {
          return accountRepository.save(account);
     }

}

