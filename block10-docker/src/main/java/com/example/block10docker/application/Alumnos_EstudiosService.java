package com.example.block10docker.application;

import com.example.block10docker.controller.dto.Alumnos_EstudiosInputDto;
import com.example.block10docker.controller.dto.Alumnos_EstudiosOutputDto;
import com.example.block10docker.controller.dto.StudentOutputSimpleDto;

public interface Alumnos_EstudiosService {
    Alumnos_EstudiosOutputDto getEstudioById(int id);
    Alumnos_EstudiosOutputDto addEstudio(Alumnos_EstudiosInputDto estudio);
    Alumnos_EstudiosOutputDto updateEstudio(Alumnos_EstudiosInputDto estudio, int id);
    void deleteEstudioById(int id);
    Iterable<Alumnos_EstudiosOutputDto> getAllEstudios(int numPage, int pageSize);

    StudentOutputSimpleDto getListAsignaturasByStudent(int idStudent);
}
