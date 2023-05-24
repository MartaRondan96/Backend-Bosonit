package com.example.block10docker.exception;

import com.example.block10docker.domain.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    private Date timestamp = new Date();
    private String message;
    public  EntityNotFoundException(String message){
        this.message = message;
    }

    public CustomError getError() {
        return new CustomError(timestamp, HttpStatus.NOT_FOUND.value(), this.message);

    }
}
