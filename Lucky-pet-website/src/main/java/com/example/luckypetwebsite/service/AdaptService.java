package com.example.luckypetwebsite.service;


import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdaptService {

    private final PetService petService;
    private final CustomerService customerService;

    public List<Pet> getAllPetForAdopt(){
        return petService.getAllPetByStatus("offered");
    }

    public boolean AddPetForAdopt(Integer customerId,Integer petId){
        Optional<Pet>currentPet=petService.getPetByCustomer(customerId,petId);
        if (!currentPet.isPresent()||!currentPet.get().getStatus().equalsIgnoreCase("owned")){
            return false;
        }
        currentPet.get().setStatus("offered");
        petService.updatePet(currentPet.get());
        return true;
    }

    public boolean adoptPet(Integer petOwnerId,Integer petId,Integer customerId){
        Optional<Pet>currentPet=petService.getPetById(petId);
        Optional<Customer>petOwner=customerService.getCustomerById(petOwnerId);
        Optional<Customer>customer=customerService.getCustomerById(customerId);

        if (!currentPet.isPresent()||!petOwner.isPresent()||!customer.isPresent()
                ||!currentPet.get().getStatus().equalsIgnoreCase("offered")){
            return false;
        }
        currentPet.get().setStatus("owned");
        petOwner.get().getPets().remove(currentPet.get());
        customer.get().getPets().add(currentPet.get());
        currentPet.get().setCustomer(customer.get());
        petService.updatePet(currentPet.get());
        customerService.updateCustomer(petOwner.get());
        customerService.updateCustomer(customer.get());
        return true;
    }

}
