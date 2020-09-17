package hibernate.Find_grades;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Grades extends JApplet {
    private static final long serialVersionUID = 1L;
    private final JTextField jtfSSN = new JTextField(9);
    private final JButton jbtShowGrade = new JButton("Show Grade");
    private final JTextArea jTextArea = new JTextArea();
    private PreparedStatement preparedStatement;

    /**
     * Initialize the applet
     */
    public void init() {
        initializeDB();
        jbtShowGrade.addActionListener(this::jbtShowGrade_actionPerformed);

        JPanel jPanel1 = new JPanel();
        jPanel1.add(new JLabel("SSN"));
        jPanel1.add(jtfSSN);
        jPanel1.add(jbtShowGrade);

        add(jPanel1, BorderLayout.NORTH);
        jTextArea.setEditable(false);
        add(jTextArea, BorderLayout.CENTER);
    }

    private void initializeDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/registry", "root", "hibernate");
            String queryString = "select firstName, mi, lastName, title, grade from Student, Enrollment, Course where Student.ssn = ? and Enrollment.ssn = Student.ssn and Enrollment.courseId = Course.courseId";
            preparedStatement = connection.prepareStatement(queryString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbtShowGrade_actionPerformed(ActionEvent e) {
        String ssn = jtfSSN.getText();
        try {
            preparedStatement.setString(1, ssn);
            ResultSet resultSet = preparedStatement.executeQuery();

            jTextArea.setText("");
            while (resultSet.next()) {
                String lastName = resultSet.getString(1);
                String mi = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String title = resultSet.getString(4);
                String grade = resultSet.getString(5);

                jTextArea.append(firstName + " " + mi + " " + lastName + "'s grade on course " + title + " is " + grade + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}