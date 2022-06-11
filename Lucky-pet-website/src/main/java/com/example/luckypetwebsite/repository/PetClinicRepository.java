package com.example.luckypetwebsite.repository;

import com.example.luckypetwebsite.model.PetClinic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetClinicRepository extends JpaRepository<PetClinic,Integer> {
     Optional<PetClinic> findPetClinicByIdAndClinicOwner_Id(Integer petClinicId, Integer ownerId);
}
