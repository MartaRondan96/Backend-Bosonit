package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;


public interface PersonaService {
    PersonaOutputDto getPersonaById(int id) throws EntityNotFoundException;
    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;
    PersonaOutputDto updatePersona(PersonaInputDto persona, int id) throws EntityNotFoundException, UnprocessableEntityException;
    void deletePersonaById(int id) throws EntityNotFoundException;
    Iterable<PersonaOutputDto> getAllPersonas(int numPage, int pageSize);
    PersonaOutputDto getPersonaByUsuario(String usuario) throws EntityNotFoundException;
}
