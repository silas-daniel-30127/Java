package hibernate.Access_and_update_a_Staff_table;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonUI extends JPanel {

    private TextField id;
    private TextField lastName;
    private TextField firstName;
    private TextField mi;
    private TextField address;
    private TextField city;
    private TextField state;
    private TextField telephone;

    private final JButton insertButton = new JButton(("Insert"));
    private final JButton updateButton = new JButton(("Update"));
    private final JButton clearButton = new JButton(("Clear"));
    private final JButton viewButton = new JButton(("View"));
    private final Staff staffMember = new Staff();

    public PersonUI() {
        setBorder(new TitledBorder(new EtchedBorder(), "Person Details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(viewButton);
        viewButton.addActionListener(new ButtonHandler());

        panel.add(insertButton);
        insertButton.addActionListener(new ButtonHandler());

        panel.add(updateButton);
        updateButton.addActionListener(new ButtonHandler());

        panel.add(clearButton);
        clearButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();

        id = new TextField(9);
        lastName = new TextField(15);
        firstName = new TextField(15);
        mi = new TextField(1);
        address = new TextField(20);
        city = new TextField(20);
        state = new TextField(2);
        telephone = new TextField(10);

        panel.add(new JLabel("ID"), "align label");
        panel.add(id, "wrap");
        panel.add(new JLabel("Last Name"), "align label");
        panel.add(lastName, "wrap");
        panel.add(new JLabel("First Name"), "align label");
        panel.add(firstName, "wrap");
        panel.add(new JLabel("MI"), "align label");
        panel.add(mi, "wrap");
        panel.add(new JLabel("Address"), "align label");
        panel.add(address, "wrap");
        panel.add(new JLabel("City"), "align label");
        panel.add(city, "wrap");
        panel.add(new JLabel("State"), "align label");
        panel.add(state, "wrap");
        panel.add(new JLabel("Phone"), "align label");
        panel.add(telephone, "wrap");
        return panel;
    }

    private Person getFieldData() {
        Person p = new Person();
        p.setId(Integer.parseInt(id.getText()));
        p.setFirstName(firstName.getText());
        p.setLastName(lastName.getText());
        p.setAddress(address.getText());
        p.setCity(city.getText());
        p.setMi(mi.getText());
        p.setState(state.getText());
        p.setTelephone(telephone.getText());
        return p;
    }

    private void setFieldData(Person p) {
        id.setText(String.valueOf(p.getId()));
        firstName.setText(p.getFirstName());
        lastName.setText(p.getLastName());
        telephone.setText(p.getTelephone());
        mi.setText(p.getMi());
        address.setText(p.getAddress());
        city.setText(p.getCity());
        state.setText(p.getState());
    }

    private boolean isEmptyFieldData() {
        return (firstName.getText().trim().isEmpty()
                && lastName.getText().trim().isEmpty()
                && telephone.getText().trim().isEmpty()
                && mi.getText().trim().isEmpty()
                && city.getText().trim().isEmpty()
                && address.getText().trim().isEmpty()
                && state.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Person p = new Person();
            if (!id.getText().equals("")) {
                p = getFieldData();
            }
            switch (e.getActionCommand()) {
                case "Insert":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null, "Cannot create an empty record");
                        System.out.println("Add some data!");
                        return;
                    }
                    if (staffMember.insert(p) != null) {
                        JOptionPane.showMessageDialog(null, "New person created successfully.");
                        System.out.println("New staff with id = " + id.getText() + " created successfully.");
                    }
                    break;
                case "View":
                    if (id.getText().length() != 0) {
                        setFieldData(staffMember.getPersonById(id.getText()));
                        System.out.println("View data!");
                    } else System.out.println("Id not compatible!");

                    break;
                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null, "Cannot update an empty record");
                        System.out.println("Add some data!");
                        return;
                    }
                    if (staffMember.update(p) != null) {
                        JOptionPane.showMessageDialog(null, "Person with ID:" + p.getId() + " is updated successfully");
                        System.out.println("Person with ID:" + p.getId() + " is updated successfully");
                    }
                    break;
                case "Clear":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null, "Cannot delete an empty record");
                        System.out.println("Cannot delete an empty record");
                        return;
                    }
                    p = staffMember.getPersonById(id.getText());
                    staffMember.delete(p.getId());
                    JOptionPane.showMessageDialog(null, "Person with ID:" + p.getId() + " is deleted successfully");
                    System.out.println("Person with ID:" + p.getId() + " is deleted successfully");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid command");
            }
        }
    }
}