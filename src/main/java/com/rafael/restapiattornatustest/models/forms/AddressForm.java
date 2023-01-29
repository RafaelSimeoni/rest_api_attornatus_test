package com.rafael.restapiattornatustest.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class AddressForm {
    @NotBlank(message = "Public place field cannot be empty")
    @Length(min = 3, max = 100, message = "Public place field must be between 3 and 100 characters")
    private String publicPlace;

    @NotBlank(message = "Zip code field cannot be empty")
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "Zip code field must have the format '99999-999'")
    private String zipCode;

    @NotNull(message = "Number field cannot be null")
    @Positive(message = "Number field must be a positive value")
    private Integer number;

    @NotBlank(message = "City field cannot be empty")
    @Length(min = 3, max = 100, message = "City field must be between 3 and 100 characters")
    private String city;
}
