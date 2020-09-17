package hibernate.Visualize_data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class TransferDATA {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/registry";
    static final String DB_USER = "root";
    static final String DB_PASS = "hibernate";

    public TransferDATA() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void transferData() throws IOException {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            BufferedReader fr = new BufferedReader(new FileReader("data.txt"));
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Student (`ssn`, `firstName`, `mi`, `lastName`, `phone`, `birthDate`, `street`, `zipCode`, `deptId`) values (?,?,?,?,?,?,?,?,?)");
            String i;
            while ((i = fr.readLine()) != null) {
                String[] words = i.split(";");
                System.out.println(Arrays.toString(words));
                preparedStatement.setString(1, words[0]);
                preparedStatement.setString(2, words[1]);
                preparedStatement.setString(3, words[2]);
                preparedStatement.setString(4, words[3]);
                preparedStatement.setString(5, words[4]);
                preparedStatement.setDate(6, Date.valueOf(words[5]));
                preparedStatement.setString(7, words[6]);
                preparedStatement.setString(8, words[7]);
                preparedStatement.setString(9, words[8]);
                preparedStatement.executeUpdate();
            }
            fr.close();


        } catch (SQLException | ClassCastException e) {
            e.printStackTrace();
        }
    }
}
