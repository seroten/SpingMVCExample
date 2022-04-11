package org.example.models;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;

    @NotEmpty(message = "Name should`t be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 14, message = "Age should be greater than 14")
    @Max(value = 120, message = "Age should be smaller than 120")
    private int age;

    @NotEmpty(message = "Email should`t be empty")
    @Email(message = "Email isn`t valid")
    private String email;
}
