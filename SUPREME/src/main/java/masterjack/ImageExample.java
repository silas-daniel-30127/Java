package masterjack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.io.*;

public class ImageExample extends JFrame implements ActionListener {

    JButton bLoad;
    final JFileChooser fc = new JFileChooser();
    ImagePanel p;

    public ImageExample() {
        setTitle("Image viewer");
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        bLoad = new JButton("Load image");
        bLoad.addActionListener(this);

        p = new ImagePanel();
        p.setPreferredSize(new Dimension(400, 400));
        p.setBorder(new LineBorder(Color.BLACK));

        add(p, BorderLayout.CENTER);
        add(bLoad, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //load image from file and display it on screen
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            p.displayImage(file);
        } else {
            System.out.println("Error loading file.");
        }
    }

    public static void main(String[] args) {
        new ImageExample();
    }

    class ImagePanel extends JPanel {
        File f;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (f != null) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = toolkit.getImage(f.getAbsolutePath());
                g.drawImage(image, 0, 0, this);
            }
        }

        public void displayImage(File f) {
            this.f = f;
            repaint();
        }
    }
}