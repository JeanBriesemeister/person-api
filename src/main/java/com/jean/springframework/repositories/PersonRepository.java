package com.jean.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jean.springframework.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
