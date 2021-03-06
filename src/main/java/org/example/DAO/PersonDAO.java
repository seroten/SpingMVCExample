package org.example.DAO;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class PersonDAO {
    private static int count;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "4321";

    private static Connection connection;
    private Person person;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Person\"");
            while (resultSet.next()) {
                setAllFieldPerson(resultSet);
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        people.sort(Comparator.comparing(Person::toString));
        return people;
    }

    public Person show(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"Person\" WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            setAllFieldPerson(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"Person\"(name, age, email) VALUES(?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE \"Person\" SET name = ?, age = ?, email = ? WHERE id = ?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM \"Person\" WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Person setAllFieldPerson(ResultSet resultSet) {
        person = new Person();
        try {
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}
