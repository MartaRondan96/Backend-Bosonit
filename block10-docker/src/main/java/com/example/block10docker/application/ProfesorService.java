package com.example.block10docker.application;


import com.example.block10docker.controller.dto.ProfesorInputDto;
import com.example.block10docker.controller.dto.ProfesorFullOutputDto;

public interface ProfesorService {
    ProfesorFullOutputDto getProfesorById(int id);
    ProfesorFullOutputDto addProfesor(ProfesorInputDto profesor) throws Exception;
    ProfesorFullOutputDto updateProfesor(ProfesorInputDto profesor, int id);
    void deleteProfesorById(int id);
    Iterable<ProfesorFullOutputDto> getAllProfesors(int numPage, int pageSize);
}
