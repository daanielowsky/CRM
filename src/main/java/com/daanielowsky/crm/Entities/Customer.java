package com.daanielowsky.crm.Entities;


import com.daanielowsky.crm.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String name;

    private String surname;

    private String email;

    private Long phoneNumber;

    private String city;

    private String postCode;

    private String note;

    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Activity> activities = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
