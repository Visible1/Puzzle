package accountSetup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    Container container = getContentPane();
    private JTextField emailField = new JTextField();
    private JLabel emailLabel = new JLabel("Email:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField passwordField = new JPasswordField();
    private JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
    private JButton loginButton = new JButton("Login");
    private JButton createAccountButton = new JButton("Create Account");



    // Constructor for Login Class

    public LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponents();
        addActionEvent();

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


    // *****
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String emailTxt;
            String passwordTxt;
            emailTxt = emailField.getText();
            passwordTxt = passwordField.getText();
            if (emailTxt.equalsIgnoreCase("mehtab") && passwordTxt.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Email or Password");
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
}
