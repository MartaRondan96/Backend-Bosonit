package com.bosonit.formacion;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        /*Crear un método que reciba la ruta de un fichero CSV (por ejemplo: people.csv) y devuelva una lista de
        personas. El formato del fichero es el siguiente:
        Cada línea del fichero corresponde a una persona (clase Person).
        Cada línea puede contener hasta 3 campos separados por dos puntos (:). Los campos serán los siguientes:
            name:town:age

        El campo name es obligatorio, el resto de campos son opcionales. Nota: cuando no se informe el campo age
        guardaremos la persona con 0 años. Si hay alguna línea en el fichero en la que el campo age sea 0,
        consideraremos que esa persona tiene edad desconocida.
        Si cualquiera de las líneas no tiene formato correcto el método debe lanzar una excepción
        InvalidLineFormatException. Esta excepción debe incluir un mensaje descriptivo incluyendo la línea que provocó
        el error y la causa de la excepción.
        Nota: no usamos Stream en este método porque no permite tratar correctamente la excepción
        InvalidLineFormatException. Stream no se recomienda cuando hay que tratar excepciones.
         */
    }

}
