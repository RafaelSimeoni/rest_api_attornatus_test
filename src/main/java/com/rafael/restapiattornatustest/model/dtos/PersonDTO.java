package com.rafael.restapiattornatustest.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonDTO {
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private List<AddressDTO> addressList = new ArrayList<>();

}
