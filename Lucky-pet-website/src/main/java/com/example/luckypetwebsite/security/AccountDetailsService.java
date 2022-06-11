package com.example.luckypetwebsite.security;

import com.example.luckypetwebsite.model.Account;
import com.example.luckypetwebsite.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

     private final AccountService accountService;

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          Optional<Account> account = accountService.getAccountByEmail(email);

          if (!account.isPresent()) {
               throw new UsernameNotFoundException("account not found");
          }

          List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
          String[] authStrings = account.get().getRoles().split(",");
          for (String authString : authStrings) {
               authorities.add(new SimpleGrantedAuthority(authString));
          }

          UserDetails ud = new User(account.get().getEmail(), account.get().getPassword(), authorities);
          return account.get();
     }
}
