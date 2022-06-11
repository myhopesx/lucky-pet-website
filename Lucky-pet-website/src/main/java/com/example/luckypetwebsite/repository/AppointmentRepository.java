package com.example.luckypetwebsite.repository;

import com.example.luckypetwebsite.model.Appointment;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

     Optional<Appointment> findAppointmentByIdAndCustomer_Id(Integer appointmentId, Integer customerId);

     List<Appointment> findAllByPetClinic_Id(Integer clinic_id);
     List<Appointment> findAppointmentByPetClinic_Id(Integer clinic_id);
     List<Appointment> findAllByCustomer_Id(Integer customer_id);
}
