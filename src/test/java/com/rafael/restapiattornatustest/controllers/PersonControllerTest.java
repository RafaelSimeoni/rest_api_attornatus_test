package com.rafael.restapiattornatustest.controllers;

import com.rafael.restapiattornatustest.models.dtos.AddressDTO;
import com.rafael.restapiattornatustest.models.dtos.PersonAddressesDTO;
import com.rafael.restapiattornatustest.models.dtos.PersonDTO;
import com.rafael.restapiattornatustest.models.forms.AddressForm;
import com.rafael.restapiattornatustest.models.forms.PersonForm;
import com.rafael.restapiattornatustest.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonControllerTest {

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

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    private PersonForm personForm;
    private PersonDTO personDTO;
    private AddressForm addressForm;
    private AddressDTO addressDTO, addressDTO2;
    private List<PersonDTO> personDTOList = new ArrayList<>();
    private List<AddressDTO> addressDTOList = new ArrayList<>();
    private PersonAddressesDTO personAddressesDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAttributes();
    }

    @Test
    void whenSaveThenReturnCreatedAndPersonDTOInstance(){
        when(personService.save(personForm)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personController.save(personForm);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(PersonDTO.class, response.getBody().getClass());
        assertEquals(PERSON_UUID, response.getBody().getId());
        assertEquals(PERSON_NAME, response.getBody().getName());
        assertEquals(PERSON_BIRTHDATE, response.getBody().getBirthDate());
    }

    @Test
    void whenFindByIdThenReturnOkAndPersonDTOInstance(){
        when(personService.findById(PERSON_UUID)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personController.findById(PERSON_UUID);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(PersonDTO.class, response.getBody().getClass());
        assertEquals(PERSON_UUID, response.getBody().getId());
        assertEquals(PERSON_NAME, response.getBody().getName());
        assertEquals(PERSON_BIRTHDATE, response.getBody().getBirthDate());
    }

    @Test
    void whenFindAllThenReturnOkAndAListOfPersonDTO(){
        when(personService.findAll()).thenReturn(personDTOList);

        ResponseEntity<List<PersonDTO>> response = personController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(1, response.getBody().size());
        assertEquals(PERSON_UUID, response.getBody().get(0).getId());
        assertEquals(PERSON_NAME, response.getBody().get(0).getName());
        assertEquals(PERSON_BIRTHDATE, response.getBody().get(0).getBirthDate());
    }

    @Test
    void whenUpdateThenReturnOkAndPersonDTOInstance(){
        when(personService.update(PERSON_UUID, personForm)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personController.update(PERSON_UUID, personForm);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(PersonDTO.class, response.getBody().getClass());
        assertEquals(PERSON_UUID, response.getBody().getId());
        assertEquals(PERSON_NAME, response.getBody().getName());
        assertEquals(PERSON_BIRTHDATE, response.getBody().getBirthDate());
    }

    @Test
    void whenSavePersonAddressThenReturnCreatedAndAddressDTOInstance(){
        when(personService.savePersonAddress(PERSON_UUID, addressForm)).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> response = personController.savePersonAddress(PERSON_UUID, addressForm);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(AddressDTO.class, response.getBody().getClass());
        assertEquals(ADDRESS_UUID, response.getBody().getId());
        assertEquals(ADDRESS_CITY, response.getBody().getCity());
        assertEquals(ADDRESS_PUBLIC_PLACE, response.getBody().getPublicPlace());
        assertEquals(ADDRESS_ZIP_CODE, response.getBody().getZipCode());
        assertEquals(ADDRESS_NUMBER, response.getBody().getNumber());

    }

    @Test
    void whenListPersonAddressesThenReturnOkAndPersonAddressesDTOInstance(){
        when(personService.listPersonAddresses(PERSON_UUID)).thenReturn(personAddressesDTO);

        ResponseEntity<PersonAddressesDTO> response = personController.listPersonAddresses(PERSON_UUID);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(PersonAddressesDTO.class, response.getBody().getClass());
        assertEquals(2, response.getBody().getAddressList().size());
        assertEquals(PERSON_UUID, response.getBody().getId());
        assertEquals(PERSON_NAME, response.getBody().getName());
        assertEquals(ADDRESS_UUID, response.getBody().getAddressList().get(0).getId());
        assertEquals(ADDRESS_CITY, response.getBody().getAddressList().get(0).getCity());
        assertEquals(ADDRESS_PUBLIC_PLACE, response.getBody().getAddressList().get(0).getPublicPlace());
        assertEquals(ADDRESS_ZIP_CODE, response.getBody().getAddressList().get(0).getZipCode());
        assertEquals(ADDRESS_NUMBER, response.getBody().getAddressList().get(0).getNumber());

    }

    @Test
    void whenChangeMainAddressThenReturnOkAndddressDTOInstance(){
        when(personService.changeMainAddress(PERSON_UUID, ADDRESS_UUID)).thenReturn(addressDTO);

        ResponseEntity<AddressDTO> response = personController.changeMainAddress(PERSON_UUID, ADDRESS_UUID);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(AddressDTO.class, response.getBody().getClass());
        assertEquals(ADDRESS_UUID, response.getBody().getId());
        assertEquals(ADDRESS_CITY, response.getBody().getCity());
        assertEquals(ADDRESS_PUBLIC_PLACE, response.getBody().getPublicPlace());
        assertEquals(ADDRESS_ZIP_CODE, response.getBody().getZipCode());
        assertEquals(ADDRESS_NUMBER, response.getBody().getNumber());
    }

    private void startAttributes() {
        addressForm = new AddressForm();
        addressForm.setCity(ADDRESS_CITY);
        addressForm.setZipCode(ADDRESS_ZIP_CODE);
        addressForm.setPublicPlace(ADDRESS_PUBLIC_PLACE);
        addressForm.setNumber(ADDRESS_NUMBER);

        addressDTO = new AddressDTO();
        addressDTO.setId(ADDRESS_UUID);
        addressDTO.setCity(ADDRESS_CITY);
        addressDTO.setZipCode(ADDRESS_ZIP_CODE);
        addressDTO.setPublicPlace(ADDRESS_PUBLIC_PLACE);
        addressDTO.setNumber(ADDRESS_NUMBER);

        addressDTO2 = new AddressDTO();
        addressDTO2.setId(ADDRESS2_UUID);
        addressDTO2.setCity(ADDRESS2_CITY);
        addressDTO2.setZipCode(ADDRESS2_ZIP_CODE);
        addressDTO2.setPublicPlace(ADDRESS2_PUBLIC_PLACE);
        addressDTO2.setNumber(ADDRESS2_NUMBER);

        personForm = new PersonForm();
        personForm.setName(PERSON_NAME);
        personForm.setBirthDate(PERSON_BIRTHDATE);

        personDTO = new PersonDTO();
        personDTO.setId(PERSON_UUID);
        personDTO.setName(PERSON_NAME);
        personDTO.setBirthDate(PERSON_BIRTHDATE);

        personDTOList.add(personDTO);

        addressDTOList.add(addressDTO);
        addressDTOList.add(addressDTO2);

        personAddressesDTO = new PersonAddressesDTO();
        personAddressesDTO.setId(PERSON_UUID);
        personAddressesDTO.setName(PERSON_NAME);
        personAddressesDTO.setAddressList(addressDTOList);
    }
}
