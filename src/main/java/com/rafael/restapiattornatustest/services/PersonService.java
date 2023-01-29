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
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    public PersonService() {
    }

    @Transactional
    public PersonDTO save(PersonForm personForm) {
        Person newPerson = modelMapper.map(personForm, Person.class);
        Person savedPerson = personRepository.save(newPerson);
        return modelMapper.map(savedPerson, PersonDTO.class);
    }

    public PersonDTO findById(UUID personId) {
        Person recoveryPerson = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return modelMapper.map(recoveryPerson, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        if(personList.isEmpty()) throw new EntityNotFoundException("Person list is empty");
        return personList.stream().map(person -> modelMapper.map(person, PersonDTO.class)).toList();
    }

    @Transactional
    public PersonDTO update(UUID personId, PersonForm personForm) {
        Person personToUpdate = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        modelMapper.map(personForm, personToUpdate);

        Person updatedPerson = personRepository.save(personToUpdate);
        return modelMapper.map(updatedPerson, PersonDTO.class);
    }

    @Transactional
    public AddressDTO savePersonAddress(UUID personId, AddressForm addressForm) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        Address newAddress = modelMapper.map(addressForm, Address.class);

        //Apenas o primeiro endereço adicionado será o principal
        newAddress.setIsMainAddress(person.getAddressList().isEmpty());

        newAddress.setPerson(person);

        Address savedAddress = addressRepository.save(newAddress);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    public PersonAddressesDTO listPersonAddresses(UUID personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return modelMapper.map(person, PersonAddressesDTO.class);
    }

    public AddressDTO changeMainAddress(UUID personId, UUID addressId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        Address newMainAddress = person.getAddressList().stream()
                .filter(address -> address.getId().equals(addressId))
                .findFirst()
                .orElseThrow( () -> new EntityNotFoundException("Address not found"));

        //O endereço com o id igual ao fornecido será o principal e os outros não.
        person.getAddressList().forEach(address -> address.setIsMainAddress(address.getId().equals(addressId)));

        personRepository.save(person);

        return modelMapper.map(newMainAddress, AddressDTO.class);

    }

}