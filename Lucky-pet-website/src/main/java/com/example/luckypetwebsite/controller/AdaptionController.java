package com.example.luckypetwebsite.controller;

import com.example.luckypetwebsite.model.Appointment;
import com.example.luckypetwebsite.service.AdaptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/adaption")
@RequiredArgsConstructor
public class AdaptionController {

     private final AdaptService adaptService;

     @GetMapping("/all")
     public ResponseEntity getAllPetForAdopt() throws IllegalAccessException {
          return ResponseEntity.status(201).body(adaptService.getAllPetForAdopt());
     }

     @PostMapping("/offered/{petid}/{customerid}")
     public ResponseEntity AddPetForAdopt(@PathVariable Integer petid, @PathVariable Integer customerid) throws IllegalAccessException {
          if (!adaptService.AddPetForAdopt(petid, customerid)) {
               return ResponseEntity.status(400).body("Pet or customer not found");
          }
          return ResponseEntity.status(201).body("pet Added for adaption!!");
     }

     @PostMapping("/adopt/{petid}/{ownedid}/{customerid}")
     public ResponseEntity adoptPet(@PathVariable Integer petid, @PathVariable Integer ownedid, @PathVariable Integer customerid) throws IllegalAccessException {
          if (!adaptService.adoptPet(ownedid, petid, customerid)) {
               return ResponseEntity.status(400).body("invalid adopt pet");
          }
          return ResponseEntity.status(201).body("thank you so much now you are the owner of the pet");
     }

}
