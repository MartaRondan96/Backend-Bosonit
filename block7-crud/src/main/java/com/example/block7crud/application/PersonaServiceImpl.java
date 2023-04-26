package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonaInputDTO;
import com.example.block7crud.controller.dto.PersonaOutputDTO;
import com.example.block7crud.domain.Persona;
import com.example.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO persona) {
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow()
                .personaToPersonaOutputDTO();
    }
    @Override
    public PersonaOutputDTO getPersonaByNombre(String nombre) {
        return personaRepository.findByNombre(nombre).orElseThrow()
                .personaToPersonaOutputDTO();
    }

    @Override
    public void deletePersonaById(int id) {
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
    }

    @Override
    public Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDTO).toList();
    }

    @Override
    public PersonaOutputDTO updatePersona(PersonaInputDTO persona,int id) {
        Persona personaAct = personaRepository.findById(id).orElseThrow();
        if (persona.getNombre() != null) {
            personaAct.setNombre(persona.getNombre());
        }
        if (persona.getEdad() != 0) {
            personaAct.setEdad(persona.getEdad());
        }
        if (persona.getPoblacion() != null) {
            personaAct.setPoblacion(persona.getPoblacion());
        }
        return personaRepository.save(personaAct).personaToPersonaOutputDTO();
    }

}
