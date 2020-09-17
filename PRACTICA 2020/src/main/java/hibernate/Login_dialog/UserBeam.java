package hibernate.Login_dialog;

import javax.swing.*;
import java.sql.*;

public class UserBeam {

    private final String DB_URL = "jdbc:mysql://localhost:3306/registry";
    private final String DB_USER = "root";
    private final String DB_PASS = "hibernate";

    public UserBeam() {
        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JScrollPane addNames() {
        JScrollPane scroll = null;
        String[] res = new String[45];
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from registry");
            int i = 0;
            while (resultSet.next()) {
                res[i++] = resultSet.getString("username");
            }
            JList<String> list = new JList<>(res);
            scroll = new JScrollPane(list);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQLException");
        }
        return scroll;
    }

    public boolean login(String username, String pass) {
        String password = String.valueOf(pass);
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            PreparedStatement statement;
            String sql = "SELECT * FROM registry WHERE username=? AND pass=?";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet;

            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException");
        }
        return false;
    }

    public boolean updateData(String username, String p) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert registry(username,pass) values('" + username + "', '" + p + "')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}