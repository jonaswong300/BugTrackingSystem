package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugReporterUI
{
    final static int width = 800, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    public BugReporterUI()
    {
        showForm();
    }

    public void showForm(){
        frame.setSize(width, height);
        frame.add(panel);
        panel.setLayout(null);

        // abit inefficient but the gui will have 3 buttons.
        //each button will be assigned to a class where further functions will be done

        JLabel user = new JLabel("Welcome Bug Reporter");
        user.setBounds(330, 10, 200, 100);
        panel.add(user);

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

        frame.setVisible(true);
    }
}

class addBugReport implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {   
        userAddBugsUI addbugUI = new userAddBugsUI();
    }
}

class trackBugReport implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        userTrackBugUI trackUI = new userTrackBugUI();
    }
    
}

class searchForBugs implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        searchUI searchUI = new searchUI();
    }
    
}