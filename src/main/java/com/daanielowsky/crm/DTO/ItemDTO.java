package com.daanielowsky.crm.DTO;

import com.daanielowsky.crm.Enums.Producers;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDTO {

    @NotBlank(message = "Uzupełnij producenta!")
    private Producers producer;

    @NotBlank(message = "Uzupełnij nazwę produktu!")
    private String name;

    @NotBlank(message = "Uzupełnij opis!")
    private String description;

    @NotBlank(message = "Uzupełnij opis na ofertę!")
    private String descriptionForOffer;

    @NotBlank(message = "Uzupełnij cenę netto!")
    private BigDecimal price;

    @NotBlank(message = "Uzupełnij ilość na stanie!")
    private Long quantityOnStock;
}
