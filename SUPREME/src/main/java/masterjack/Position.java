package masterjack;

import javax.swing.*;

public class Position extends JFrame {

    public Position(String name) {
        super(name);

        JTextField newItemField;
        JList itemsList;
        JButton addButton;
        JButton removeButton;

        getContentPane().setLayout(null);

//The text field
        newItemField = new JTextField();
        newItemField.setLocation(12, 12);
        newItemField.setSize(150, 30);
        add(newItemField);

//The Add button
        addButton = new JButton("Add");
        addButton.setMnemonic('A');
        addButton.setLocation(174, 12);
        addButton.setSize(100, 30);
        add(addButton);

//The List
        itemsList = new JList();
        JScrollPane scrollPane = new JScrollPane(itemsList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setLocation(12, 45);
        scrollPane.setSize(150, 150);
        add(scrollPane);

//The Remove button
        removeButton = new JButton("Remove");
        removeButton.setMnemonic('R');
        removeButton.setLocation(174, 45);
        removeButton.setSize(100, 30);
        add(removeButton);
    }

    public static void main(String[] args) {
        JFrame frame = new Position("NULL Example");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(286, 230);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
