package com.example.luckypetwebsite.model;


import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "type cannot empty")
    private String type;

    @NotNull(message = "age cannot be empty")
    @PositiveOrZero(message = "age should be number")
    private Integer age;

    @NotEmpty(message = "description cannot be empty")
    private String description;

    @NotNull
    @Pattern(regexp = "(owned|offered|lost)" ,message = "status should be owned|offered|lost")
    private String status;

    private String image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private Customer customer;

}
