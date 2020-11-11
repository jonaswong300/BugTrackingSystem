package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TriagerUI implements ActionListener
{
    final static int width = 1500, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    public TriagerUI(){
        showForm();
    }

    public void showForm(){
        frame.setSize(width, height);
        frame.add(panel);
        frame.setTitle("Triager UI");
        panel.setLayout(null);

        JLabel user = new JLabel("Welcome Triager");
        user.setBounds(590, 10, 200, 100);
        panel.add(user);

        JButton viewBugReport = new JButton("View All Bugs");
        viewBugReport.setBounds(60, 80, 200, 100);
        viewBugReport.addActionListener(this);
        panel.add(viewBugReport);

        JButton assignBugToDeveloper = new JButton("Assign bug to developer");
        assignBugToDeveloper.setBounds(300, 80, 200, 100);
        assignBugToDeveloper.addActionListener(this);
        panel.add(assignBugToDeveloper);

        JButton setBugSeverityLevel = new JButton("Set severity level of Bug");
        setBugSeverityLevel.setBounds(540, 80, 200, 100);
        setBugSeverityLevel.addActionListener(this);
        panel.add(setBugSeverityLevel);

        JButton searchBugs = new JButton("Search for Bugs");
        searchBugs.setBounds(780, 80, 200, 100);
        searchBugs.addActionListener(this);
        panel.add(searchBugs);

        JButton generateReports = new JButton("Generate System Reports");
        generateReports.setBounds(1020, 80, 200, 100);
        generateReports.addActionListener(this);
        panel.add(generateReports);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("View All Bugs")){
            TriagerViewAllBugsUI viewUI = new TriagerViewAllBugsUI();
        }else if(e.getActionCommand().equals("Assign bug to developer")){
            TriagerAssignDevUI assignUI = new TriagerAssignDevUI();
        }else if (e.getActionCommand().equals("Set severity level of Bug")){
            TriagerSetBugSeverityUI setUI = new TriagerSetBugSeverityUI();
        }else if(e.getActionCommand().equals("Search for Bugs")){
            SearchUI s = new SearchUI();
        }else if(e.getActionCommand().equals("Generate System Reports")){
            TriagerGenerateReportUI reportUI = new TriagerGenerateReportUI();
        }
    }
}
