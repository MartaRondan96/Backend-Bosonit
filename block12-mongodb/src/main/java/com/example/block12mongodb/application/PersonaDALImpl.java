package com.example.block12mongodb.application;

import com.example.block12mongodb.controller.dto.PersonaInputDto;
import com.example.block12mongodb.controller.dto.PersonaOutputDto;
import com.example.block12mongodb.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaDALImpl implements PersonaDAL{
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonaDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Persona savePerson(Persona person) {
        mongoTemplate.save(person);
        return person;
    }

    @Override
    public List<Persona> getAllPerson() {
        return mongoTemplate.findAll(Persona.class);
    }

    @Override
    public List<Persona> getAllPersonPaginated(int pageNumber, int pageSize) {
        Query query = new Query();
        query.skip(pageNumber * pageSize);
        query.limit(pageSize);
        return mongoTemplate.find(query, Persona.class);
    }

    @Override
    public List<Persona> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Persona.class);
    }

    @Override
    public Persona updateOnePerson(Persona person, int id) {
        person.setId_persona(id);
        return mongoTemplate.save(person);
    }

    @Override
    public void deletePerson(Persona person) {
        mongoTemplate.remove(person);
    }

    @Override
    public Persona getPersonById(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Persona.class);
    }
}
