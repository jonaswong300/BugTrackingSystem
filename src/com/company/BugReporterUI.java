package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugReporterUI implements ActionListener
{
    final static int width = 800, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private String reporter;

    public BugReporterUI(String reporter)
    {
        this.reporter = reporter;
        showForm();
    }

    public String getReporter()
    {
        return reporter;
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
        addBugReport.addActionListener(this);
        panel.add(addBugReport);

        JButton trackBug = new JButton("Track Reported Bugs");
        trackBug.setBounds(300, 80, 200, 100);
        trackBug.addActionListener(this);
        panel.add(trackBug);

        JButton searchBugs = new JButton("Search for Bugs");
        searchBugs.setBounds(540, 80, 200, 100);
        searchBugs.addActionListener(this);
        panel.add(searchBugs);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Add Bug Report"))
        {
            UserAddBugsUI addbugUI = new UserAddBugsUI(reporter);
        }
        else if(e.getActionCommand().equals("Track Reported Bugs"))
        {
            UserTrackBugUI trackUI = new UserTrackBugUI();
        }
        else if(e.getActionCommand().equals("Search for Bugs"))
        {
            SearchUI searchUI = new SearchUI();
        }
    }
}
