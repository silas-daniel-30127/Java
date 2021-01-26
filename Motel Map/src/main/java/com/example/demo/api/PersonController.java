package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @NotBlank @RequestBody Person person) {
        this.personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person selectPersonById(@PathVariable("id") int id) {
        return personService.selectPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deletePersonById(@PathVariable("id") int id) {
        return personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public int updatePersonById(@PathVariable("id") int id, @Valid @NonNull @RequestBody Person person) {
        return personService.updatePersonById(id, person);
    }
}
