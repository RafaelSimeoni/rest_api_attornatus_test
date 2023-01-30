package com.rafael.restapiattornatustest.services;

import com.rafael.restapiattornatustest.exceptions.EntityNotFoundException;
import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
import com.rafael.restapiattornatustest.models.entities.Person;
import com.rafael.restapiattornatustest.models.forms.PersonForm;
import com.rafael.restapiattornatustest.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceTest {

    public static final UUID PERSON_UUID = UUID.fromString("b2761dc7-b7bf-4983-bd3c-9731413347cb");
    public static final String PERSON_NAME = "Rafael";
    public static final LocalDate PERSON_BIRTHDATE = LocalDate.of(1997, 2, 28);

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Spy
    private ModelMapper modelMapper;

    private Person person;
    private PersonForm personForm;
    private PersonDTO personDTO;
    private List<Person> personList = new ArrayList<>();
    private Optional<Person> optionalPerson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    @Test
    void whenSaveThenReturnSuccess(){
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        PersonDTO response = personService.save(personForm);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertEquals(PERSON_UUID, response.getId());
        assertEquals(PERSON_NAME, response.getName());
        assertEquals(PERSON_BIRTHDATE, response.getBirthDate());

    }

    @Test
    void whenFindByIdThenReturnAnPersonInstance(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(optionalPerson);

        try {
            PersonDTO response = personService.findById(PERSON_UUID);
            assertNotNull(response);
            assertEquals(PersonDTO.class, response.getClass());
            assertEquals(PERSON_UUID, response.getId());
            assertEquals(PERSON_NAME, response.getName());
            assertEquals(PERSON_BIRTHDATE, response.getBirthDate());
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }

    }

    @Test
    void whenFindByIdThenReturnAnEntityNotFoundException(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(Optional.empty());

        try {
            PersonDTO response = personService.findById(PERSON_UUID);
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertEquals(EntityNotFoundException.class, e.getClass());
            assertEquals("Person not found", e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnPersonList(){
        when(personRepository.findAll()).thenReturn(personList);

        List<PersonDTO> response = personService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());

        assertEquals(PersonDTO.class, response.get(0).getClass());
        assertEquals(PERSON_UUID, response.get(0).getId());
        assertEquals(PERSON_NAME, response.get(0).getName());
        assertEquals(PERSON_BIRTHDATE, response.get(0).getBirthDate());

    }

    @Test
    void whenUpdateThenReturnSuccess(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(optionalPerson);
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        try {
            PersonDTO response = personService.update(PERSON_UUID, personForm);

            assertNotNull(response);
            assertEquals(PersonDTO.class, response.getClass());
            assertEquals(PERSON_UUID, response.getId());
            assertEquals(PERSON_NAME, response.getName());
            assertEquals(PERSON_BIRTHDATE, response.getBirthDate());
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }

    }

    @Test
    void whenUpdateThenReturnEntityNotFoundException(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(Optional.empty());

        try {
            PersonDTO response = personService.update(PERSON_UUID, personForm);
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertEquals(EntityNotFoundException.class, e.getClass());
            assertEquals("Person not found", e.getMessage());
        }
    }

    @Test
    void savePersonAddress(){}

    @Test
    void listPersonAddresses(){}

    @Test
    void changeMainAddress(){}

    private void startPerson() {
        person = new Person();
        person.setId(PERSON_UUID);
        person.setName(PERSON_NAME);
        person.setBirthDate(PERSON_BIRTHDATE);

        personForm = new PersonForm();
        personForm.setName(PERSON_NAME);
        personForm.setBirthDate(PERSON_BIRTHDATE);

        personDTO = new PersonDTO();
        personDTO.setId(PERSON_UUID);
        personDTO.setName(PERSON_NAME);
        personDTO.setBirthDate(PERSON_BIRTHDATE);

        optionalPerson = Optional.of(person);

        personList.add(person);

    }


}
