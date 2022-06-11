package com.example.luckypetwebsite.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class PetClinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "time cannot be empty")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "time cannot be empty")
    private LocalTime endTime;

    @NotEmpty(message = "description cannot be empty")
    private String description;

    @NotNull
    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="owner_id")
    @JsonIgnore
    private ClinicOwner clinicOwner;

    @OneToMany(mappedBy = "petClinic",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointmentList;
}
