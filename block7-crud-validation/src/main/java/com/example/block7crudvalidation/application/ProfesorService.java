package com.example.block7crudvalidation.application;


import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorFullOutputDto;

public interface ProfesorService {
    ProfesorFullOutputDto getProfesorById(int id);
    ProfesorFullOutputDto addProfesor(ProfesorInputDto profesor) throws Exception;
    ProfesorFullOutputDto updateProfesor(ProfesorInputDto profesor, int id);
    void deleteProfesorById(int id);
    Iterable<ProfesorFullOutputDto> getAllProfesors(int numPage, int pageSize);
}
