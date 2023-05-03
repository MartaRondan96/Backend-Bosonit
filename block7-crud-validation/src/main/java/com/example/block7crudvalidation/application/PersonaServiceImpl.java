package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDto getPersonaById(int id) throws EntityNotFoundException {
        if(personaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        }
        else{
            return personaRepository.findById(id).orElseThrow()
                    .personaToPersonaOutputDTO();
        }
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception {
        if (persona.getUsuario() == null || persona.getUsuario().length()<6  || persona.getUsuario().length()>10)
            throw new UnprocessableEntityException();

        if (persona.getPassword() == null || persona.getName() == null || persona.getCompany_email() == null || persona.getPersonal_email() == null
                || persona.getCity() == null || persona.getActive() == null || persona.getCreate_date() == null)
            throw new UnprocessableEntityException();
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona, int id ) throws EntityNotFoundException, UnprocessableEntityException {
        if(personaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        }
        else {
            Persona personaAct = personaRepository.findById(id).orElseThrow();
            if (persona.getUsuario() != null && persona.getUsuario().length() >= 6 && persona.getUsuario().length() <= 10) {
                personaAct.setUsuario(persona.getUsuario());
            } else {
                throw new UnprocessableEntityException();
            }
            if (persona.getPassword() == null || persona.getName() == null || persona.getCompany_email() == null || persona.getPersonal_email() == null
                    || persona.getCity() == null || persona.getActive() == null || persona.getCreate_date() == null){
                throw new UnprocessableEntityException();
            }else {
                personaAct.setPassword(persona.getPassword());
                personaAct.setName(persona.getName());
                personaAct.setSurname(persona.getSurname());
                personaAct.setCompany_email(persona.getCompany_email());
                personaAct.setPersonal_email(persona.getPersonal_email());
                personaAct.setCity(persona.getCity());
                personaAct.setActive(persona.getActive());
                personaAct.setCreate_date(persona.getCreate_date());
                personaAct.setImage_url(persona.getImage_url());
                personaAct.setTermination_date(persona.getTermination_date());
            }
            return personaRepository.save(personaAct).personaToPersonaOutputDTO();
        }
    }

    @Override
    public void deletePersonaById(int id) throws EntityNotFoundException {
        if(personaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        }
        else{
            personaRepository.deleteById(id);
        }
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDTO).toList();
    }

    @Override
    public PersonaOutputDto getPersonaByUsuario(String usuario) throws EntityNotFoundException {
        if(personaRepository.findByUsuario(usuario).isEmpty()) {
            throw new EntityNotFoundException();
        }
        else{
        return personaRepository.findByUsuario(usuario).orElseThrow()
                .personaToPersonaOutputDTO();
        }
    }
}
