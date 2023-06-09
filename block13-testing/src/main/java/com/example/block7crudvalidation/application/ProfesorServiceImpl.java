package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorFullOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public ProfesorFullOutputDto getProfesorById(int id) {
        if(profesorRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra un profesor con ese ID");
        }
        else {
            return profesorRepository.findById(id).orElseThrow()
                    .profesorToProfesorFullOutputDto();
        }
    }

    @Override
    public ProfesorFullOutputDto addProfesor(ProfesorInputDto profesorDto) throws Exception {
        Persona persona = personaRepository.findById(profesorDto.getIdPersona()).orElseThrow();
        if(persona.getProfesion()!=null)
            throw new Exception("La persona ya tiene profesion asignada");
        persona.setProfesion("Profesor");
        Profesor profesor = new Profesor(profesorDto);
        profesor.setPersona(persona);
        profesorRepository.save(profesor);
        return profesor.profesorToProfesorFullOutputDto();
    }

    @Override
    public ProfesorFullOutputDto updateProfesor(ProfesorInputDto profesor, int id) {
        if (profesorRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        Profesor profesorAct = profesorRepository.findById(id).orElseThrow();
        profesorAct.setBranch(profesor.getBranch());
        profesorAct.setComments(profesor.getComments());
        return profesorRepository.save(profesorAct)
                .profesorToProfesorFullOutputDto();
    }

    @Override
    public void deleteProfesorById(int id) {
        if (profesorRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");

        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        Persona persona = personaRepository.findById(profesor.getPersona().getId()).orElseThrow();
        persona.setProfesion("null");

        profesorRepository.deleteById(id);

    }

    @Override
    public Iterable<ProfesorFullOutputDto> getAllProfesors(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return profesorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profesor::profesorToProfesorFullOutputDto).toList();
    }
}
