package com.example.luckypetwebsite.controller;

import com.example.luckypetwebsite.model.Pet;
import com.example.luckypetwebsite.service.PetService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/pet")
@RequiredArgsConstructor
public class PetController {

     private final PetService petService;

         Logger logger = LoggerFactory.getLogger(PetController.class);
     @GetMapping("/all")
     public ResponseEntity getPet() { return ResponseEntity.status(200).body(petService.getAllPet());
     }

     @PostMapping("customer/{customerid}")
     public ResponseEntity postPet(@RequestBody @Valid Pet Pet, @PathVariable Integer customerid) throws IllegalAccessException {

          petService.addPet(Pet, customerid);
        logger.info("new Pet added");
          return ResponseEntity.status(201).body("Pet Added!!");
     }

     @PutMapping
     public ResponseEntity updatePet(@RequestBody @Valid Pet pet) throws IllegalAccessException {
          if (!petService.updatePet(pet)) {
               return ResponseEntity.status(400).body("pet not found");
          }
          return ResponseEntity.status(200).body("pet has updated");
     }

     @DeleteMapping("/{petid}/{customerid}")
     public ResponseEntity updatePet(@PathVariable @Valid Integer petid,@PathVariable @Valid Integer customerid) throws IllegalAccessException {
          if (!petService.deletePet(petid,customerid)) {
               return ResponseEntity.status(400).body("pet not found");
          }
          return ResponseEntity.status(200).body("pet has deleted");
     }
}
