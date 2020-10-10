package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginUI implements ActionListener {

    final static int width = 350, height = 200;

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static  JButton loginButton;
    private static JLabel successLabel;

    public static void main (String [] args){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165,25);
        panel.add(userText);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165,25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new LoginUI());
        panel.add(loginButton);

        successLabel = new JLabel("");
        successLabel.setBounds(10, 110, 300, 25);
        panel.add(successLabel);
        panel.setToolTipText("Success");

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello");
        String userName = userText.getText();
        String password = passwordField.getText();

        successLabel.setText("Login Successful");
    }

}
