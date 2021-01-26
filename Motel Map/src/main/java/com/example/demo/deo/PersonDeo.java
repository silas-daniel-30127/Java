package com.example.demo.deo;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface PersonDeo {
    void insertPerson(int id, Person person);

    default void insertPerson(Person person) {
        int id = new Random().nextInt(1000);
        insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(int id);

    int deletePersonById(int id);
    int updatePersonById(int id, Person person);
}
