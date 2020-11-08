package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UserAddBugsUI implements ActionListener
{

    JFrame form = new JFrame();
    JPanel panel = new JPanel();
    JTextField titleText, keywordsField;
    JTextArea descriptionArea;
    String title, keywords, description;
    JButton submit;

    String[] terms = {"0", "1", "2", "3"};
    final JComboBox<String> severity = new JComboBox<String>(terms);

    SeverityLevel level;
   
    public UserAddBugsUI()
    {
        System.out.println("At add bugs UI");
        showForm();
    }

    public void showForm()
    {

        form.setSize(1000,590);
        form.add(panel);
        panel.setLayout(null);
        
        JLabel titleLabel = new JLabel("Title : " );
        titleLabel.setBounds(20, 20, 160, 25);
        panel.add(titleLabel);

        titleText = new JTextField(150);
        titleText.setBounds(200,20,400,25);
        panel.add(titleText);

        JLabel keywordLabel = new JLabel("Add some keywords : ");
        keywordLabel.setBounds(20, 60, 300, 25);
        JLabel warningKeys = new JLabel("(Make sure to seperate with only a whitespace, eg. search files keyboard)");
        warningKeys.setBounds(20,85,500,25);
        panel.add(keywordLabel);
        panel.add(warningKeys);

        keywordsField = new JTextField(150);
        keywordsField.setBounds(200, 60, 400, 25);
        panel.add(keywordsField);

        JLabel severityLabel = new JLabel("Severity Level: ");
        severityLabel.setBounds(20, 130, 160, 25);
        panel.add(severityLabel);

        severity.setFont(severity.getFont().deriveFont(18.0f));
        severity.setBounds(200,130,400,25);
        panel.add(severity);

        JLabel descriptLabel = new JLabel("Description : ");
        descriptLabel.setBounds(20, 170, 200, 25);
        panel.add(descriptLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(200, 170, 600, 300);
        panel.add(descriptionArea);

        JLabel submitLabel = new JLabel("Submit your bug report : ");
        submitLabel.setBounds(20, 440, 400, 100);
        panel.add(submitLabel);

        submit = new JButton("Submit");
        submit.setBounds(200, 480, 100,50);
        submit.addActionListener(this);
        panel.add(submit);


        form.setVisible(true);

    }

    public void sendBugRequest(String title, String keywords, String description, SeverityLevel level)
    {
        UserAddBugController addBugController = new UserAddBugController(title, keywords, description, level);
        if(addBugController.checkEmpty())
        {
            JOptionPane.showMessageDialog(form, "Ensure that you have filled in the bug report and did not leave any empty text fields.", 
                                            "Form empty", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            addBugController.addBugsDetails();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // on submit, the details will be sent to useraddbugcontroller
        title = titleText.getText();
        keywords = keywordsField.getText();
        description = descriptionArea.getText();

        Object selectedItem = severity.getSelectedItem();
        if ("0".equals(selectedItem)) {
            level = SeverityLevel.LOW;
        } else if ("1".equals(selectedItem)) {
            level = SeverityLevel.MEDIUM;
        } else if ("2".equals(selectedItem)) {
            level = SeverityLevel.HIGH;
        } else if ("3".equals(selectedItem)) {
            level = SeverityLevel.CRITICAL;
        } else {
            level = null;
        }
        sendBugRequest(title, keywords, description, level);
		form.dispose();		
		System.out.println("Frame Closed.");		
    }
}
