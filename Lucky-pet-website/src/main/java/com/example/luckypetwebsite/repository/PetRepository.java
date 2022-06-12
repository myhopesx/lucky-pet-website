package com.example.luckypetwebsite.repository;

import com.example.luckypetwebsite.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet,Integer> {

    Optional<Pet> findPetByIdAndCustomer_Id(Integer petId,Integer customerId);
    List<Pet> findAllByStatus(String status);
}
