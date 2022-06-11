package com.example.luckypetwebsite.controller;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/all")
    public ResponseEntity getCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomer());
    }

    @GetMapping("/{customerid}")
    public ResponseEntity getCustomer(@PathVariable Integer customerid){
        return ResponseEntity.status(200).body(customerService.getCustomerById(customerid));
    }

    @PostMapping("/signup")
    public ResponseEntity postCustomer(@RequestBody @Valid Customer Customer) throws IllegalAccessException {

        customerService.addCustomer(Customer);
        logger.info("new Customer added");
        return ResponseEntity.status(201).body("Customer Added!!");
    }

    @PutMapping("/profile")
    public ResponseEntity updateCustomer(@RequestBody @Valid Customer customer)throws IllegalAccessException {
        if(!customerService.updateCustomer(customer)){
            return ResponseEntity.status(400).body("customer not found");
        }
        return ResponseEntity.status(200).body("customer has updated");
    }

    @DeleteMapping("/profile/{customerid}")
    public ResponseEntity deleteCustomer(@PathVariable @Valid Integer customerid)throws IllegalAccessException {
        if(!customerService.deleteCustomer(customerid)){
            return ResponseEntity.status(400).body("customer not found");
        }

        return ResponseEntity.status(200).body("customer has deleted");
    }
}
