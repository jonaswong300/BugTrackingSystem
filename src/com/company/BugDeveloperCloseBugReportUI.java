package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugDeveloperCloseBugReportUI implements ActionListener {

    JFrame form = new JFrame();
    JPanel panel = new JPanel();
    JTextField bugIDField;
    JTextField titleField;
    JTextArea remarksArea;
    JButton closeButton;

    String bugID, title, remarks;

    public BugDeveloperCloseBugReportUI(){
        System.out.println("At close bug UI");
        showForm();
    }

    public void showForm(){
        form.setSize(1000,590);
        form.add(panel);
        form.setTitle("Close Bug Report UI");
        panel.setLayout(null);

        JLabel bugLabel = new JLabel("Bug ID : " );
        bugLabel.setBounds(20, 20, 160, 25);
        panel.add(bugLabel);

        bugIDField = new JTextField(150);
        bugIDField.setBounds(200,20,400,25);
        panel.add(bugIDField);

        JLabel titleLabel = new JLabel("Title : ");
        titleLabel.setBounds(20, 60, 300, 25);
        panel.add(titleLabel);

        titleField = new JTextField(150);
        titleField.setBounds(200, 60, 400, 25);
        panel.add(titleField);

        JLabel remarksLabel = new JLabel("Remarks : ");
        remarksLabel.setBounds(20, 100, 200, 25);
        panel.add(remarksLabel);

        remarksArea = new JTextArea();
        remarksArea.setBounds(200, 105, 600, 300);
        panel.add(remarksArea);

        JLabel closeLabel = new JLabel("Close Bug Report: ");
        closeLabel.setBounds(20, 410, 400, 100);
        panel.add(closeLabel);

        closeButton = new JButton("Close");
        closeButton.setBounds(200, 430, 100,50);
        closeButton.addActionListener(this);
        panel.add(closeButton);

        form.setVisible(true);
    }
    public void closeBugRequest(String bugID, String title, String remarks){
        BugDeveloperCloseBugReportController bdcbrc = new BugDeveloperCloseBugReportController();
        bdcbrc.closeReport(bugID, title, remarks);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        closeBugRequest(bugIDField.getText(), titleField.getText(), remarksArea.getText());
    }


}
