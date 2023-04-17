import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(0,"12345678A","Marta","Rondan"));
        people.add(new Person(0,"12345678B","Mario","Cherbuy"));
        people.add(new Person(0,"12345678C","Alicia","Rondan"));
        for(Person p : people)
            System.out.println(p);

    }
}
