package com.example.luckypetwebsite.service;


import com.example.luckypetwebsite.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LostPetService {

     private final PetService petService;

    /* method for pet owners to post their lost pets'
    customer should owen this pet first then the method will set status of this pet
    by "lost" , and if anyone finds the pet , the post will close and the status of
    the pet will change to "owned" again
    */
    public List<Pet> getAllLostPet(){
         return petService.getAllPetByStatus("lost");
    }

     //    post lost pet
     public boolean lostPet(Integer petId, Integer customerId) {
          Optional<Pet> currentPet = petService.getPetByCustomer(customerId, petId);

          if (!currentPet.isPresent()) {
               return false;
          }

          currentPet.get().setStatus("lost");
          petService.updatePet(currentPet.get());
          return true;
     }

//_________________________________________________________________________

     //    pet found
     public boolean findPet(Integer petId, Integer customerId) {
          Optional<Pet> currentPet = petService.getPetByCustomer(customerId, petId);

          if (!currentPet.isPresent()) {
               return false;
          }

          currentPet.get().setStatus("owned");
          petService.updatePet(currentPet.get());
          return true;
     }

}
