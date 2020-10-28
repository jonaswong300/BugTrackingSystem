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
        addBugReport.addActionListener(new AddBugReport());
        panel.add(addBugReport);

        JButton trackBug = new JButton("Track Reported Bugs");
        trackBug.setBounds(300, 80, 200, 100);
        trackBug.addActionListener(new TrackBugReport());
        panel.add(trackBug);

        JButton searchBugs = new JButton("Search for Bugs");
        searchBugs.setBounds(540, 80, 200, 100);
        searchBugs.addActionListener(new SearchForBugs());
        panel.add(searchBugs);

        frame.setVisible(true);
    }
}

class AddBugReport implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {   
        UserAddBugsUI addbugUI = new UserAddBugsUI();
    }
}

class TrackBugReport implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        UserTrackBugUI trackUI = new UserTrackBugUI();
    }
    
}

class SearchForBugs implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        SearchUI searchUI = new SearchUI();
    }
    
}