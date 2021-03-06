package com.jean.springframework.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jean.springframework.dto.MessageResponseDTO;
import com.jean.springframework.dto.PersonDTO;
import com.jean.springframework.services.PersonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonResource {

	private PersonService personService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO create(@RequestBody @Valid PersonDTO personDTO) {
		return personService.create(personDTO);
	}

	@GetMapping
	public List<PersonDTO> listAll() {
		return personService.listAll();
	}

	@GetMapping("/{id}")
	public PersonDTO findById(@PathVariable Long id) {
		return personService.findById(id);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) {
		return personService.updateById(id, personDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		personService.deleteById(id);
	}
}
