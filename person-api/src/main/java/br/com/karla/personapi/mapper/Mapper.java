package br.com.karla.personapi.mapper;

import br.com.karla.personapi.data.vo.v1.PersonVO;
import br.com.karla.personapi.model.Person;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static final ModelMapper mapper = configureMapper();

    public static <O, D> D parseObject (O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<>();
        for (O o : origin){
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

    private static ModelMapper configureMapper(){

        ModelMapper mapper = new ModelMapper();

        mapper
                .createTypeMap(Person.class, PersonVO.class)
                .addMapping(Person::getId, PersonVO::setKey);

        return mapper;
    }

}
