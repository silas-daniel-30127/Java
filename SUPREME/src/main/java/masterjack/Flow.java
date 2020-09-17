package masterjack;

import javax.swing.*;
import java.awt.*;

public class Flow extends JFrame {

    public Flow(String s) {
        setLayout(new FlowLayout());
        setTitle(s);
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JButton("Button 3"));
        add(new JButton("Long-Named Button 4"));
        add(new JButton("5"));

        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        new Flow("flow demo");
    }

}
