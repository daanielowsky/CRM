package com.daanielowsky.crm.DTO;

import com.daanielowsky.crm.Enums.Roles;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDTO {

    @NotBlank (message = "This field can not be empty!")
    private String name;

    @NotBlank (message = "This field can not be empty!")
    private String surname;

    @NotNull (message = "This field can not be empty!")
    private Long phoneNumber;

    @Email (message = "Invalid email adress")
    @NotBlank (message = "This field can not be empty!")
    private String email;

    @NotNull (message = "This field can not be empty!")
    private Roles roles;

    @Digits(integer = 10, fraction = 2)
    @NotNull (message = "This field can not be empty!")
    private BigDecimal salary;

    private LocalDate dateOfBirth;
}
