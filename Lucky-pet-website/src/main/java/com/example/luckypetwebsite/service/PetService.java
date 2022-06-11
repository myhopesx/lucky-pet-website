package com.example.luckypetwebsite.service;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.model.Pet;
import com.example.luckypetwebsite.repository.CustomerRepository;
import com.example.luckypetwebsite.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final CustomerService customerService;

    public List getAllPet() {
        return petRepository.findAll();
    }

    public List<Pet> getAllPetByStatus(String status) {
        return petRepository.findAllByStatus(status);
    }


    public Optional<Pet> getPetById(Integer id) {
        return petRepository.findById(id);
    }
    public boolean addPet(Pet pet,Integer customerId)throws IllegalAccessException{
        Optional<Customer> currentCustomer=customerService.getCustomerById(customerId);
        if (!currentCustomer.isPresent()){
            return false;
        }
        pet.setCustomer(currentCustomer.get());
        petRepository.save(pet);
        return true;
    }

    public boolean updatePet(Pet pet){
        Optional<Pet> currentPet=getPetById(pet.getId());
        if (!currentPet.isPresent()){
            return false;
        }
        currentPet.get().setAge(pet.getAge());
        currentPet.get().setDescription(pet.getDescription());
        currentPet.get().setImage(pet.getImage());
        currentPet.get().setType(pet.getType());
        currentPet.get().setStatus(pet.getStatus());
        petRepository.save(currentPet.get());
        return true;
    }
    public boolean deletePet(Integer petid, Integer customerid){
        Optional<Pet> currentPet=petRepository.findPetByIdAndCustomer_Id(customerid,petid);
        if (!currentPet.isPresent()){
            return false;
        }
        petRepository.deleteById(petid);
        return true;
    }

    public Optional<Pet> getPetByCustomer(Integer customerId,Integer petId){
        Optional<Pet> pet=petRepository.findPetByIdAndCustomer_Id(customerId,petId);
        if (!pet.isPresent()){
            return null;
        }
        return pet;
    }
}
