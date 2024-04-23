package com.example.project3.SecurityConfig;

import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                //ADMIN
                .requestMatchers("/api/v1/auth/get-all","/api/v1/customer/get-all",
                        "/api/v1/customer/delete/{customerId}","/api/v1/employee/get-all",
                        "/api/v1/employee/delete/{employeeId}","/api/v1/account/get-all").hasAuthority("ADMIN")

                //ALL
                .requestMatchers("/api/v1/auth/login","/api/v1/auth/logout","/api/v1/customer/register-customer","/api/v1/employee/register-employee").permitAll()

                //CUSTOMER
                .requestMatchers("/api/v1/customer/update","/api/v1/account/create-account","/api/v1/account/update/{accId}",
                        "/api/v1/account/delete/{accId}","/api/v1/account/get",
                        "/api/v1/account/deposit/{accId}/{amount}","/api/v1/account/view-details/{accId}",
                        "/api/v1/account/withdraw/{accId}/{amount}","/api/v1/account/transfer/{acc1Id}/{acc2Id}/{amount}").hasAuthority("CUSTOMER")

                //EMPLOYEE
                .requestMatchers("/api/v1/employee/update","/api/v1/account/activate/{accId}","/api/v1/account/block/{accId}").hasAuthority("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
