package com.rafael.restapiattornatustest.controllers;

import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
import com.rafael.restapiattornatustest.models.forms.PersonForm;
import com.rafael.restapiattornatustest.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> savePerson(@RequestBody @Valid PersonForm personForm) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.savePerson(personForm));
    }
}
