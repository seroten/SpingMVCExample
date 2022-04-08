package org.example.DAO;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int count = 0;

    {
        people = new ArrayList<>();
        people.add(new Person(count++, "Bob"));
        people.add(new Person(count++, "Tom"));
        people.add(new Person(count++, "John"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(people -> people.getId() == id).findAny().orElse(null);
    }
}
