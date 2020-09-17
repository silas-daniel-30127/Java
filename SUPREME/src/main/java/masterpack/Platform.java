package masterpack;

import javax.swing.*;
import java.awt.*;

public class Platform extends JFrame {
    private Rectangle rectangle;
    private JButton rect;

    public Platform(Rectangle rectangle) throws HeadlessException {
        this.rectangle = rectangle;
        init();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rectangle.move(Moves.RIGHT, 200);
        rect_to_button();
    }

    private void init() {

        this.setSize(1000, 1000);
        this.setLocation(500, 10);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.rect = new JButton(rectangle.getName());
        this.rect.setBounds(200, 200, rectangle.getLat1(), rectangle.getLat2());
        this.add(rect);
    }

    private void rect_to_button() {
        //this.rect.setBounds(200 + rectangle.positionX, 200 + rectangle.positionY, rectangle.getLat1(), rectangle.getLat2());
        rect.move(200,310);
    }
}
