package com.example.luckypetwebsite.security;


import com.example.luckypetwebsite.model.ClinicOwner;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.service.ClinicOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicOwnerDetailsService implements UserDetailsService {

    private final ClinicOwnerService clinicOwnerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ClinicOwner> clinicOwner= clinicOwnerService.getClinicOwnerByEmail(email);

        if(!clinicOwner.isPresent()){
            throw new UsernameNotFoundException("clinicOwner not found");
        }

        return clinicOwner.get();
    }
}
