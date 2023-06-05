package com.example.block12mongodb.application;

import com.example.block12mongodb.controller.dto.PersonaInputDto;
import com.example.block12mongodb.controller.dto.PersonaOutputDto;
import com.example.block12mongodb.domain.Persona;

import java.util.List;

public interface PersonaDAL {
    Persona savePerson(Persona person);
    List<Persona> getAllPerson();
    List<Persona> getAllPersonPaginated(
            int pageNumber, int pageSize);
    List<Persona> findByName(String name);
    Persona updateOnePerson(Persona person,int id);
    void deletePerson(Persona person);
    Persona getPersonById(int id);
}
