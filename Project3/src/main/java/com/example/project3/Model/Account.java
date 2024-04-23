package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accId;

    @NotEmpty(message = "account number cannot be null")
    @Pattern(regexp = "^\\w{4}-\\w{4}-\\w{4}-\\w{4}$")
    @Column(columnDefinition = "varchar(20) not null")
    private String accountNumber;

    @NotNull(message = "balance cannot be null")
    @Positive
    @Column(columnDefinition = "double not null")
    private Double balance;

    
    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private Customer customer;
}