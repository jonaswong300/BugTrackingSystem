package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugDeveloperUI extends JFrame{

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

        JButton fixBugReport = new JButton("Fix Bug");
        fixBugReport.setBounds(60, 80, 200, 100);
        fixBugReport.addActionListener(new FixBugReport());
        panel.add(fixBugReport);

        JButton closeBugReport = new JButton("Close Bug Report");
        closeBugReport.setBounds(300, 80, 200, 100);
        closeBugReport.addActionListener(new CloseBugReport());
        panel.add(closeBugReport);

        JButton searchBugs = new JButton("Search for Bugs");
        searchBugs.setBounds(540, 80, 200, 100);
        searchBugs.addActionListener(new SearchForBugs());
        panel.add(searchBugs);

        frame.setVisible(true);
    }

}

class FixBugReport implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        BugDeveloperFixBugReportUI fixBugUI = new BugDeveloperFixBugReportUI();
    }
}

class CloseBugReport implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        BugDeveloperCloseBugReportUI closeBugUI = new BugDeveloperCloseBugReportUI();
    }

}

