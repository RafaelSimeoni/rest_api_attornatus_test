package com.rafael.restapiattornatustest.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter @Setter
public class PersonForm {
    @NotBlank(message = "Name field cannot be empty")
    @Length(min = 3, max = 100, message = "Name field must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "birth date field cannot be null")
    @Past(message = "birth date field must be in the past")
    private LocalDate birthDate;
}
