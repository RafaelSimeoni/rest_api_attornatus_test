package com.rafael.restapiattornatustest.services;

import com.rafael.restapiattornatustest.exceptions.EntityNotFoundException;
import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
import com.rafael.restapiattornatustest.models.entities.Person;
import com.rafael.restapiattornatustest.models.forms.PersonForm;
import com.rafael.restapiattornatustest.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public PersonDTO findPersonById(UUID id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isEmpty()) throw new EntityNotFoundException("Person not found");
        return modelMapper.map(optionalPerson.get(), PersonDTO.class);
    }

    public List<PersonDTO> findAllPersons() {
        List<Person> personList = personRepository.findAll();
        if(personList.isEmpty()) throw new EntityNotFoundException("Person list is empty");
        return personList.stream().map(person -> modelMapper.map(person, PersonDTO.class)).toList();
    }
}