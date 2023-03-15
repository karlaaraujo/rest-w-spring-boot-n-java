package br.com.karla.personapi.services;

import br.com.karla.personapi.exceptions.ResourceNotFoundException;
import br.com.karla.personapi.data.vo.v1.PersonVO;
import br.com.karla.personapi.mapper.Mapper;
import br.com.karla.personapi.model.Person;
import br.com.karla.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service // Spring trata como objeto que será injetado em runtime em outros objetos
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(long id){
        logger.info("Finding one person.");

        var entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for provided ID."));


        return Mapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        logger.info("Finding all people.");

        return Mapper.parseObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating person.");

        var entity = Mapper.parseObject(person, Person.class);

        return Mapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        logger.info("Updating person.");

        var entity = Mapper.parseObject(findById(person.getId()), Person.class);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return Mapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(long id){

        logger.info("Deleting person " + id);

        var person = Mapper.parseObject(findById(id), Person.class);

        repository.delete(person);
    }

}
