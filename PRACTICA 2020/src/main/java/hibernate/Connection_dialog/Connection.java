package hibernate.Connection_dialog;

import javax.swing.*;

public class Connection extends JApplet {
    private final DBConnectionPanel connectionPanel = new DBConnectionPanel("jdbc:mysql://localhost/registry", "root", "hibernate");

    public Connection() {
        init();
        start();
    }

    public void init() {
        add(connectionPanel);
    }
}