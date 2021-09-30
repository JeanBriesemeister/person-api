package com.jean.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.springframework.domain.Person;
import com.jean.springframework.dto.MessageResponseDTO;
import com.jean.springframework.dto.PersonDTO;
import com.jean.springframework.mapper.PersonMapper;
import com.jean.springframework.repositories.PersonRepository;
import com.jean.springframework.services.exception.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

	private PersonRepository personRepository;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	public PersonDTO findById(Long id) {
		Optional<Person> obj = personRepository.findById(id);

		return personMapper.toDTO(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Class: " + Person.class.getName())));
	}

	public MessageResponseDTO create(PersonDTO personDTO) {
		Person savedPerson = personRepository.save(personMapper.toModel(personDTO));

		return createMessageResponse(savedPerson.getId(), "Created person with ID ");
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();

		return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	public void deleteById(Long id) {
		personRepository.deleteById(findById(id).getId());
	}

	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) {
		PersonDTO personDTOPersisted = findById(id);
		personDTO.setId(personDTOPersisted.getId());

		Person updatedPerson = personRepository.save(personMapper.toModel(personDTO));

		return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
	}

	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO.builder().message(message + id).build();
	}
}
