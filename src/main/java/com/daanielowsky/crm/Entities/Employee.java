package com.daanielowsky.crm.Entities;

import com.daanielowsky.crm.Enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Employee {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private Long phoneNumber;

    private String email;

    private Roles roles;

    private BigDecimal salary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Customer> customerList;

    private LocalDate dateOfBirth;

    private LocalDate dateOfEmployment;


    @PrePersist
    public void setDateOfEmployment(){
        this.dateOfEmployment = LocalDate.now();
    }
}
