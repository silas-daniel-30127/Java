package hibernate.Connection_dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.DriverManager;

public class DBConnectionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JLabel connected;
    private final JComboBox<String> jComboBox = new JComboBox<>(new String[]{"sun.jdbc.odbc.JdbcOdbcDriver", "com.mysql.jdbc.Driver", "com.mysql.cj.jdbc.Driver", "oracle.jdbc.driver.OracleDriver"});
    private final JTextField jTextField1 = new JTextField(20);
    private final JTextField jTextField2 = new JTextField(20);
    private final JTextField jTextField3 = new JTextField(20);

    public DBConnectionPanel(String db, String user, String pass) {
        jTextField1.setText(db);
        jTextField2.setText(user);
        jTextField3.setText(pass);
        JPanel jPanel1 = new JPanel(new BorderLayout(5, 5));

        JPanel jPanel2 = new JPanel(new GridLayout(4, 1, 5, 5));
        jPanel2.add(new JLabel("JDBC Driver"));
        jPanel2.add(new JLabel("Database URL"));
        jPanel2.add(new JLabel("Username"));
        jPanel2.add(new JLabel("Password"));
        jPanel1.add(jPanel2, BorderLayout.WEST);

        JPanel jPanel3 = new JPanel(new GridLayout(4, 1, 5, 5));
        jPanel3.add(jComboBox);
        jPanel3.add(jTextField1);
        jPanel3.add(jTextField2);
        jPanel3.add(jTextField3);
        jPanel1.add(jPanel3, BorderLayout.CENTER);

        JPanel jPanel4 = new JPanel(new BorderLayout(5, 5));
        jPanel4.setBorder(new EmptyBorder(5, 5, 5, 5));

        connected = new JLabel("No connection");
        connected.setForeground(Color.red);
        jPanel4.add(connected, BorderLayout.WEST);
        JButton jButton = new JButton("Connect to DB");
        jPanel4.add(jButton, BorderLayout.EAST);
        setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.CENTER);
        add(jPanel4, BorderLayout.SOUTH);

        jButton.addActionListener(arg0 -> {
            try {
                Class.forName(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
                DriverManager.getConnection(jTextField1.getText(), jTextField2.getText(), jTextField3.getText());
                connected.setForeground(Color.green);
                connected.setText("Connected");
            } catch (Exception ex) {
                connected.setText("No connection");
                connected.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }
        });

    }

}