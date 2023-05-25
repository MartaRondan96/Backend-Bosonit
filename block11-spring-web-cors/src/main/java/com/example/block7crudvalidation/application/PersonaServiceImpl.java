package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public PersonaOutputDto getPersonaById(int id) throws EntityNotFoundException {
        if(personaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        else{
            return personaRepository.findById(id).orElseThrow()
                    .personaToPersonaOutputDTO();
        }
    }

    @Override
    public Object getPersonaById(int id, String outputType) throws EntityNotFoundException {
        Persona persona = personaRepository.findById(id).orElseThrow();
        if(outputType.equalsIgnoreCase("full") && persona.getProfesion()!=null) {
            if (persona.getProfesion().equalsIgnoreCase("estudiante")) {
                Student s = studentRepository.findByPersona(persona);
                persona.setStudent(s);
                return persona.personaToPersonaStudentOutputDTO();
            }
            if (persona.getProfesion().equalsIgnoreCase("profesor")) {
                return persona.personaToPersonaProfesorOutputDTO();
            }
        }
        return persona.personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception {
        if (!(persona.getUsuario() != null && persona.getUsuario().length() >= 6 && persona.getUsuario().length() <= 10))
            throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Usuario no cumple las condiciones");
            if (persona.getPassword() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Password no puede ser nulo");
            if (persona.getName() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Name no puede ser nulo");
            if (persona.getCompany_email() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Company_email no puede ser nulo");
            if (persona.getPersonal_email() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Personal_email no puede ser nulo");
            if (persona.getCity() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: City no puede ser nulo");
            if (persona.getActive() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Active no puede ser nulo");
            if (persona.getCreated_date() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Create_date no puede ser nulo");

            return personaRepository.save(new Persona(persona)).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona, int id ) throws EntityNotFoundException, UnprocessableEntityException {
        if(personaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }

            Persona personaAct = personaRepository.findById(id).orElseThrow();
            if (!(persona.getUsuario() != null && persona.getUsuario().length() >= 6 && persona.getUsuario().length() <= 10))
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Usuario no cumple las condiciones");
            if (persona.getPassword() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Password no puede ser nulo");
            if (persona.getName() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Name no puede ser nulo");
            if (persona.getCompany_email() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Company_email no puede ser nulo");
            if (persona.getPersonal_email() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Personal_email no puede ser nulo");
            if (persona.getCity() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: City no puede ser nulo");
            if (persona.getActive() == null)
                    throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Active no puede ser nulo");
            if (persona.getCreated_date() == null)
                throw new UnprocessableEntityException("UNPROCESSABLE ENTITY: Create_date no puede ser nulo");

                personaAct.setUsuario(persona.getUsuario());
                personaAct.setPassword(persona.getPassword());
                personaAct.setName(persona.getName());
                personaAct.setSurname(persona.getSurname());
                personaAct.setCompany_email(persona.getCompany_email());
                personaAct.setPersonal_email(persona.getPersonal_email());
                personaAct.setCity(persona.getCity());
                personaAct.setActive(persona.getActive());
                personaAct.setCreated_date(persona.getCreated_date());
                personaAct.setImage_url(persona.getImage_url());
                personaAct.setTermination_date(persona.getTermination_date());
            return personaRepository.save(personaAct).personaToPersonaOutputDTO();

    }

    @Override
    public void deletePersonaById(int id) throws EntityNotFoundException {
        if(personaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
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
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        else{
        return personaRepository.findByUsuario(usuario).orElseThrow()
                .personaToPersonaOutputDTO();
        }
    }
}
