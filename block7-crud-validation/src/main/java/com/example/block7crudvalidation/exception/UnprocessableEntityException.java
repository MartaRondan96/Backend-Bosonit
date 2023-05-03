package com.example.block7crudvalidation.exception;

import com.example.block7crudvalidation.domain.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;


@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
    private Date timestamp = new Date();
    public UnprocessableEntityException(){
    }

    public CustomError getError() {
        return new CustomError(timestamp, HttpStatus.UNPROCESSABLE_ENTITY.value(), "UNPROCESSABLE ENTITY: La validación de los campos no cumple los requisitos establecidos");

    }
}
