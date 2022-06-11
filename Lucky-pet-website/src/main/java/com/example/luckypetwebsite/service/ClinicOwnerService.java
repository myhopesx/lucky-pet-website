package com.example.luckypetwebsite.service;
import com.example.luckypetwebsite.model.Account;
import com.example.luckypetwebsite.model.ClinicOwner;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.repository.AccountRepository;
import com.example.luckypetwebsite.repository.ClinicOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicOwnerService {

    private final ClinicOwnerRepository clinicOwnerRepository;
    private final AccountService accountService;

    public List getAllClinicOwner() {
        return clinicOwnerRepository.findAll();
    }

    public Optional<ClinicOwner> getClinicOwnerById(Integer id) {
        return clinicOwnerRepository.findById(id);
    }

    public Optional<ClinicOwner> getClinicOwnerByEmail(String email) {
        return clinicOwnerRepository.findByEmail(email);
    }

    public void addClinicOwner(ClinicOwner clinicOwner)throws IllegalAccessException{
        // create account for customer
        Account account = accountService.addAccount(new Account(null,clinicOwner.getEmail(),null, clinicOwner, null,"clinicOwner"));

        clinicOwner.setPassword(
                new BCryptPasswordEncoder().encode(clinicOwner.getPassword()));

        // set account for customer
        clinicOwner.setAccount(account);

        clinicOwnerRepository.save(clinicOwner);
    }

    public boolean updateClinicOwner(ClinicOwner clinicOwner){
        Optional<ClinicOwner> currentClinicOwner=getClinicOwnerById(clinicOwner.getId());
        if (!currentClinicOwner.isPresent()){
            return false;
        }
        currentClinicOwner.get().setEmail(clinicOwner.getEmail());
        currentClinicOwner.get().setName(clinicOwner.getName());
        currentClinicOwner.get().setPhoneNumber(clinicOwner.getPhoneNumber());
        clinicOwnerRepository.save(currentClinicOwner.get());
        return true;
    }
    public boolean deleteClinicOwner(Integer id){
        Optional<ClinicOwner> currentClinicOwner=getClinicOwnerById(id);
        if (!currentClinicOwner.isPresent()){
            return false;
        }
        clinicOwnerRepository.deleteById(id);
        return true;
    }
}
