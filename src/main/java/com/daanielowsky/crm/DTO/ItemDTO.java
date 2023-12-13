package com.daanielowsky.crm.DTO;

import com.daanielowsky.crm.Enums.Producers;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDTO {

    @NotNull(message = "Uzupełnij producenta!")
    private Producers producer;

    @NotBlank(message = "Uzupełnij nazwę produktu!")
    private String name;

    @NotBlank(message = "Uzupełnij opis!")
    private String description;

    @NotBlank(message = "Uzupełnij opis na ofertę!")
    private String descriptionForOffer;

    @NotNull(message = "Uzupełnij cenę netto!")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @NotNull(message = "Uzupełnij ilość na stanie!")
    private Long quantityOnStock;
}
