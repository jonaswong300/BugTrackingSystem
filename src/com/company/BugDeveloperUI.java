package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugDeveloperUI extends JFrame implements ActionListener{

    final static int width = 800, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    public BugDeveloperUI(){
        showForm();
    }

    public void showForm(){
        frame.setSize(width, height);
        frame.add(panel);
        panel.setLayout(null);

        JLabel user = new JLabel("Welcome Bug Developer");
        user.setBounds(330, 10, 200, 100);
        panel.add(user);

        JButton fixBugReport = new JButton("Close Bug Report");
        fixBugReport.setBounds(60, 80, 300, 100);
        fixBugReport.addActionListener(this);
        panel.add(fixBugReport);

        JButton searchBugs = new JButton("Search for Bugs");
        searchBugs.setBounds(400, 80, 300, 100);
        searchBugs.addActionListener(this);
        panel.add(searchBugs);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Search for Bugs"))
        {
            SearchUI searchUI = new SearchUI();
        }
        else if(e.getActionCommand().equals("Close Bug Report"))
        {
            BugReviewerCloseBugReportUI fixBugUI = new BugReviewerCloseBugReportUI();
        }
    }

}
