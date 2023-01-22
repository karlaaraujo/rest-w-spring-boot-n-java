package br.com.karla.personapi.services;

import br.com.karla.personapi.exceptions.ResourceNotFoundException;
import br.com.karla.personapi.model.Person;
import br.com.karla.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service // Spring trata como objeto que será injetado em runtime em outros objetos
public class PersonService {

    private final AtomicLong counter = new AtomicLong(); // Mocka ids
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public Person findById(long id){
        logger.info("Finding one person.");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for provided ID."));
    }

    public List<Person> findAll(){
        logger.info("Finding all people.");

        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Creating person.");

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating person.");

        var savedPerson = findById(person.getId());

        savedPerson.setFirstName(person.getFirstName());
        savedPerson.setLastName(person.getLastName());
        savedPerson.setAddress(person.getAddress());
        savedPerson.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(long id){

        logger.info("Deleting person " + id);

        var person = findById(id);

        repository.delete(person);
    }

}
