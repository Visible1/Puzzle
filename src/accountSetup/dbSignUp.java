package accountSetup;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

// We need to import the java.sql package to use JDBC
// for reading from the command line
// for the login window

public class dbSignUp extends JFrame implements ActionListener {


    private Connection con;

    Container container = getContentPane();

    // components of the login window
    private JLabel usernameLabel = new JLabel("Username: ");
    private JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password: ");
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Database Login");

    // user is allowed 3 login attempts
    private int loginAttempts = 0;



    public dbSignUp() {

        setLayoutManager();
        setLocationAndSize();
        addComponents();
        addActionEvens();

        // replace password with *****
        passwordField.setEchoChar('*');

        // place the cursor in the text field for the username
        usernameField.requestFocus();

        loadDriver();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        usernameLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        usernameField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(100, 300, 150, 30);
    }

    public void addComponents() {
        container.add(usernameLabel);
        container.add(usernameField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(loginButton);
    }

    public void addActionEvens() {
        passwordField.addActionListener(this);
        loginButton.addActionListener(this);
    }


    public void loadDriver() {
        try {
            // Load the Oracle JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            // may be oracle.jdbc.driver.OracleDriver as of Oracle 11g
        }
        catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.exit(-1);
        }
    }
    /*
     * connects to Oracle database named ug using user supplied username and password
     */
    private boolean connect(String username, String password) {
        String connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";

        try {
            con = DriverManager.getConnection(connectURL,username,password);
            return true;
        }
        catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
            return false;
        }
    }


    /*
     * event handler for login window
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {

           if (connect(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
               // if the username and password are valid,
               // remove the login window and display a text menu
               this.dispose();
               LoginFrame userLogin = new LoginFrame(con);
               userLogin.setTitle("Login Form");
               userLogin.setVisible(true);
               userLogin.setBounds(10, 10, 370, 600);
               userLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               userLogin.setResizable(false);
           }

            else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials, Try Again");
                loginAttempts++;

                if (loginAttempts >= 3) {
                    System.exit(-1);
                }
                else {
                    // clear the password
                    passwordField.setText("");
                }
            }
        }

    }
}

