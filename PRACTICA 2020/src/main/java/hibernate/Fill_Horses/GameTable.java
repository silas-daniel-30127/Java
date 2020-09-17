package hibernate.Fill_Horses;

import javax.swing.*;
import java.awt.*;

public class GameTable extends JFrame {
    private final JButton[][] buttons = new JButton[8][8];
    private int cnt = 0;
    private boolean lost = false;
    private final Icon img;

    public GameTable() {
        img = new ImageIcon("horse.jpg");
        addButtons();
        addActionListeners();
        editFrame();
        restart();
    }

    private void editFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Horse Game");
        setLayout(new GridLayout(8, 8));
        setSize(500, 500);
        setBackground(Color.gray);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addActionListeners() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(
                        e -> {
                            lost = true;
                            if (this.buttons[finalI][finalJ].isEnabled()) {
                                removeHorse();
                                this.buttons[finalI][finalJ].setIcon(img);
                                cnt++;
                                this.buttons[finalI][finalJ].setText("" + cnt);
                                removeTrack();
                                this.buttons[finalI][finalJ].setEnabled(true);
                                if (isOnTable(finalI - 1, finalJ - 2)) {
                                    this.buttons[finalI - 1][finalJ - 2].setEnabled(true);
                                    lost = false;
                                }
                                if (isOnTable(finalI - 2, finalJ - 1)) {
                                    lost = false;
                                    this.buttons[finalI - 2][finalJ - 1].setEnabled(true);
                                }
                                if (isOnTable(finalI + 1, finalJ - 2)) {
                                    lost = false;
                                    this.buttons[finalI + 1][finalJ - 2].setEnabled(true);
                                }
                                if (isOnTable(finalI + 2, finalJ - 1)) {
                                    lost = false;
                                    this.buttons[finalI + 2][finalJ - 1].setEnabled(true);
                                }
                                if (isOnTable(finalI - 1, finalJ + 2)) {
                                    lost = false;
                                    this.buttons[finalI - 1][finalJ + 2].setEnabled(true);
                                }
                                if (isOnTable(finalI - 2, finalJ + 1)) {
                                    lost = false;
                                    this.buttons[finalI - 2][finalJ + 1].setEnabled(true);
                                }
                                if (isOnTable(finalI + 1, finalJ + 2)) {
                                    lost = false;
                                    this.buttons[finalI + 1][finalJ + 2].setEnabled(true);
                                }
                                if (isOnTable(finalI + 2, finalJ + 1)) {
                                    lost = false;
                                    this.buttons[finalI + 2][finalJ + 1].setEnabled(true);
                                }
                                if (cnt == 64) {
                                    JOptionPane.showMessageDialog(null, "You win!");
                                } else if (lost) {
                                    JOptionPane.showMessageDialog(null, "You lost with " + cnt + " score!");
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                    restart();
                                }
                            }
                        }
                );
            }
        }
    }

    private void restart() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setBackground(null);
                buttons[i][j].setIcon(null);
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("");
                cnt = 0;
                lost = false;
            }
        }
    }

    private void removeHorse() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j].getIcon() == img) {
                    buttons[i][j].setIcon(null);
                    buttons[i][j].setBackground(Color.darkGray);
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    private boolean isOnTable(int i, int j) {
        return i >= 0 && i < 8 && j >= 0 && j < 8 && buttons[i][j].getText().equals("");
    }

    private void removeTrack() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void addButtons() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new JButton("");
                add(buttons[i][j]);
            }
        }
    }
}
