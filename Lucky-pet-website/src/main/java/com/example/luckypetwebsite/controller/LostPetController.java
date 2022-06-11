package com.example.luckypetwebsite.controller;
import com.example.luckypetwebsite.service.LostPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lost-pet")
@RequiredArgsConstructor

public class LostPetController {

    private final LostPetService lostPetService;

    @GetMapping("/all")
    public ResponseEntity getAllLostPet() throws IllegalAccessException {
        return ResponseEntity.status(201).body(lostPetService.getAllLostPet());
    }

    @PostMapping("lost/{petid}/{ownedid}")
    public ResponseEntity addLostPost(@PathVariable Integer petid, @PathVariable Integer ownedid) throws IllegalAccessException {
        if(!lostPetService.lostPet(petid,ownedid)){
            return ResponseEntity.status(400).body("Pet or customer not found");
        }
        return ResponseEntity.status(201).body("post added!! we hope you found your pet soon T-T");
    }

    @PostMapping("found/{petid}/{ownedid}")
    public ResponseEntity foundPet(@PathVariable Integer petid, @PathVariable Integer ownedid) throws IllegalAccessException {
        if(!lostPetService.findPet(petid,ownedid)){
            return ResponseEntity.status(400).body("invalid found pet");
        }
        return ResponseEntity.status(201).body("nice now you fun");
    }

}
