package com.bosonit.formacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws IOException, InvalidLineFormatException {
        List<Person> people = readCSVFile();
        System.out.println("-----People list-----");
        for(Person p : people){
            System.out.println(p);
        }

        Predicate<Person> under25 = p -> p.getAge() < 25 && p.getAge() > 0;
        List<Person> youngPeople = filterPeople(people, under25);
        System.out.println("-----Young People list-----");
        for(Person p : youngPeople){
            System.out.println(p);
        }
        Predicate<Person> startsA = p -> p.getName().toUpperCase().startsWith("A") || p.getName().toUpperCase().startsWith("Á");
        List<Person> APeople = filterPeople(people, startsA);
        System.out.println("-----A People list-----");
        for(Person p : APeople){
            System.out.println(p);
        }

        Optional<Person> madridPeople = youngPeople.stream()
                .filter(p -> p.getTown().equals("Madrid"))
                .findFirst();
        System.out.println("First Madrid Person: " + madridPeople);

        Optional<Person> bcnPeople = youngPeople.stream()
                .filter(p -> p.getTown().equals("Barcelona"))
                .findFirst();

        System.out.println("First Bcn Person: " + bcnPeople);
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
            String town = data[1].isBlank() ? "unknown" : data[1];
            int age = data.length > 2 ? Integer.parseInt(data[2]) : 0;
            peopleList.add(new Person(name, town, age));
            line=reader.readLine();
        }
        return peopleList;
    }
    //Método genérico filtros
    public static List<Person> filterPeople(List<Person> people, Predicate<Person> predicate) {
        return people.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
