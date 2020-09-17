package hibernate.Login_dialog;

import hibernate.Login_dialog.exceptions.WrongUsernameOrPasswordException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class UserUI extends JFrame {
    private JPanel login;
    private JPanel register;
    private JScrollPane namesList;
    private JLabel loginLabel;
    private JLabel registerLabel;
    private JTextField field;
    private JButton registerButton;
    private JTextField registerFieldPass;
    private JTextField registerField;
    private JPasswordField fieldPass;
    private JButton loginButton;

    private final UserBeam userBeam = new UserBeam();

    public UserUI() {
        create_panel();
        initialize_frame();
        initHandlers();
    }

    private void create_panel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        createPage1();
        createPage2();
        create_page3();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("login", login);
        tabbedPane.addTab("register", register);
        tabbedPane.addTab("namesList", namesList);
        topPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    private void initialize_frame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Authentication");
        setSize(500, 250);
        setBackground(Color.gray);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createPage1() {
        register = new JPanel();

        register.setBorder(new TitledBorder(new EtchedBorder(), "Register"));
        register.setLayout(new GridLayout(4, 4));

        JLabel label1 = new JLabel("Username:");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        register.add(label1, 0);

        registerField = new JTextField();
        registerField.setHorizontalAlignment(SwingConstants.CENTER);
        register.add(registerField, 1);

        JLabel label2 = new JLabel("Password:");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        register.add(label2, 2);

        registerFieldPass = new JTextField();
        registerFieldPass.setHorizontalAlignment(SwingConstants.CENTER);
        register.add(registerFieldPass, 3);

        registerLabel = new JLabel();
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        register.add(registerLabel, 4);

        registerButton = new JButton("Register");
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        register.add(registerButton, 5);
    }

    public void createPage2() {
        login = new JPanel();

        login.setBorder(new TitledBorder(new EtchedBorder(), "Login"));
        login.setLayout(new GridLayout(4, 4));

        JLabel label1 = new JLabel("Username:");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        login.add(label1, 0);

        field = new JTextField();
        field.setHorizontalAlignment(SwingConstants.CENTER);
        login.add(field, 1);

        JLabel label2 = new JLabel("Password:");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        login.add(label2, 2);

        fieldPass = new JPasswordField();
        fieldPass.setHorizontalAlignment(SwingConstants.CENTER);
        login.add(fieldPass, 3);

        loginLabel = new JLabel();
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        login.add(loginLabel, 4);

        loginButton = new JButton("Log in");
        login.add(loginButton, 5);
    }

    public void create_page3() {
        namesList = new JScrollPane();
        namesList = userBeam.addNames();
        namesList.setBorder(new TitledBorder(new EtchedBorder(), "Names"));
    }

    private void setLoginLabel(String mess) {
        this.loginLabel.setText(mess);
        this.loginLabel.setVisible(true);
    }

    private void setRegisterLabel(String mess) {
        this.registerLabel.setVisible(true);
        this.registerLabel.setText(mess);
    }

    private User getLoginFieldData() {
        User user = new User();
        user.setName(this.field.getText());
        user.setPass(String.valueOf(this.fieldPass.getPassword()));
        return user;
    }

    private User getRegisterFieldData() {
        User user = new User();
        user.setName(this.registerField.getText());
        user.setPass(this.registerFieldPass.getText());
        return user;
    }

    private void initHandlers() {
        this.registerButton.addActionListener(e -> {
            User user = getRegisterFieldData();
            if (userBeam.updateData(user.getName(), user.getPass())) {
                this.registerLabel.setForeground(Color.green);
                setRegisterLabel("Successfully registered!");
                JOptionPane.showMessageDialog(null, "Successfully registered!");
            } else {
                this.registerLabel.setForeground(Color.red);
                setRegisterLabel("Already exists username!");
                JOptionPane.showMessageDialog(null, "Already exists username!");
            }
        });

        this.loginButton.addActionListener(e -> {
            User user = getLoginFieldData();
            try {
                if (userBeam.login(user.getName(), user.getPass())) {
                    this.loginLabel.setForeground(Color.green);
                    setLoginLabel("Successfully logged in!");
                    JOptionPane.showMessageDialog(null, "Successfully logged in!");

                } else {
                    this.loginLabel.setForeground(Color.red);
                    setLoginLabel("Incorrect username or password!");
                    throw new WrongUsernameOrPasswordException();

                }
            } catch (WrongUsernameOrPasswordException w) {
                JOptionPane.showMessageDialog(null, "Wrong username or password!");
            }

        });
    }

}