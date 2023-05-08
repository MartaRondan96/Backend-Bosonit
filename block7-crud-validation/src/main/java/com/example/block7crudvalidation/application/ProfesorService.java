package com.example.block7crudvalidation.application;


import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorOutputDto;

public interface ProfesorService {
    ProfesorOutputDto getProfesorById(int id);
    ProfesorOutputDto addProfesor(ProfesorInputDto profesor);
    ProfesorOutputDto updateProfesor(ProfesorInputDto profesor, int id);
    void deleteProfesorById(int id);
    Iterable<ProfesorOutputDto> getAllProfesors(int numPage, int pageSize);
}
