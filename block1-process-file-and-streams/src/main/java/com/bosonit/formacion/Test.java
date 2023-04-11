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
        System.out.println("lista de personas");
        for(Person p : people){
            System.out.println(p);
        }

    }

    public static List<Person> readCSVFile() throws IOException, InvalidLineFormatException {
        List<Person> peopleList = new ArrayList<>();
        Person p = new Person();
        Path path = Paths.get("src/main/resources/people.cvs");

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        while(line!=null){
            String[] data = line.split(":");

            if (data.length <2 || data[0].isBlank()) {
                throw new InvalidLineFormatException("Invalid line format: " + line, null);
            }
            String name = data[0];
            //no escribe unknown em la ciudad
            //Revisar condicion
            String town = data.length > 1 ? data[1] : "unknown";
            int age = data.length > 2 ? Integer.parseInt(data[2]) : 0;

            peopleList.add(new Person(name, town, age));

            line=reader.readLine();
        }
        return peopleList;
    }

    static class InvalidLineFormatException extends Exception {
        public InvalidLineFormatException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
