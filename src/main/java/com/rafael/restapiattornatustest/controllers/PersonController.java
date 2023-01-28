package com.rafael.restapiattornatustest.controllers;

import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
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
    public ResponseEntity<PersonDTO> savePerson(@RequestBody @Valid PersonForm personForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(personForm));
    }

   @GetMapping("/{personId}")
   public ResponseEntity<PersonDTO> findPersonById(@PathVariable UUID personId) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findPersonById(personId));
   }

   @GetMapping
    public ResponseEntity<List<PersonDTO>> findAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAllPersons());
   }

}
