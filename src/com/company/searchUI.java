package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class searchUI extends JFrame
{
    final static int width = 800, height = 500;

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    public searchUI()
    {
        frame.setSize(width, height);
        frame.add(panel);
        panel.setLayout(null);

        JLabel searchL = new JLabel("Search Terms : ");
        searchL.setBounds(20,20,100,20);
        panel.add(searchL);

        JTextField search = new JTextField(100);
        search.setBounds(120,20,250,20);
        panel.add(search);

        String[] terms = { "Title","Keywords", "Assignee"};

        final JComboBox<String> options = new JComboBox<String>(terms);
        options.setBounds(380,20,120,20);
        panel.add(options);

        JButton submit = new JButton("Search");
        submit.setBounds(520,20,100,20);
        panel.add(submit);


        frame.setVisible(true);
        
    }
}
