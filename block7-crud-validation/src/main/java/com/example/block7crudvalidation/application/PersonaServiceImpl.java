package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow()
                .personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception {
        if (persona.getUsuario() == null || persona.getUsuario().length()<6  || persona.getUsuario().length()>10) {
            throw new Exception("Usuario no cumple las condiciones");
        }
        if (persona.getPassword() == null) {
            throw new Exception("Password no puede ser nulo");
        }
        if (persona.getName() == null) {
            throw new Exception("Name no puede ser nulo");
        }
        if (persona.getCompany_email() == null) {
            throw new Exception("Company_email no puede ser nulo");
        }
        if (persona.getPersonal_email() == null) {
            throw new Exception("Personal_email no puede ser nulo");
        }
        if (persona.getCity() == null) {
            throw new Exception("City no puede ser nulo");
        }
        if (persona.getActive() == null) {
            throw new Exception("Active no puede ser nulo");
        }
        if (persona.getCreate_date() == null) {
            throw new Exception("Create_Date no puede ser nulo");
        }
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona, int id ) {
        Persona personaAct = personaRepository.findById(id).orElseThrow();
        if (persona.getUsuario() != null && persona.getUsuario().length()>=6 && persona.getUsuario().length()<=10 ) {
            personaAct.setUsuario(persona.getUsuario());
        }
        if (persona.getPassword() != null) {
            personaAct.setPassword(persona.getPassword());
        }
        if (persona.getName() != null) {
            personaAct.setName(persona.getName());
        }
        personaAct.setSurname(persona.getSurname());
        if (persona.getCompany_email() != null) {
            personaAct.setCompany_email(persona.getCompany_email());
        }
        if (persona.getPersonal_email() != null) {
            personaAct.setPersonal_email(persona.getPersonal_email());
        }
        if (persona.getCity() != null) {
            personaAct.setCity(persona.getCity());
        }
        if (persona.getActive() != null) {
            personaAct.setActive(persona.getActive());
        }
        if (persona.getCreate_date() != null) {
            personaAct.setCreate_date(persona.getCreate_date());
        }
        personaAct.setImage_url(persona.getImage_url());
        personaAct.setTermination_date(persona.getTermination_date());
        return personaRepository.save(personaAct).personaToPersonaOutputDTO();

    }

    @Override
    public void deletePersonaById(int id) {
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDTO).toList();
    }

    @Override
    public PersonaOutputDto getPersonaByUsuario(String usuario) {
        return personaRepository.findByUsuario(usuario).orElseThrow()
                .personaToPersonaOutputDTO();
    }
}
