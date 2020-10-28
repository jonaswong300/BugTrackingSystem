package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugDeveloperFixBugReportUI implements ActionListener {

    JFrame form = new JFrame();
    JPanel panel = new JPanel();
    JTextField titleText;
    JTextField keywordsField;
    JTextArea solutionArea;
    JButton submit;

    public BugDeveloperFixBugReportUI(){
        System.out.println("At fixed bug UI");
        showForm();
    }

    public void showForm(){
        form.setSize(1000,590);
        form.add(panel);
        form.setTitle("Fix Bug Report UI");
        panel.setLayout(null);

        JLabel bugLabel = new JLabel("Bug ID : " );
        bugLabel.setBounds(20, 20, 160, 25);
        panel.add(bugLabel);

        titleText = new JTextField(150);
        titleText.setBounds(200,20,400,25);
        panel.add(titleText);

        JLabel titleLabel = new JLabel("Title : ");
        titleLabel.setBounds(20, 60, 300, 25);
        panel.add(titleLabel);

        keywordsField = new JTextField(150);
        keywordsField.setBounds(200, 60, 400, 25);
        panel.add(keywordsField);

        JLabel solutionLabel = new JLabel("Solution : ");
        solutionLabel.setBounds(20, 100, 200, 25);
        panel.add(solutionLabel);

        solutionArea = new JTextArea();
        solutionArea.setBounds(200, 105, 600, 300);
        panel.add(solutionArea);

        JLabel submitLabel = new JLabel("Submit your solution : ");
        submitLabel.setBounds(20, 410, 400, 100);
        panel.add(submitLabel);

        submit = new JButton("Submit");
        submit.setBounds(200, 430, 100,50);
        submit.addActionListener(this);
        panel.add(submit);

        form.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
