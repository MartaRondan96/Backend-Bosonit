package com.example.block10docker.application;

import com.example.block10docker.controller.dto.PersonaInputDto;
import com.example.block10docker.controller.dto.PersonaOutputDto;
import com.example.block10docker.exception.EntityNotFoundException;
import com.example.block10docker.exception.UnprocessableEntityException;


public interface PersonaService {
    PersonaOutputDto getPersonaById(int id) throws EntityNotFoundException;
    Object getPersonaById(int id, String outputType) throws EntityNotFoundException;
    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;
    PersonaOutputDto updatePersona(PersonaInputDto persona, int id) throws EntityNotFoundException, UnprocessableEntityException;
    void deletePersonaById(int id) throws EntityNotFoundException;
    Iterable<PersonaOutputDto> getAllPersonas(int numPage, int pageSize);
    PersonaOutputDto getPersonaByUsuario(String usuario) throws EntityNotFoundException;
}
