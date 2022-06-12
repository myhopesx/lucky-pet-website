package com.example.luckypetwebsite.controller;

import com.example.luckypetwebsite.model.ClinicOwner;
import com.example.luckypetwebsite.service.ClinicOwnerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/clinic-owner")
@RequiredArgsConstructor
public class ClinicOwnerController {

     private final ClinicOwnerService clinicOwnerService;

         Logger logger = LoggerFactory.getLogger(ClinicOwnerController.class);
     @GetMapping("/all")
     public ResponseEntity getClinicOwner() {
          return ResponseEntity.status(200).body(clinicOwnerService.getAllClinicOwner());
     }

     @GetMapping("/{id}")
     public ResponseEntity getClinicOwnerById(@PathVariable Integer id){
          return ResponseEntity.status(200).body(clinicOwnerService.getClinicOwnerById(id));
     }

     @PostMapping("/signup")
     public ResponseEntity postClinicOwner(@RequestBody @Valid ClinicOwner clinicOwner) throws IllegalAccessException {
          clinicOwnerService.addClinicOwner(clinicOwner);
        logger.info("new ClinicOwner added");
          return ResponseEntity.status(201).body("ClinicOwner Added!!");
     }

     @PutMapping("/profile")
     public ResponseEntity updateClinicOwner(@RequestBody @Valid ClinicOwner clinicOwner) throws IllegalAccessException {
          if (!clinicOwnerService.updateClinicOwner(clinicOwner)) {
               return ResponseEntity.status(400).body("clinicOwner not found");
          }
          return ResponseEntity.status(200).body("clinicOwner has updated");
     }

     @DeleteMapping("/{id}")
     public ResponseEntity updateClinicOwner(@PathVariable @Valid Integer id) throws IllegalAccessException {
          if (!clinicOwnerService.deleteClinicOwner(id)) {
               return ResponseEntity.status(400).body("clinicOwner not found");
          }
          return ResponseEntity.status(200).body("clinicOwner has deleted");
     }

}
