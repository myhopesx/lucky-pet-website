package com.example.luckypetwebsite.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "date cannot be empty")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "time cannot be empty")
    private LocalTime time;

    @PositiveOrZero(message = "please enter valid price")
    private Double price;

    @NotEmpty
    @Pattern(regexp = "(inoculation|scout)", message ="")
    private String appointmentType;

    @ManyToOne
    @JoinColumn(name="petClinic_id")
    private PetClinic petClinic;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
