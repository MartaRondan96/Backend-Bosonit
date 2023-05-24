package com.example.block10docker.exception;

import com.example.block10docker.domain.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;


@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
    private Date timestamp = new Date();
    private String message;
    public UnprocessableEntityException(String message){
        this.message = message;
    }

    public CustomError getError() {
        return new CustomError(timestamp, HttpStatus.UNPROCESSABLE_ENTITY.value(), this.message);

    }
}
