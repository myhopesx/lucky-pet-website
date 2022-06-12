package com.example.luckypetwebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "must be valid email")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "phone number  cannot be empty")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)[013456789][0-9]{7}$",message = "invalid phone Number")
    @Column(unique = true)
    private String phoneNumber;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 5)
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 6,max = 30 ,message = "Name should be more than 6 and max 20 characters")
    private String name;

    @PositiveOrZero(message = "age should be number")
    @Min(value = 18,message = "age must be more than 18")
    @Max(value = 100,message = "age must be less than 100")
    private Integer age;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pet> pets;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getAuthorities();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
