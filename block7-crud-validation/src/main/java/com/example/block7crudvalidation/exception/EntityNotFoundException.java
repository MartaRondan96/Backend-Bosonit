package com.example.block7crudvalidation.exception;

import com.example.block7crudvalidation.domain.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    private Date timestamp = new Date();
    public  EntityNotFoundException(){
    }

    public CustomError getError() {
        return new CustomError(timestamp, HttpStatus.NOT_FOUND.value(), "NOT FOUND: No se encuentran registros");

    }
}
