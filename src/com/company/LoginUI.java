package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginUI implements ActionListener {

    final static int width = 500, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private JLabel welcomeLabel, userLabel, passwordLabel, successLabel;
    private JTextField userText;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginUI()
    {
        prepareGUI();
    }


    private void prepareGUI(){

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Bug Tracking System");
        frame.add(panel);
        panel.setLayout(null);

        //welcomeLabel = new JLabel("Welcome to 007's Bug Tracking System");
        //welcomeLabel.setBounds(10, 5, 80, 25);
        panel.add(welcomeLabel);

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
        successLabel.setToolTipText("Success");
        panel.add(successLabel);

        showLoginButton();

        frame.setVisible(true);
    }

    private void showLoginButton(){

        LoginController lc = new LoginController();

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                String password = passwordField.getText();

                boolean authenticate = lc.authenticateUser(userName, password);
                if(authenticate){
                    successLabel.setText("Login Successful for " + userName);
                    lc.userRoleGUI(userName);
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
