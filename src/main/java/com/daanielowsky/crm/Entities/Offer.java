package com.daanielowsky.crm.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateGenerated;

    private long employeeId;

    private long customerId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer")
    private List<Item> itemList;

    private BigDecimal nettoPrice;

    private BigDecimal tax;

    private BigDecimal grossPrice;

    @PrePersist
    public void dateGeneratedAndOfferNameGenerator(){
        this.name = "O" + LocalDate.now().getYear() + "/";
        this.dateGenerated = LocalDate.now();
    }

}
