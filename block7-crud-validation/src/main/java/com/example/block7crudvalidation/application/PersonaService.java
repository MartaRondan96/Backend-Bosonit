package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;


public interface PersonaService {
    PersonaOutputDto getPersonaById(int id);
    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;
    PersonaOutputDto updatePersona(PersonaInputDto persona, int id);
    void deletePersonaById(int id);
    Iterable<PersonaOutputDto> getAllPersonas(int numPage, int pageSize);
    PersonaOutputDto getPersonaByUsuario(String usuario);
}
