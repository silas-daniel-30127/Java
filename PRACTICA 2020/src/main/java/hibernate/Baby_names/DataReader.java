package hibernate.Baby_names;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.net.*;
import java.util.Arrays;

public class DataReader {
    private final URL url;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/registry";
    static final String DB_USER = "root";
    static final String DB_PASS = "hibernate";

    public DataReader(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public void transferData() throws IOException {

        try (Connection connection = connect();
             BufferedReader in = new BufferedReader(new InputStreamReader(this.url.openStream()))) {

            String inputLine;
            String[] words;
            while ((inputLine = in.readLine()) != null) {
                words = inputLine.split("\t");
                System.out.println(Arrays.toString(words));
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO babyname(year,name,gender) value (?,?,?)");
                String[] year = words[2].split(" ");
                preparedStatement.setInt(1, Integer.parseInt(year[0]));
                preparedStatement.setString(2, words[1]);
                preparedStatement.setString(3, "M");
                preparedStatement.executeUpdate();

                preparedStatement.setInt(1, Integer.parseInt(words[4]));
                preparedStatement.setString(2, words[3]);
                preparedStatement.setString(3, "M");
                preparedStatement.executeUpdate();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}