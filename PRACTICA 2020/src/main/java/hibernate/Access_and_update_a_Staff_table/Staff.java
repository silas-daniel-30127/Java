package hibernate.Access_and_update_a_Staff_table;

import java.sql.*;

public class Staff {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/registry";
    static final String DB_USER = "root";
    static final String DB_PASS = "hibernate";
    private String query;

    public Staff() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Person insert(Person p) {
        this.query = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone)  VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, p.getId());
            preparedStatement.setString(2, p.getLastName());
            preparedStatement.setString(3, p.getFirstName());
            preparedStatement.setString(4, p.getMi());
            preparedStatement.setString(5, p.getAddress());
            preparedStatement.setString(6, p.getCity());
            preparedStatement.setString(7, p.getState());
            preparedStatement.setString(8, p.getTelephone());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            p = null;
            ex.printStackTrace();
        }
        return p;
    }

    public Person update(Person p) {
        this.query = "UPDATE `registry`.`staff` SET `lastName` = ?, `firstName` = ?, `mi` = ?, `address` = ?, `city` = ?, `state` = ?, `telephone` = ? WHERE (`id` = " + p.getId() + ")";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p.getLastName());
            preparedStatement.setString(2, p.getFirstName());
            preparedStatement.setString(3, p.getMi());
            preparedStatement.setString(4, p.getAddress());
            preparedStatement.setString(5, p.getCity());
            preparedStatement.setString(6, p.getState());
            preparedStatement.setString(7, p.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public void delete(int id) {
        this.query = "DELETE FROM Staff WHERE id = " + id;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Person getPersonById(String id) {
        this.query = "SELECT * FROM Staff WHERE id = " + id;
        Person p = new Person();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                p.setId(resultSet.getInt("id"));
                p.setFirstName(resultSet.getString("firstName"));
                p.setLastName(resultSet.getString("lastName"));
                p.setAddress(resultSet.getString("address"));
                p.setCity(resultSet.getString("city"));
                p.setState(resultSet.getString("state"));
                p.setMi(resultSet.getString("mi"));
                p.setTelephone(resultSet.getString("telephone"));
            }
        } catch (SQLException t) {
            t.printStackTrace();
        }
        return p;
    }
}