package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonaInputDTO;
import com.example.block7crud.controller.dto.PersonaOutputDTO;

public interface PersonaService {
    PersonaOutputDTO addPersona(PersonaInputDTO persona);
    PersonaOutputDTO getPersonaById(int id);
    void deletePersonaById( int id);
    Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDTO getPersonaByNombre(String nombre);

    PersonaOutputDTO updatePersona(int id, PersonaInputDTO persona);
}
