package com.rafael.restapiattornatustest.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressDTO {
    private UUID id;
    private String publicPlace;
    private String zipCode;
    private Integer number;
    private String city;
    private Boolean isMainAddress;
}
