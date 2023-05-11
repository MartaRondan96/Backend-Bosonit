package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosInputDto;
import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputFullDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputSimpleDto;
import com.example.block7crudvalidation.domain.Alumnos_Estudios;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.Alumnos_EstudiosRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Alumnos_EstudiosServiceImpl implements Alumnos_EstudiosService{
    @Autowired
    private Alumnos_EstudiosRepository estudiosRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Alumnos_EstudiosOutputDto getEstudioById(int id) {
        if(estudiosRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra un estudio con ese ID");
        }
        else{
            return estudiosRepository.findById(id).orElseThrow()
                    .estudioToEstudioOutputDto();
        }
    }

    @Override
    public Alumnos_EstudiosOutputDto addEstudio(Alumnos_EstudiosInputDto estudio) {
        return estudiosRepository.save(new Alumnos_Estudios(estudio)).estudioToEstudioOutputDto();
    }

    @Override
    public Alumnos_EstudiosOutputDto updateEstudio(Alumnos_EstudiosInputDto estudioDto, int id) {
        if (estudiosRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("NOT FOUND: No se encuentra un estudio con ese ID");
        }
        Profesor profesor = profesorRepository.findById(estudioDto.getIdProfesor()).orElseThrow();
        Alumnos_Estudios estudioAct = estudiosRepository.findById(id).orElseThrow();
        estudioAct.setAsignatura(estudioDto.getAsignatura());
        estudioAct.setComment(estudioDto.getComment());
        estudioAct.setProfesor(profesor);
        estudioAct.setInitial_date(estudioDto.getInitialDate());
        estudioAct.setFinish_date(estudioDto.getFinishDate());
        return estudiosRepository.save(estudioAct)
                .estudioToEstudioOutputDto();
    }

    @Override
    public void deleteEstudioById(int id) {
        if (estudiosRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("NOT FOUND: No se encuentra una persona con ese ID");
        Alumnos_Estudios estudio = estudiosRepository.findById(id).orElseThrow();
        if(estudio.getStudent()!= null)
            throw new UnprocessableEntityException("La asignatura no se puede borrar porque tiene alumnos asignados");
        estudiosRepository.deleteById(id);
    }

    @Override
    public Iterable<Alumnos_EstudiosOutputDto> getAllEstudios(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return estudiosRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Alumnos_Estudios::estudioToEstudioOutputDto).toList();
    }

    @Override
    public StudentOutputSimpleDto getListAsignaturasByStudent(int idStudent) {
        Student student = studentRepository.findById(idStudent).orElseThrow();
        List<Alumnos_EstudiosOutputDto> asignaturasList = estudiosRepository.findByIdStudent(idStudent).stream().map(Alumnos_Estudios::estudioToEstudioOutputDto).toList();
        Set<Alumnos_EstudiosOutputDto> estudios = new HashSet<>(asignaturasList);

        return new StudentOutputSimpleDto(student.getId(), student.getNum_hours_week(),
                student.getComments(), student.getBranch(), estudios);
    }
}
