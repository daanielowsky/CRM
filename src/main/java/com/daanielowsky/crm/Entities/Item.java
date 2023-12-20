package com.daanielowsky.crm.Entities;

import com.daanielowsky.crm.Enums.Producers;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Producers producer;

    private String name;

    private String description;

    private String descriptionForOffer;

    private BigDecimal price;

    private Long quantityOnStock;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
}
