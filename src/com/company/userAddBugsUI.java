package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class userAddBugsUI extends JFrame implements ActionListener
{

    JFrame form = new JFrame();
    JPanel panel = new JPanel();
    JTextField titleText;
    JTextField keywordsField;
    JTextArea descriptionArea;
    String title;
    String keywords;
    String description;
    JButton submit;
   
    public userAddBugsUI()
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
        panel.add(keywordLabel);

        keywordsField = new JTextField(150);
        keywordsField.setBounds(200, 60, 400, 25);
        panel.add(keywordsField);

        JLabel descriptLabel = new JLabel("Description : ");
        descriptLabel.setBounds(20, 100, 200, 25);
        panel.add(descriptLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(200, 105, 600, 300);
        panel.add(descriptionArea);

        JLabel submitLabel = new JLabel("Submit your bug report : ");
        submitLabel.setBounds(20, 410, 400, 100);
        panel.add(submitLabel);

        submit = new JButton("Submit");
        submit.setBounds(200, 430, 100,50);
        submit.addActionListener(this);
        panel.add(submit);


        form.setVisible(true);

    }

    public void sendBugRequest(String title, String keywords, String description)
    {
        userAddBugController addBugController = new userAddBugController(title, keywords, description);
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
        sendBugRequest(title, keywords, description);
		form.dispose();		
		System.out.println("Frame Closed.");		
    }
}
