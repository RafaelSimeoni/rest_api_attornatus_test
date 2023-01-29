package com.rafael.restapiattornatustest.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonAddressesDTO {
    private UUID id;
    private String name;
    private List<AddressDTO> addressList = new ArrayList<>();
}
