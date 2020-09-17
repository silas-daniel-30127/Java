package hibernate.Find_grades;

import javax.swing.*;
import java.awt.*;

public class AppMain {
    public static void main(String[] args) {
        Grades applet = new Grades();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Grade finder");
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
