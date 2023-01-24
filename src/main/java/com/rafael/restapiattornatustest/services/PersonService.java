package com.rafael.restapiattornatustest.services;

import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
import com.rafael.restapiattornatustest.models.entities.Person;
import com.rafael.restapiattornatustest.models.forms.PersonForm;
import com.rafael.restapiattornatustest.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PersonRepository personRepository;

    @Transactional
    public PersonDTO savePerson(PersonForm personForm) {
        Person newPerson = modelMapper.map(personForm, Person.class);
        Person savedPerson = personRepository.save(newPerson);
        return modelMapper.map(savedPerson, PersonDTO.class);
    }
}
