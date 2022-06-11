package com.example.luckypetwebsite.controller;


import com.example.luckypetwebsite.model.Admin;
import com.example.luckypetwebsite.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/all")
    public ResponseEntity getAdmins(){
        return ResponseEntity.status(200).body(adminService.getAllAdmin());
    }

    @GetMapping("/{adminid}")
    public ResponseEntity getAdmin(@PathVariable Integer adminid){
        return ResponseEntity.status(200).body(adminService.getAdminById(adminid));
    }

    @PostMapping("/signup")
    public ResponseEntity postAdmin(@RequestBody @Valid Admin admin) throws IllegalAccessException {

        adminService.addAdmin(admin);
        logger.info(admin.getName()+" Admin added");
        return ResponseEntity.status(201).body("Admin Added!!");
    }
}
