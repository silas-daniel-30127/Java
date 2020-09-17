package hibernate.Access_and_update_a_Staff_table;

import javax.swing.*;
import java.awt.*;

public class AppMain {
    public static void main(String[] args) {
        JFrame f = new JFrame("Staff");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        f.getContentPane().add(new PersonUI());
        f.setSize(1500, 250);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}