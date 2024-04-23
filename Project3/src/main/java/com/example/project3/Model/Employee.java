package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    private Integer empId;

    @Column(columnDefinition = "varchar(40) not null")
    private String position;

    @Column(columnDefinition = "int not null")
    private Integer salary;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;
}
