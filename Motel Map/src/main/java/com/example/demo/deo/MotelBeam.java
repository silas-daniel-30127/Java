package com.example.demo.deo;

import com.example.demo.model.Motel;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mySQLMotel")
public class MotelBeam implements MotelDeo {
    private final String DB_URL = "jdbc:mysql://localhost:3306/registry";
    private final String DB_USER = "root";
    private final String DB_PASS = "hibernate";

    public MotelBeam() {
        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertMotel(int id, Motel motel, int x, int y) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.execute("insert into motels values (" + id + ", '" + motel.getName() + "', " + x + ", " + y + " );");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Motel> selectAllMotels() {
        List<Motel> motels = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from motels");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int x = resultSet.getInt("pos_x");
                int y = resultSet.getInt("pos_y");
                motels.add(new Motel(id, name, x, y));

            }
            System.out.println(motels);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLException at select all motels");
        }
        return motels;
    }

    @Override
    public Optional<Motel> selectMotelById(int id) {
        List<Motel> motels = selectAllMotels();
        return motels.stream().filter(motel -> motel.getId() == id).findFirst();
    }

    @Override
    public int deleteMotelById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.execute("delete from motels where id = " + id + ";");
            System.out.println("Successfully deleted !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }

    @Override
    public int updateMotelById(int id, Motel motel) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.execute("update persons set name = '" + motel.getName() + "', pos_x = " + motel.getPos_x() + ", pos_y = " + motel.getPos_y() + " where id = " + id + ";");
            System.out.println("Successfully updated !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}
