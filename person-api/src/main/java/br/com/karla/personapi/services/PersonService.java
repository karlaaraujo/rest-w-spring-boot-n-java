package br.com.karla.personapi.services;

import br.com.karla.personapi.model.Person;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service // Spring trata como objeto que será injetado em runtime em outros objetos
public class PersonService {

    private final AtomicLong counter = new AtomicLong(); // Mocka ids
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person.");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Karla");
        person.setLastName("Araújo");
        person.setAddress("Maceió - AL");
        person.setGender("Female");

        return person;

    }


}
