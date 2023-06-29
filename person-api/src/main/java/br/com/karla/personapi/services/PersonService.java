package br.com.karla.personapi.services;

import br.com.karla.personapi.controllers.PersonController;
import br.com.karla.personapi.data.vo.v1.PersonVO;
import br.com.karla.personapi.exceptions.ResourceNotFoundException;
import br.com.karla.personapi.mapper.Mapper;
import br.com.karla.personapi.model.Person;
import br.com.karla.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service // Spring trata como objeto que será injetado em runtime em outros objetos
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(long id){
        logger.info("Finding a person...");

        var entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for provided ID."));

        PersonVO vo = Mapper.parseObject(entity, PersonVO.class);

        vo.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).findById(id)
                ).withSelfRel()
        );

        return vo;
    }

    public List<PersonVO> findAll(){
        logger.info("Returning all people...");

        List<PersonVO> vos = Mapper.parseObjects(repository.findAll(), PersonVO.class);

        vos.stream().forEach(vo -> vo.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())
                ).withSelfRel()
        ));


        return vos;
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating a person...");

        var entity = Mapper.parseObject(person, Person.class);

        PersonVO vo = Mapper.parseObject(repository.save(entity), PersonVO.class);

        vo.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())
                ).withSelfRel()
        );

        return vo;
    }

    public br.com.karla.personapi.data.vo.v2.PersonVO create(br.com.karla.personapi.data.vo.v2.PersonVO person){
        logger.info("Creating a person...");

        var entity = Mapper.parseObject(person, Person.class);

        return Mapper.parseObject(repository.save(entity), br.com.karla.personapi.data.vo.v2.PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        logger.info("Updating a person...");

        var entity = Mapper.parseObject(findById(person.getKey()), Person.class);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = Mapper.parseObject(repository.save(entity), PersonVO.class);

        vo.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())
                ).withSelfRel()
        );

        return vo;
    }

    public void delete(long id){

        logger.info("Deleting person " + id);

        var person = Mapper.parseObject(findById(id), Person.class);

        repository.delete(person);
    }

}
