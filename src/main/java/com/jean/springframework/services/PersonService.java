package com.jean.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.springframework.domain.Person;
import com.jean.springframework.dto.MessageResponseDTO;
import com.jean.springframework.dto.PersonDTO;
import com.jean.springframework.mapper.PersonMapper;
import com.jean.springframework.repositories.PersonRepository;
import com.jean.springframework.services.exception.ObjectNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	public Person findById(Long id) {
		Optional<Person> obj = personRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Person.class.getName()));
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person savedPerson = personRepository.save(personMapper.toModel(personDTO));

		return MessageResponseDTO.builder().message("Create person with ID " + savedPerson.getId()).build();
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();

		return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}
}
