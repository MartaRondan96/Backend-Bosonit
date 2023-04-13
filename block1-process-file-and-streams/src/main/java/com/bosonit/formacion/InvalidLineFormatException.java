package com.bosonit.formacion;

//Clase que trata la excepción que hemos creado
public class InvalidLineFormatException extends Exception {
    public InvalidLineFormatException(String message) {
        super(message);
    }
}