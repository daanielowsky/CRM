package com.daanielowsky.crm.DTO;

import com.daanielowsky.crm.Entities.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {

//    @NotBlank(message = "This field can not be empty!")
    // TODO ogarnąć to
    private String title;

    @NotBlank(message = "This field can not be empty!")
    private String name;

    @NotBlank(message = "This field can not be empty!")
    private String surname;

    @Email(message = "Invalid email address")
    @NotBlank(message = "This field can not be empty")
    private String email;

    @NotNull(message = "This field can not be empty")
    private Long phoneNumber;

    @NotBlank(message = "This field can not be empty")
    private String city;

    @NotBlank(message = "This field can not be empty")
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Wrong format. Correct format is XX-XXX")
    private String postCode;

    private String note;

//    @NotNull
    private Employee employee;

}
