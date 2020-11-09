package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TriagerUI 
{
    final static int width = 1040, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    public TriagerUI(){
        showForm();
    }

    public void showForm(){
        frame.setSize(width, height);
        frame.add(panel);
        panel.setLayout(null);

        JLabel user = new JLabel("Welcome Triager");
        user.setBounds(470, 10, 200, 100);
        panel.add(user);

        JButton viewBugReport = new JButton("View All Bugs");
        viewBugReport.setBounds(60, 80, 200, 100);
        viewBugReport.addActionListener(new viewAllBugReport());
        panel.add(viewBugReport);

        JButton assignBugToDeveloper = new JButton("Assign bug to developer");
        assignBugToDeveloper.setBounds(300, 80, 200, 100);
        assignBugToDeveloper.addActionListener(new AssignBugToDeveloper());
        panel.add(assignBugToDeveloper);

        JButton searchBugs = new JButton("Search for Bugs");
        searchBugs.setBounds(540, 80, 200, 100);
        searchBugs.addActionListener(new T_searchForBugs());
        panel.add(searchBugs);

        JButton generateReports = new JButton("Generate System Reports");
        generateReports.setBounds(780, 80, 200, 100);
        generateReports.addActionListener(new generateReports());
        panel.add(generateReports);

        frame.setVisible(true);
    }
}

class viewAllBugReport implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        TriagerViewAllBugsUI viewUI = new TriagerViewAllBugsUI();
    }
}

class AssignBugToDeveloper implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        TriagerAssignDevUI assignUI = new TriagerAssignDevUI();
    }
}

class generateReports implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        TriagerGenerateReportUI reportUI = new TriagerGenerateReportUI();
    }
    
}

class T_searchForBugs implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
        SearchUI s = new SearchUI();

    }

}