package projects.airTrafficControlSystem;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class UI_ATC extends JFrame {
    private final ATC ATC;
    private JButton takeoffCommandButton;
    private JTextField altitudeTextField;
    private JPanel panel1;
    private JPanel panel2;
    //send command
    private JTextField idTextField;
    private JButton landCommandButton;
    private JLabel errorMessageLabel;
    //add aircraft
    private JButton addAircraftButton;
    private JTextField aircraftIdToAddTextField;
    private JLabel addAircraftMessageLabel;
    //show aircraft
    private JButton showAircraftButton;
    private JTextArea showAircraftTextField;


    public UI_ATC(ATC atc) {
        this.ATC = atc;
        initFrame();
        initHandlers();
    }

    private void initFrame() {
        setTitle("Aircraft Traffic Controller");

        setSize(700, 200);
        setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        //Create pages
        createPage1();
        createPage2();

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("sendCommand", panel1);
        tabbedPane.addTab("addAircraft", panel2);

        topPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    private void createPage1() {
        panel1 = new JPanel();
        panel1.setLayout(null);

        JLabel label1 = new JLabel("Type id:");
        label1.setBounds(10, 15, 150, 20);
        panel1.add(label1);

        idTextField = new JTextField();
        idTextField.setBounds(10, 35, 150, 20);
        panel1.add(idTextField);

        this.landCommandButton = new JButton("LAND");
        this.landCommandButton.setBounds(180, 35, 85, 20);
        panel1.add(landCommandButton);

        JLabel label2 = new JLabel("Altitude:");
        label2.setBounds(10, 60, 70, 20);
        panel1.add(label2);

        this.altitudeTextField = new JTextField();
        this.altitudeTextField.setBounds(80, 60, 80, 20);
        panel1.add(this.altitudeTextField);

        this.takeoffCommandButton = new JButton("TAKEOFF");
        this.takeoffCommandButton.setBounds(180, 60, 85, 20);
        panel1.add(this.takeoffCommandButton);

        this.errorMessageLabel = new JLabel();
        this.errorMessageLabel.setBounds(10, 85, 150, 20);
        this.errorMessageLabel.setVisible(false);
        panel1.add(errorMessageLabel);

        this.showAircraftButton = new JButton("Show");
        this.showAircraftButton.setBounds(300, 15, 80, 40);
        panel1.add(this.showAircraftButton);

        this.showAircraftTextField = new JTextArea();
        this.showAircraftTextField.setBounds(400, 15, 250, 100);
        this.showAircraftTextField.setColumns(2);
        panel1.add(this.showAircraftTextField);
    }

    private void createPage2() {
        panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel label1 = new JLabel("Aircraft id:");
        label1.setBounds(10, 15, 150, 20);
        panel2.add(label1);

        aircraftIdToAddTextField = new JTextField("");
        aircraftIdToAddTextField.setBounds(90, 15, 125, 20);
        panel2.add(aircraftIdToAddTextField);

        this.addAircraftButton = new JButton("Add");
        this.addAircraftButton.setBounds(90, 65, 125, 20);
        panel2.add(addAircraftButton);


        this.addAircraftMessageLabel = new JLabel();
        this.addAircraftMessageLabel.setBounds(10, 85, 150, 20);
        this.addAircraftMessageLabel.setVisible(false);
        panel2.add(addAircraftMessageLabel);
    }


    private void initHandlers() {
        this.landCommandButton.addActionListener(e -> {
            String id = this.idTextField.getText();
            try {
                ATC.sendCommand(id, new LandCommand());
                setErrorMessageLabel(id + " is landing!");
            } catch (NotFoundAirCraft notFoundAirCraft) {
                setErrorMessageLabel(id + " not found!");
            }
            this.showAircraftButton.setContentAreaFilled(false);
        });

        this.takeoffCommandButton.addActionListener(e -> {
            String id = this.idTextField.getText();
            int altitude = Integer.parseInt(altitudeTextField.getText());
            try {
                ATC.sendCommand(id, new TakeoffCommand(altitude));
                setErrorMessageLabel(id + " is taking off!");
            } catch (NotFoundAirCraft notFoundAirCraft) {
                setErrorMessageLabel(id + " not found!");
            }
        });

        this.addAircraftButton.addActionListener(e -> {
            String id = this.aircraftIdToAddTextField.getText();
            try {
                this.ATC.addAircraft(id);
                setAddTenantMessageLabel(id + " added!");
            } catch (AlreadyExistsAirCraft alreadyExistsAirCraft) {
                setAddTenantMessageLabel(id + "already exists !");
            }
        });

        this.showAircraftButton.addActionListener(e -> {
            String text = ATC.toString();
            Random random = new Random();
            int r1 = random.nextInt(256);
            int r2 = random.nextInt(256);
            int r3 = random.nextInt(256);
            text = text + " " + r1 + " " + r2 + " " + r3;
            this.showAircraftButton.setContentAreaFilled(true);
            this.showAircraftButton.setBackground(new Color(r1, r2, r3));
            this.showAircraftTextField.setText(text);
        });
    }

    private void setErrorMessageLabel(String messageText) {
        errorMessageLabel.setText(messageText);
        errorMessageLabel.setVisible(true);
    }

    private void setAddTenantMessageLabel(String messageText) {
        addAircraftMessageLabel.setText(messageText);
        addAircraftMessageLabel.setVisible(true);
    }

}