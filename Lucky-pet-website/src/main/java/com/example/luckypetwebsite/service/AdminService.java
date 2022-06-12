package com.example.luckypetwebsite.service;


import com.example.luckypetwebsite.model.Account;
import com.example.luckypetwebsite.model.Admin;
import com.example.luckypetwebsite.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AccountService accountService;

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Integer id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public void addAdmin(Admin admin)throws IllegalAccessException {
        // create account for admin
        Account account = accountService.addAccount(new Account(null,admin.getEmail(),null, null, admin,"admin"));

        // encrypt password
        admin.setPassword(
                new BCryptPasswordEncoder().encode(admin.getPassword()));

        // set account for admin
        admin.setAccount(account);
        adminRepository.save(admin);
    }
    
}
