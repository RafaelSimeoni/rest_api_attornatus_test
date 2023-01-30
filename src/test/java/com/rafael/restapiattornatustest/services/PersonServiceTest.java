package com.rafael.restapiattornatustest.services;

import com.rafael.restapiattornatustest.exceptions.EntityNotFoundException;
import com.rafael.restapiattornatustest.models.dtos.AddressDTO;
import com.rafael.restapiattornatustest.models.dtos.PersonAddressesDTO;
import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
import com.rafael.restapiattornatustest.models.entities.Address;
import com.rafael.restapiattornatustest.models.entities.Person;
import com.rafael.restapiattornatustest.models.forms.AddressForm;
import com.rafael.restapiattornatustest.models.forms.PersonForm;
import com.rafael.restapiattornatustest.repositories.AddressRepository;
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
    public static final UUID ADDRESS_UUID = UUID.fromString("6fa65ef3-6781-45d3-97a7-3da81f18196c");
    private static final String ADDRESS_CITY = "Curitiba";
    private static final String ADDRESS_ZIP_CODE = "81015-123";
    private static final String ADDRESS_PUBLIC_PLACE = "Rua das Flores";
    private static final Integer ADDRESS_NUMBER = 123;
    public static final UUID ADDRESS2_UUID = UUID.fromString("3d447dc8-ad22-4592-b788-a7dc21ce5612");
    private static final String ADDRESS2_CITY = "São Paulo";
    private static final String ADDRESS2_ZIP_CODE = "81010-124";
    private static final String ADDRESS2_PUBLIC_PLACE = "Rua Do Ipiranga";
    private static final Integer ADDRESS2_NUMBER = 168;


    private Person person;
    private PersonForm personForm;
    private PersonDTO personDTO;
    private List<Person> personList = new ArrayList<>();
    private Optional<Person> optionalPerson;

    private Address address, address2;
    private AddressForm addressForm;

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private AddressRepository addressRepository;

    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAttributes();
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
    void whenFindByIdThenReturnAnPersonDTOInstance(){
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
    void whenFindAllThenReturnAnPersonDTOList(){
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
    void whenSavePersonAddressThenReturnAnAddressDTOInstance(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(optionalPerson);
        when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        try {
            AddressDTO response = personService.savePersonAddress(PERSON_UUID, addressForm);

            assertNotNull(response);
            assertEquals(AddressDTO.class, response.getClass());
            assertEquals(ADDRESS_UUID, response.getId());
            assertEquals(ADDRESS_CITY, response.getCity());
            assertEquals(ADDRESS_PUBLIC_PLACE, response.getPublicPlace());
            assertEquals(ADDRESS_ZIP_CODE, response.getZipCode());
            assertEquals(ADDRESS_NUMBER, response.getNumber());
            assertEquals(true, response.getIsMainAddress());

        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }
    }

    @Test
    void whenSavePersonAddressThenReturnAnEntityNotFoundException(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(Optional.empty());

        try {
            AddressDTO response = personService.savePersonAddress(PERSON_UUID, addressForm);
            fail("Expected exception was not thrown");

        } catch (Exception e) {
            assertEquals(EntityNotFoundException.class, e.getClass());
            assertEquals("Person not found", e.getMessage());
        }

    }

    @Test
     void whenlistPersonAddressesThenReturnAnPersonAddressesDTO(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(optionalPerson);

        try {
            PersonAddressesDTO response = personService.listPersonAddresses(PERSON_UUID);

            assertNotNull(response);
            assertEquals(PersonAddressesDTO.class, response.getClass());
            assertEquals(PERSON_UUID, response.getId());
            assertEquals(PERSON_NAME, response.getName());
            assertEquals(1, response.getAddressList().size());
            assertEquals(ADDRESS_UUID, response.getAddressList().get(0).getId());
            assertEquals(ADDRESS_NUMBER, response.getAddressList().get(0).getNumber());
            assertEquals(ADDRESS_CITY, response.getAddressList().get(0).getCity());
            assertEquals(ADDRESS_PUBLIC_PLACE, response.getAddressList().get(0).getPublicPlace());
            assertEquals(ADDRESS_ZIP_CODE, response.getAddressList().get(0).getZipCode());

        }catch (Exception e) {
            fail("Unexpected exception was thrown");
        }
    }

    @Test
    void whenlistPersonAddressesThenReturnAnEntityNotFoundException(){
        when(personRepository.findById(PERSON_UUID)).thenReturn(Optional.empty());

        try {
            PersonAddressesDTO response = personService.listPersonAddresses(PERSON_UUID);
            fail("Expected exception was not thrown");

        } catch (Exception e) {
            assertEquals(EntityNotFoundException.class, e.getClass());
            assertEquals("Person not found", e.getMessage());
        }
    }

    @Test
    void changeMainAddress(){}

    private void startAttributes() {
        addressForm = new AddressForm();
        addressForm.setCity(ADDRESS_CITY);
        addressForm.setZipCode(ADDRESS_ZIP_CODE);
        addressForm.setPublicPlace(ADDRESS_PUBLIC_PLACE);
        addressForm.setNumber(ADDRESS_NUMBER);

        address = new Address();
        address.setId(ADDRESS_UUID);
        address.setCity(ADDRESS_CITY);
        address.setZipCode(ADDRESS_ZIP_CODE);
        address.setPublicPlace(ADDRESS_PUBLIC_PLACE);
        address.setNumber(ADDRESS_NUMBER);
        address.setIsMainAddress(true);

        address2 = new Address();
        address2.setId(ADDRESS2_UUID);
        address2.setCity(ADDRESS2_CITY);
        address2.setZipCode(ADDRESS2_ZIP_CODE);
        address2.setPublicPlace(ADDRESS2_PUBLIC_PLACE);
        address2.setNumber(ADDRESS2_NUMBER);
        address2.setIsMainAddress(false);

        person = new Person();
        person.setId(PERSON_UUID);
        person.setName(PERSON_NAME);
        person.setBirthDate(PERSON_BIRTHDATE);
        person.addAddress(address);

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
