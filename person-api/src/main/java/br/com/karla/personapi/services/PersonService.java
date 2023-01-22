package br.com.karla.personapi.services;

import br.com.karla.personapi.model.Person;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service // Spring trata como objeto que será injetado em runtime em outros objetos
public class PersonService {

    private final AtomicLong counter = new AtomicLong(); // Mocka ids
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person.");

        return mockPerson(counter.incrementAndGet());
    }

    public List<Person> findAll(){
        logger.info("Finding all people.");

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 9 ; i++){
            people.add(mockPerson(i));
        }

        return people;
    }

    public Person create(Person person){
        logger.info("Creating person.");

        return person;
    }

    public Person update(Person person){
        logger.info("Updating person " + person.getId());

        return person;
    }

    public void delete(String id){
        logger.info("Deleting person " + id);
    }

    private Person mockPerson(long id){

        Person person = new Person();
        person.setId(id);
        person.setFirstName("First name " + id);
        person.setLastName("Last name " + id);
        person.setAddress("State " + id);
        person.setGender("Female");

        return person;
    }


}
