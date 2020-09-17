package hibernate.Visualize_data;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Chart extends Application {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/registry";
    static final String DB_USER = "root";
    static final String DB_PASS = "hibernate";

    public Chart() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<PieChart.Data> getData() {
        String sql = "select deptId, count(*) from Student where deptId is not null group by deptId;";
        ObservableList<PieChart.Data> pieData = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<PieChart.Data> data = new ArrayList<>();

            while (resultSet.next()) {
                data.add(new PieChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            }
            pieData = FXCollections.observableArrayList(data);

        } catch (SQLException t) {
            t.printStackTrace();
        }
        return pieData;
    }

    @Override
    public void start(Stage primaryStage) {
        ObservableList<PieChart.Data> pieData = getData();

        PieChart pieChart = new PieChart(pieData);
        pieChart.setClockwise(false);
        Group group = new Group(pieChart);
        Scene scene = new Scene(group, 600, 400);
        primaryStage.setTitle("Chart example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
