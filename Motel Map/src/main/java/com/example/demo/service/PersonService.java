package com.example.demo.service;

import com.example.demo.deo.PersonDeo;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonDeo personDeo;

    @Autowired
    public PersonService(@Qualifier("mySQLPerson") PersonDeo personDeo) {
        this.personDeo = personDeo;
    }

    public void addPerson(Person person) {
        personDeo.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDeo.selectAllPeople();
    }

    public Optional<Person> selectPersonById(int id) {
        return personDeo.selectPersonById(id);
    }

    public int deletePersonById(int id) {
        return personDeo.deletePersonById(id);
    }

    public int updatePersonById(int id, Person person) {
        return personDeo.updatePersonById(id, person);
    }
}
