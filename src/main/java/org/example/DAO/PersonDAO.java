package org.example.DAO;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int count;

    {
        people = new ArrayList<>();
        people.add(new Person(++count, "Bob", 25, "bob@gmail.com"));
        people.add(new Person(++count, "Tom", 43, "tom32@mail.ru"));
        people.add(new Person(++count, "John", 32, "john23@yandex.com"));
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
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
        System.out.println("id=" + id + " deleted");
    }
}
