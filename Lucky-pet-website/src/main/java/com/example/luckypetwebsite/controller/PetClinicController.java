package com.example.luckypetwebsite.controller;


import com.example.luckypetwebsite.model.PetClinic;
import com.example.luckypetwebsite.model.PetClinic;
import com.example.luckypetwebsite.service.PetClinicService;
import com.example.luckypetwebsite.service.PetClinicService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/pet-clinic")
@RequiredArgsConstructor
public class PetClinicController {

     private final PetClinicService petClinicService;

         Logger logger = LoggerFactory.getLogger(PetClinicController.class);

     @GetMapping("/all")
     public ResponseEntity getPetClinic() {
          return ResponseEntity.status(200).body(petClinicService.getAllPetClinic());
     }

     @PostMapping("/{ownerid}")
     public ResponseEntity postPetClinic(@RequestBody @Valid PetClinic petClinic, @PathVariable Integer ownerid) throws IllegalAccessException {
          if(!petClinicService.addPetClinic(petClinic, ownerid)){
               return ResponseEntity.status(404).body("Clinic Owner not found");
          }

        logger.info("new PetClinic added");
          return ResponseEntity.status(201).body("PetClinic Added!!");
     }

     @PutMapping
     public ResponseEntity updatePetClinic(@RequestBody @Valid PetClinic petClinic) throws IllegalAccessException {
          if (!petClinicService.updatePetClinic(petClinic)) {
               return ResponseEntity.status(400).body("petClinic not found");
          }
          return ResponseEntity.status(200).body("petClinic has updated");
     }

     @DeleteMapping("/{clinicid}/{ownerid}")
     public ResponseEntity deletePetClinic(@PathVariable @Valid Integer clinicid, @PathVariable Integer ownerid) throws IllegalAccessException {
          if (!petClinicService.deletePetClinic(clinicid, ownerid)) {
               return ResponseEntity.status(400).body("petClinic not found");
          }
          return ResponseEntity.status(200).body("petClinic has deleted");
     }

}
