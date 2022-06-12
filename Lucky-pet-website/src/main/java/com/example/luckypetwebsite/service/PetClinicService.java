package com.example.luckypetwebsite.service;


import com.example.luckypetwebsite.model.ClinicOwner;
import com.example.luckypetwebsite.model.PetClinic;
import com.example.luckypetwebsite.repository.PetClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetClinicService {

     private final PetClinicRepository petClinicRepository;
     private final ClinicOwnerService clinicOwnerService;

     public List getAllPetClinic() {
          return petClinicRepository.findAll();
     }

     public Optional<PetClinic> getPetClinicById(Integer id) {
          return petClinicRepository.findById(id);
     }

     public boolean addPetClinic(PetClinic petClinic, Integer clinicOwnerId) throws IllegalAccessException {
          Optional<ClinicOwner> currentClinicOwner = clinicOwnerService.getClinicOwnerById(clinicOwnerId);
          if (!currentClinicOwner.isPresent()) {
               return false;
          }
          petClinic.setClinicOwner(currentClinicOwner.get());
          petClinicRepository.save(petClinic);

          return true;
     }

     public boolean updatePetClinic(PetClinic petClinic) {
          Optional<PetClinic> currentPetClinic = getPetClinicById(petClinic.getId());
          if (!currentPetClinic.isPresent()) {
               return false;
          }
          currentPetClinic.get().setIsActive(petClinic.getIsActive());
          currentPetClinic.get().setAddress(petClinic.getAddress());
          currentPetClinic.get().setStartTime(petClinic.getStartTime());
          currentPetClinic.get().setEndTime(petClinic.getEndTime());
          currentPetClinic.get().setName(petClinic.getName());
          currentPetClinic.get().setDescription(petClinic.getDescription());
          petClinicRepository.save(currentPetClinic.get());
          return true;
     }


     public boolean deletePetClinic(Integer petId, Integer ownerid) {
          Optional<PetClinic> currentPet = petClinicRepository.findPetClinicByIdAndClinicOwner_Id(petId, ownerid);
          if (!currentPet.isPresent()) {
               return false;
          }

          petClinicRepository.delete(currentPet.get());
          return true;
     }
}
