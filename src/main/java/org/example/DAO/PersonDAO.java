package org.example.DAO;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int count;

    {
        people = new ArrayList<>();
        people.add(new Person(++count, "Bob"));
        people.add(new Person(++count, "Tom"));
        people.add(new Person(++count, "John"));
    }

    public List<Person> index() {
        people.forEach(System.out::println);
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++count);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
    }

    public void delete(int id) {
        people.remove(id);
    }
}
