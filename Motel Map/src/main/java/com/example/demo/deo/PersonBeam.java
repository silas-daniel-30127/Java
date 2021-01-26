package com.example.demo.deo;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mySQLPerson")
public class PersonBeam implements PersonDeo {
    private final String DB_URL = "jdbc:mysql://localhost:3306/registry";
    private final String DB_USER = "root";
    private final String DB_PASS = "hibernate";

    public PersonBeam() {
        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPerson(int id, Person person) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.execute("insert into persons values ('" + id + "', '" + person.getName() + "');");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Person> selectAllPeople() {
        List<Person> people = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from persons");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                people.add(new Person(id, username));
            }
            System.out.println(people);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLException at select all people");
        }
        return people;
    }

    @Override
    public Optional<Person> selectPersonById(int id) {
        List<Person> people = selectAllPeople();
        return people.stream().filter(person -> person.getId() == id).findFirst();
    }

    @Override
    public int deletePersonById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.execute("delete from persons where id = " + id + ";");
            System.out.println("Successfully deleted !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }

    @Override
    public int updatePersonById(int id, Person person) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.execute("update persons set username = '" + person.getName() + "' where id = " + id + ";");
            System.out.println("Successfully updated !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}
