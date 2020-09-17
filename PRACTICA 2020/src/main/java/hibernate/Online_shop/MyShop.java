package hibernate.Online_shop;

import hibernate.Login_dialog.UserUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyShop extends JFrame {

    private UserUI login;
    private ArrayList<JButton> buttons = new ArrayList<>();

    public MyShop() {
        editFrame();
    }

    private void editFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Magazin Apicol");
        setLayout(new GridLayout(16,16));

        convertButtonList();

        addUIButtons();

        setSize(1000, 1000);
        setBackground(Color.gray);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void convertButtonList() {
        int cnt = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                    JButton localButton = new JButton("" + cnt++);
                    localButton.setBackground(new Color(i*16, 255-j*16, j*16));
                    this.buttons.add(localButton);

            }

        }
    }

    private void addUIButtons() {
        for (JButton button : buttons) {
            this.add(button);

        }
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
