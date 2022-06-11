package com.example.luckypetwebsite.service;
import com.example.luckypetwebsite.excption.AppointmentDateException;
import com.example.luckypetwebsite.model.Appointment;
import com.example.luckypetwebsite.model.Customer;
import com.example.luckypetwebsite.model.PetClinic;
import com.example.luckypetwebsite.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmenService {
    private final AppointmentRepository appointmentRepository;
    private final CustomerService customerService;
    private final PetClinicService petClinicService;

    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }


    public boolean addAppointment(Appointment appointmen, Integer customerId,Integer clinicId)throws IllegalAccessException{
        Optional<Customer> currentCustomer=customerService.getCustomerById(customerId);
        Optional<PetClinic> currentPetClinic=petClinicService.getPetClinicById(clinicId);

        if (!currentCustomer.isPresent()||!currentPetClinic.isPresent()){
            return false;
        }

        if(appointmen.getDate().isBefore(LocalDate.now())){
             throw new AppointmentDateException("please enter correct date");
        }

        appointmen.setCustomer(currentCustomer.get());
        appointmen.setPetClinic(currentPetClinic.get());
        appointmentRepository.save(appointmen);

        return true;
    }

    public boolean cancelAppointment(Integer appointmentId, Integer customerId){
        Optional<Appointment> appointment= appointmentRepository.findAppointmentByIdAndCustomer_Id(appointmentId,customerId);
        if (!appointment.isPresent()){
            return false;
        }

        appointmentRepository.deleteById(appointmentId);
        return true;
    }

    public List<Appointment> getAllAppointmentByClinicId(Integer clinic_id) {
        return appointmentRepository.findAppointmentByPetClinic_Id(clinic_id);
    }

    public List<Appointment> getAllAppointmentByCustomerId(Integer customer_id) {
        return appointmentRepository.findAllByCustomer_Id(customer_id);
    }
}
