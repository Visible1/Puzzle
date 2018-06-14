package accountSetup;

import javax.swing.*;

public class Login {
    public static void main(String[] a) {
        dbSignUp signUp = new dbSignUp();
        signUp.setBounds(10, 10, 370, 600);
        signUp.setVisible(true);
        signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUp.setResizable(false);
    }
}
