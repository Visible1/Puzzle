package accountSetup;

// We need to import the java.sql package to use JDBC

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener {

    private Connection con;

    Container container = getContentPane();
    private JTextField emailField = new JTextField();
    private JLabel emailLabel = new JLabel("Email:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField passwordField = new JPasswordField();
    private JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
    private JButton loginButton = new JButton("Login");
    private JButton createAccountButton = new JButton("Create Account");

    // user is allowed 3 login attempts
    private int loginAttempts = 0;



    // Constructor for Login Class
    //   - creates login frame
    //   - loads JDBC driver
    //   - connects to Oracle database ug

    public LoginFrame(Connection con) {
        this.con = con;

        // setup for login frame
        setLayoutManager();
        setLocationAndSize();
        addComponents();
        addActionEvent();
        emailField.requestFocus();


    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        emailLabel.setBounds(50,150,100,30);
        passwordLabel.setBounds(50,220,100,30);
        emailField.setBounds(150,150,150,30);
        passwordField.setBounds(150,220,150,30);
        showPasswordCheckBox.setBounds(150,250,150,30);
        loginButton.setBounds(50,300,100,30);
        createAccountButton.setBounds(200,300,150,30);
    }

    public void addComponents() {
        container.add(emailLabel);
        container.add(emailField);
        container.add(passwordField);
        container.add(passwordLabel);
        container.add(showPasswordCheckBox);
        container.add(loginButton);
        container.add(createAccountButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        showPasswordCheckBox.addActionListener(this);
    }

//    public void loadDriver() {
//        try {
//            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//        }
//        catch (SQLException ex) {
//            System.out.println("Message: " + ex.getMessage());
//            System.exit(-1);
//        }
//    }

//    private void connect() {
//        String connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";
//        String username = "ora_u5b1b";
//        String password = "a24649162";
//        try {
//            con = DriverManager.getConnection(connectURL, username, password);
//            System.out.println("\nConnected to Oracle!");
//        }
//        catch (SQLException ex) {
//            System.out.println("Message: " + ex.getMessage());
//        }
//    }


    // *****
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            if (authenticate(emailField.getText(), String.valueOf(passwordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                // ****************************
                //redirect();
                // ****************************
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Email or Password");
                loginAttempts++;

                if (loginAttempts >= 3) {
                    System.exit(-1);
                }
                else {
                    emailField.setText("");
                    passwordField.setText("");
                }
            }

        }

        if (e.getSource() == createAccountButton) {
            emailField.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == showPasswordCheckBox) {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('*');
            }
        }
   }

    public boolean authenticate(String email, String password) {
        boolean valid = false;
        PreparedStatement pState;
        ResultSet rs;

        try {
            pState = con.prepareStatement("SELECT * FROM APP_USER WHERE NAME=? and PASSWORD=?");

            rs = pState.executeQuery("SELECT * FROM APP_USER");
            //JOptionPane.showMessageDialog(this, resultSet);
            System.out.println("Executed query");




            while (rs.next()) {
                System.out.println("here");
                String dbEmail;
                String dbPassword;
                dbEmail = rs.getString("EMAIL");
                dbPassword = rs.getString("PASSWORD");

//                JOptionPane.showMessageDialog(this, dbEmail);
//                JOptionPane.showMessageDialog(this, dbPassword);

                if (dbEmail.equals(email) && dbPassword.equals(password)) {
                    valid = true;
                }
            }
            pState.close();
        }

        catch (SQLException se) {
                System.out.println("Message: " + se.getMessage());
                System.exit(-1);
        }

        return valid;
    }
}
