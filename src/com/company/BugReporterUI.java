package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

class BugReporterUI extends JFrame
{
    final static int width = 800, height = 500;

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    public BugReporterUI()
    {
        frame.setSize(width, height);
        frame.add(panel);
        panel.setLayout(null);

        // abit inefficient but the gui will have 3 buttons. 
        //each button will be assigned to a class where further functions will be done

        JButton addBugReport = new JButton("Add Bug Report");
        addBugReport.setBounds(60, 80, 200, 100);
        addBugReport.addActionListener(new addBugReport());
        panel.add(addBugReport);

        JButton trackBug = new JButton("Track Reported Bugs");
        trackBug.setBounds(300, 80, 200, 100);
        trackBug.addActionListener(new trackBugReport());
        panel.add(trackBug);

        JButton searchBugs = new JButton("Search for Bugs");
        searchBugs.setBounds(540, 80, 200, 100);
        searchBugs.addActionListener(new searchForBugs());
        panel.add(searchBugs);
        
        JLabel user = new JLabel("Welcome Bug Reporter");
        user.setBounds(300, 300, 200, 100);
        panel.add(user);

        frame.setVisible(true);
        
    }
}

class addBugReport implements ActionListener
{
    userAddBugsUI addbugUI = new userAddBugsUI();
    public addBugReport()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {   
        addbugUI.showForm();
    }
}

class trackBugReport implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    
}

class searchForBugs implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    
}