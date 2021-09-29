package com.jean.springframework.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.springframework.domain.Person;
import com.jean.springframework.dto.MessageResponseDTO;
import com.jean.springframework.repositories.PersonRepository;
import com.jean.springframework.services.exception.ObjectNotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person findById(Long id) {
		Optional<Person> obj = personRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Person.class.getName()));
	}

	public MessageResponseDTO createPerson(Person person) {
		person.setId(null);
		person = personRepository.save(person);

		return MessageResponseDTO.builder().message("Create person with ID " + person.getId()).build();
	}
}
