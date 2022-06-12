package com.example.luckypetwebsite.security;

import com.example.luckypetwebsite.model.Admin;
import com.example.luckypetwebsite.model.ClinicOwner;
import com.example.luckypetwebsite.service.AdminService;
import com.example.luckypetwebsite.service.ClinicOwnerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin= adminService.getAdminByEmail(email);

        if(!admin.isPresent()){
            throw new UsernameNotFoundException("clinicOwner not found");
        }

        return admin.get();
    }
}
