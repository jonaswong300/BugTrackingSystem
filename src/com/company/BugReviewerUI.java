package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugReviewerUI implements ActionListener{
    final static int width = 800, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    public BugReviewerUI(){
        showForm();
    }

    public void showForm(){
        frame.setSize(width, height);
        frame.add(panel);
        frame.setTitle("Bug Reviewer UI");
        panel.setLayout(null);

        JLabel user = new JLabel("Welcome Bug Reviewer");
        user.setBounds(330, 10, 200, 100);
        panel.add(user);

        JButton checkUnresolvedBugs = new JButton("Check Unresolved Bugs");
        checkUnresolvedBugs.setBounds(180, 80, 200, 100);
        checkUnresolvedBugs.addActionListener(this);
        panel.add(checkUnresolvedBugs);

        JButton testProposedSolution = new JButton("Test proposed solution");
        testProposedSolution.setBounds(400, 80, 200, 100);
        testProposedSolution.addActionListener(this);
        panel.add(testProposedSolution);

        JButton closeBugReport = new JButton("Close Bug Report");
        closeBugReport.setBounds(700, 80, 200, 100);
        closeBugReport.addActionListener(this);
        panel.add(closeBugReport);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Check Unresolved Bugs"))
        {
            BugReviewerCheckUnresolvedBugUI brcubUI = new BugReviewerCheckUnresolvedBugUI();
        }
        else if(e.getActionCommand().equals("Test proposed solution"))
        {
            BugReviewerTestIntendedSolutionUI brtisUI = new BugReviewerTestIntendedSolutionUI();
        }
        else if(e.getActionCommand().equals("Close Bug Report"))
        {
            BugReviewerCloseBugReportUI closeBugUI = new BugReviewerCloseBugReportUI();
        }
    }
}

