package com.rafael.restapiattornatustest.controllers;

import com.rafael.restapiattornatustest.models.dtos.AddressDTO;
import com.rafael.restapiattornatustest.models.dtos.PersonAddressesDTO;
import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
import com.rafael.restapiattornatustest.models.forms.AddressForm;
import com.rafael.restapiattornatustest.models.forms.PersonForm;
import com.rafael.restapiattornatustest.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody @Valid PersonForm personForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personForm));
    }

   @GetMapping("/{personId}")
   public ResponseEntity<PersonDTO> findById(@PathVariable UUID personId) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(personId));
   }

   @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
   }

   @PutMapping("/{personId}")
    public ResponseEntity<PersonDTO> update(
            @PathVariable UUID personId, @RequestBody @Valid PersonForm personForm) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.update(personId, personForm));
   }

    @PostMapping("/address/{personId}")
    public ResponseEntity<AddressDTO> savePersonAddress(
            @PathVariable UUID personId, @RequestBody @Valid AddressForm addressForm) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.savePersonAddress(personId, addressForm));
    }

    @GetMapping("/address/{personId}")
    public ResponseEntity<PersonAddressesDTO> listPersonAddresses(@PathVariable UUID personId) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.listPersonAddresses(personId));
    }

}
