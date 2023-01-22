package br.com.karla.personapi.controllers;

import br.com.karla.personapi.model.Person;
import br.com.karla.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping( "/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    ) // Swagger exige que 'produces' seja mantido para documentar efetivamente
    public Person findById(@PathVariable(value = "id") Long id) throws Exception {

        return service.findById(id);
    }

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> findAll() throws Exception {
        return service.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) throws Exception {
        return service.create(person);
    }


    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person) throws Exception {
        return service.update(person);
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
    }



}