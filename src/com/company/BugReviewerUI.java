package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BugReviewerUI {
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
        checkUnresolvedBugs.setBounds(60, 80, 200, 100);
        checkUnresolvedBugs.addActionListener(new FixBugReport());
        panel.add(checkUnresolvedBugs);

        JButton testProposedSolution = new JButton("Test proposed solution");
        testProposedSolution.setBounds(300, 80, 200, 100);
        testProposedSolution.addActionListener(new checkUnresolvedBugs());
        panel.add(testProposedSolution);

        frame.setVisible(true);
    }
}

class checkUnresolvedBugs implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        BugReviewerCheckUnresolvedBugUI brcubUI = new BugReviewerCheckUnresolvedBugUI();
    }
}

class testIntendedSolution implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e){
        BugReviewerTestIntendedSolutionUI brtisUI = new BugReviewerTestIntendedSolutionUI();
    }
}
