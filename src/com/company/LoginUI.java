package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginUI implements ActionListener {

    final static int width = 350, height = 200;

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel userLabel, passwordLabel, successLabel;
    private JTextField userText;
    private JPasswordField passwordField;
    private JButton loginButton;

    private LoginController loginController = new LoginController();
    private UserAccessDatabase uad;

    public LoginUI(UserAccessDatabase uad){
       prepareGUI();
       this.uad = uad;
    }

    private void prepareGUI(){

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(110, 20, 165,25);
        panel.add(userText);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(110, 50, 165,25);
        panel.add(passwordField);

        successLabel = new JLabel("");
        successLabel.setBounds(20, 110, 300, 25);
        panel.add(successLabel);
        panel.setToolTipText("Success");

        showLoginButton();

        frame.setVisible(true);
    }

    private void showLoginButton(){

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello");
                String userName = userText.getText();
                String password = passwordField.getText();

                boolean authenticate = loginController.authenticUser(uad, userName, password);

                if(authenticate){
                    successLabel.setText("Login Successful");
                }else{
                    successLabel.setText("Error. Username/password cannot be found.");
                }

            }
        });

        panel.add(loginButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
