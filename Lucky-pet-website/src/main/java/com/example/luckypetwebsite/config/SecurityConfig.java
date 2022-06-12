package com.example.luckypetwebsite.config;


import com.example.luckypetwebsite.security.AdminDetailsService;
import com.example.luckypetwebsite.security.ClinicOwnerDetailsService;
import com.example.luckypetwebsite.security.CustomerDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

     private final CustomerDetailsService customerDetailsService;
     private final ClinicOwnerDetailsService clinicOwnerDetailsService;
     private final AdminDetailsService adminDetailsService;

     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        spring load this service so i have
          auth.userDetailsService(customerDetailsService).passwordEncoder(new BCryptPasswordEncoder());
          auth.userDetailsService(clinicOwnerDetailsService).passwordEncoder(new BCryptPasswordEncoder());
          auth.userDetailsService(adminDetailsService).passwordEncoder(new BCryptPasswordEncoder());
     }

     protected void configure(HttpSecurity http) throws Exception {
          http.csrf().disable().authorizeRequests()

                  //admin auth
                  .antMatchers("/api/v1/admin/signup").permitAll()


                  // customer auth
                   .antMatchers("/api/v1/customer/signup").permitAll()
                   .antMatchers("/api/v1/customer/all").hasAuthority("admin")
                   .antMatchers("/api/v1/customer/**").hasAnyAuthority("customer", "admin")

                   // pet auth
                   .antMatchers("/api/v1/pet/all").hasAuthority("admin")
                   .antMatchers("/api/v1/pet/**").hasAuthority("customer")

                   // adaption auth
                   .antMatchers("/api/v1/adaption/all").permitAll()
                   .antMatchers("/api/v1/adaption/adopt/**").hasAuthority("customer")
                   .antMatchers("/api/v1/adaption/offered/**").hasAuthority("customer")

                   // lost-pet auth
                   .antMatchers("/api/v1/lost-pet/all").permitAll()
                   .antMatchers("/api/v1/lost-pet/lost/**").hasAuthority("customer")
                   .antMatchers("/api/v1/lost-pet/found/**").hasAuthority("customer")

                   // clinic owner auth
                   .antMatchers("/api/v1/clinic-owner/signup").permitAll()
                   .antMatchers("/api/v1/clinic-owner/all").hasAuthority("admin")
                   .antMatchers("/api/v1/clinic-owner/**").hasAnyAuthority("clinicOwner", "admin")

                   // pet-clinic auth
                   .antMatchers("/api/v1/pet-clinic/all").permitAll()
                   .antMatchers("/api/v1/pet-clinic/**").hasAnyAuthority("clinicOwner", "admin")

                   // appointment auth
                  .antMatchers("/api/v1/appointment/clinic-owner/**").hasAuthority("clinicOwner")//.hasAuthority("clinicOwner")
                   .antMatchers("/api/v1/appointment/customer/**").hasAuthority("customer")
                   .antMatchers("/api/v1/appointment/all").hasAuthority("admin")
                  //------------------------------------------------------------------------------

                  .anyRequest().authenticated()
                  .and().httpBasic();
//       to open the next paths
//       .antMatchers("/api/v1/register/**").permitAll()

     }
}
