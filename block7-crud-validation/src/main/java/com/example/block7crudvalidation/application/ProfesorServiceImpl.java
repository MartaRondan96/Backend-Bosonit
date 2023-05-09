package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorOutputDto;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;
    @Override
    public ProfesorOutputDto getProfesorById(int id) {
        if(profesorRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra un profesor con ese ID");
        }
        else {
            return profesorRepository.findById(id).orElseThrow()
                    .profesorToProfesorOutputDto();
        }
    }

    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesor) {
        return profesorRepository.save(new Profesor(profesor)).profesorToProfesorOutputDto();
    }

    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto profesor, int id) {
        if (profesorRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        }
        Profesor profesorAct = profesorRepository.findById(id).orElseThrow();

        return profesorRepository.save(profesorAct)
                .profesorToProfesorOutputDto();
    }

    @Override
    public void deleteProfesorById(int id) {
        if (profesorRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        profesorRepository.deleteById(id);

    }

    @Override
    public Iterable<ProfesorOutputDto> getAllProfesors(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return profesorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profesor::profesorToProfesorOutputDto).toList();
    }
}
