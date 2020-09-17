package hibernate.Connection_dialog;

import javax.swing.*;
import java.awt.*;

public class AppMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Connector");
        Connection connection = new Connection();
        frame.getContentPane().add(connection, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
