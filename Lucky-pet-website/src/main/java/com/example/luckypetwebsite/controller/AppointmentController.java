package com.example.luckypetwebsite.controller;

import com.example.luckypetwebsite.model.Appointment;
import com.example.luckypetwebsite.service.AppointmenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

     private final AppointmenService appointmenService;

     Logger logger = LoggerFactory.getLogger(AppointmentController.class);

     @GetMapping("/all")
     public ResponseEntity getAllAppointment() {
          return ResponseEntity.status(200).body(appointmenService.getAllAppointment());
     }

     @GetMapping("/clinic-owner/{clinic_id}")
     public ResponseEntity getAllAppointmentForClinic(@PathVariable Integer clinic_id) {
          return ResponseEntity.status(200).body(appointmenService.getAllAppointmentByClinicId(clinic_id));
     }

     @GetMapping("/customer/{customer_id}")
     public ResponseEntity getAllAppointmentForCustomer(@PathVariable Integer customer_id) {
          return ResponseEntity.status(200).body(appointmenService.getAllAppointmentByCustomerId(customer_id));
     }

     @PostMapping("/customer/{customerid}/{petclinic}")
     public ResponseEntity postAppointment(@RequestBody @Valid Appointment Appointmen,
                                           @PathVariable Integer customerid, @PathVariable Integer petclinic) throws IllegalAccessException {

          appointmenService.addAppointment(Appointmen, customerid, petclinic);
          logger.info("new Appointment added");
          return ResponseEntity.status(201).body("Appointment Added!!");
     }

     @DeleteMapping("/{appointmenid}/{customerId}")
     public ResponseEntity cancelAppointment(@PathVariable @Valid Integer appointmenid, @PathVariable Integer customerId) throws IllegalAccessException {
          if (!appointmenService.cancelAppointment(appointmenid, customerId)) {
               return ResponseEntity.status(400).body("appointment not found");
          }
          return ResponseEntity.status(200).body("appointment has deleted");
     }
}
