package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosInputDto;
import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosOutputDto;
import com.example.block7crudvalidation.domain.Alumnos_Estudios;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.repository.Alumnos_EstudiosRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class Alumnos_EstudiosServiceImpl implements Alumnos_EstudiosService{
    @Autowired
    private Alumnos_EstudiosRepository estudiosRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
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
        estudiosRepository.deleteById(id);

    }

    @Override
    public Iterable<Alumnos_EstudiosOutputDto> getAllEstudios(int numPages, int pageSize) {
        PageRequest pageRequest = PageRequest.of(numPages, pageSize);
        return estudiosRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Alumnos_Estudios::estudioToEstudioOutputDto).toList();
    }
}
