package accountSetup;

import javax.swing.*;

public class Login {
    public static void main(String[] a) {
    LoginFrame loginFrame = new LoginFrame();
    loginFrame.setTitle("Login Form");
    loginFrame.setVisible(true);
    loginFrame.setBounds(10, 10, 370, 600);
    loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loginFrame.setResizable(false);
    }
}
