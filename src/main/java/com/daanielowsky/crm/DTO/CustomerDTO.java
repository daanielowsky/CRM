package com.daanielowsky.crm.DTO;

import lombok.Data;

@Data
public class CustomerDTO {

    private String title;

    private String name;

    private String surname;

    private String email;

    private Long phoneNumber;

    private String city;

    private String postCode;

    private String notes;

}
