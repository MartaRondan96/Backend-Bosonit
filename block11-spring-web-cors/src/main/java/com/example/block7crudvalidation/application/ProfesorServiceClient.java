package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.ProfesorSimpleOutputDto;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.feign.ProfesorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceClient implements ProfesorClient {
    @Autowired
    ProfesorClient profesorClient;
    @Override
    public ProfesorSimpleOutputDto getProfesor(int id) {
        return profesorClient.getProfesor(id);
    }
}
