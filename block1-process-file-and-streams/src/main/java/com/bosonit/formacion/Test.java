package com.bosonit.formacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException, InvalidLineFormatException {
        List<Person> people = readCSVFile();
        System.out.println("-----People list-----");
        for(Person p : people){
            System.out.println(p);
        }
        //Crear un segundo método que filtre las personas obtenidas al leer el fichero
        //y devuelve esa lista de personas. Usar Stream para filtrar.

    }

    //Método que lee el fichero y devuelve una lista de personas
    public static List<Person> readCSVFile() throws IOException, InvalidLineFormatException {
        List<Person> peopleList = new ArrayList<>();
        Path path = Paths.get("src/main/resources/people.cvs");
        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        while(line!=null){
            String[] data = line.split(":");
            if (data.length <2 || data[0].isBlank()) {
                //Lanza la excepción
                throw new InvalidLineFormatException("Invalid line format: " + line);
            }
            String name = data[0];
            String town = data[1].isBlank() ? "unknown": data[1];
            int age = data.length > 2 ? Integer.parseInt(data[2]) : 0;
            peopleList.add(new Person(name, town, age));
            line=reader.readLine();
        }
        return peopleList;
    }

    //Clase que trata la excepcion que hemos creado
    public static class InvalidLineFormatException extends Exception {
        public InvalidLineFormatException(String message) {
            super(message);
        }
    }
}
