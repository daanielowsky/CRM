package com.daanielowsky.crm.DTO;

import com.daanielowsky.crm.Entities.Item;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OfferDTO {

    @NotEmpty
    private List<Item> itemList;

    @NotEmpty
    private long customerId;

    @NotEmpty
    private long employeeId;

}
