package com.daanielowsky.crm.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime localDateTime;

    private String message;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Activity(String message) {
        this.localDateTime = LocalDateTime.now();
        this.message = message;
    }
}
