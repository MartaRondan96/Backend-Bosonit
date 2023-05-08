package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosInputDto;
import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosOutputDto;
import com.example.block7crudvalidation.repository.Alumnos_EstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Alumnos_EstudiosServiceImpl implements Alumnos_EstudiosService{
    @Autowired
    private Alumnos_EstudiosRepository alumnosEstudiosRepository;
    @Override
    public Alumnos_EstudiosOutputDto getEstudioById(int id) {
        return null;
    }

    @Override
    public Alumnos_EstudiosOutputDto addEstudio(Alumnos_EstudiosInputDto estudio) {
        return null;
    }

    @Override
    public Alumnos_EstudiosOutputDto updateEstudio(Alumnos_EstudiosInputDto estudio, int id) {
        return null;
    }

    @Override
    public void deleteEstudioById(int id) {

    }

    @Override
    public Iterable<Alumnos_EstudiosOutputDto> getAllEstudios(int numPage, int pageSize) {
        return null;
    }
}
