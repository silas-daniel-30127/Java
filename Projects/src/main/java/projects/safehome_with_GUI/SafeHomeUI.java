package projects.safehome_with_GUI;

import javax.swing.*;
import java.awt.*;

public class SafeHomeUI extends JFrame {
    private final DoorLockController doorLockController;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    //enterPin
    private JTextField pinTextField;
    private JButton enterPinButton;
    private JTextField doorStatusTextField;
    private JLabel errorMessageLabel;
    //addTenant
    private JButton addTenantButton;
    private JTextField tenantNameToAddTextField;
    private JTextField tenantPinTextField;
    private JLabel addTenantMessageLabel;
    //removeTenant
    private JButton removeTenantButton;
    private JTextField tenantNameToRemoveTextField;
    private JLabel removeTenantLabel;

    public SafeHomeUI(DoorLockController doorLockController) {
        this.doorLockController = doorLockController;
        initFrame();
        initHandlers();
    }

    private void initFrame() {
        setTitle("SafeHome");

        setSize(300, 200);
        setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        //Create pages
        createPage1();
        createPage2();
        createPage3();

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("enterPin", panel1);
        tabbedPane.addTab("addTenant", panel2);
        tabbedPane.addTab("removeTenant", panel3);

        topPanel.add(tabbedPane, BorderLayout.CENTER);


    }

    private void createPage1() {
        panel1 = new JPanel();
        panel1.setLayout(null);

        JLabel label1 = new JLabel("Type pin:");
        label1.setBounds(10, 15, 150, 20);
        panel1.add(label1);

        pinTextField = new JTextField();
        pinTextField.setBounds(10, 35, 150, 20);
        panel1.add(pinTextField);

        this.enterPinButton = new JButton("Enter");
        this.enterPinButton.setBounds(180, 35, 75, 20);
        panel1.add(enterPinButton);

        JLabel label2 = new JLabel("Door status:");
        label2.setBounds(10, 60, 150, 20);
        panel1.add(label2);

        doorStatusTextField = new JTextField("CLOSE");
        doorStatusTextField.setBounds(180, 60, 75, 20);
        panel1.add(doorStatusTextField);

        this.errorMessageLabel = new JLabel();
        this.errorMessageLabel.setBounds(10, 85, 150, 20);
        this.errorMessageLabel.setVisible(false);
        panel1.add(errorMessageLabel);
    }

    private void createPage2() {
        panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel label1 = new JLabel("Tenant name:");
        label1.setBounds(10, 15, 150, 20);
        panel2.add(label1);

        tenantNameToAddTextField = new JTextField("");
        tenantNameToAddTextField.setBounds(90, 15, 125, 20);
        panel2.add(tenantNameToAddTextField);

        JLabel label2 = new JLabel("Tenant pin:");
        label2.setBounds(10, 40, 150, 20);
        panel2.add(label2);

        tenantPinTextField = new JTextField("");
        tenantPinTextField.setBounds(90, 40, 125, 20);
        panel2.add(tenantPinTextField);

        this.addTenantButton = new JButton("AddTenant");
        this.addTenantButton.setBounds(90, 65, 125, 20);
        panel2.add(addTenantButton);


        this.addTenantMessageLabel = new JLabel();
        this.addTenantMessageLabel.setBounds(10, 85, 150, 20);
        this.addTenantMessageLabel.setVisible(false);
        panel2.add(addTenantMessageLabel);
    }

    private void createPage3() {
        panel3 = new JPanel();
        panel3.setLayout(null);

        JLabel label1 = new JLabel("Tenant name:");
        label1.setBounds(10, 15, 150, 20);
        panel3.add(label1);

        tenantNameToRemoveTextField = new JTextField("");
        tenantNameToRemoveTextField.setBounds(90, 15, 125, 20);
        panel3.add(tenantNameToRemoveTextField);

        this.removeTenantButton = new JButton("RemoveTenant");
        this.removeTenantButton.setBounds(90, 65, 125, 20);
        panel3.add(removeTenantButton);

        this.removeTenantLabel = new JLabel();
        this.removeTenantLabel.setBounds(10, 85, 150, 20);
        this.removeTenantLabel.setVisible(false);
        panel3.add(removeTenantLabel);
    }

    private void initHandlers() {
        this.enterPinButton.addActionListener(e -> {
            String enteredPin = pinTextField.getText();
            try {
                DoorStatus status = doorLockController.enterPin(enteredPin);
                doorStatusTextField.setText(String.valueOf(status));
                setErrorMessageLabel("Pin ok !");
            } catch (InvalidPinException ex) {
                setErrorMessageLabel("Invalid pin !");
            } catch (TooManyAttemptsException ex) {
                setErrorMessageLabel("Too many attempts !");
            }
            this.pinTextField.setText("");
        });

        this.addTenantButton.addActionListener(e -> {
            String tenantName = tenantNameToAddTextField.getText();
            String tenantPin = tenantPinTextField.getText();
            try {
                this.doorLockController.addTenant(tenantPin, tenantName);
                setAddTenantMessageLabel("Tenant added!");
            } catch (TenantAlreadyExistsException ex) {
                setAddTenantMessageLabel("Tenant already exists!");
            }
        });

        this.removeTenantButton.addActionListener(e -> {
            String tenantName = tenantNameToRemoveTextField.getText();
            try {
                this.doorLockController.removeTenant(tenantName);
                setRemoveTenantMessageLabel("Tenant removed");
            } catch (TenantNotFoundException ex) {
                setRemoveTenantMessageLabel("Tenant not found!");
            }
        });
    }

    private void setErrorMessageLabel(String messageText) {
        errorMessageLabel.setText(messageText);
        errorMessageLabel.setVisible(true);
    }

    private void setAddTenantMessageLabel(String messageText) {
        addTenantMessageLabel.setText(messageText);
        addTenantMessageLabel.setVisible(true);
    }

    private void setRemoveTenantMessageLabel(String messageText) {
        removeTenantLabel.setText(messageText);
        removeTenantLabel.setVisible(true);
    }
}