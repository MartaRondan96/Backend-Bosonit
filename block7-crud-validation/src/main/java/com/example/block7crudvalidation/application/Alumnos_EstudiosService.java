package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosInputDto;
import com.example.block7crudvalidation.controller.dto.Alumnos_EstudiosOutputDto;

public interface Alumnos_EstudiosService {
    Alumnos_EstudiosOutputDto getEstudioById(int id);
    Alumnos_EstudiosOutputDto addEstudio(Alumnos_EstudiosInputDto estudio);
    Alumnos_EstudiosOutputDto updateEstudio(Alumnos_EstudiosInputDto estudio, int id);
    void deleteEstudioById(int id);
    Iterable<Alumnos_EstudiosOutputDto> getAllEstudios(int numPage, int pageSize);
}
