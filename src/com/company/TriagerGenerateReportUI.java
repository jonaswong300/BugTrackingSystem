package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TriagerGenerateReportUI implements ActionListener
{
    final static int width = 1040, height = 500;

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    String [] reportsDropdown = {"No. of Bugs reported in a month", "No. of bugs resolved in the last 7 days", "Best performing developer", "Best performing reporter"};
    String [] months = {"Jan","Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    JComboBox<String> reportsBox = new JComboBox<String>(reportsDropdown);
    JComboBox<String> monthBox = new JComboBox<String>(months);
    JButton generate = new JButton("Generate Report");

    public TriagerGenerateReportUI()
    {
        showInterface();
    }

    public void showInterface()
    {
        frame.setSize(850, 400);
        frame.add(panel);
        frame.setTitle("Triager generate report UI");
        panel.setLayout(null);

        JLabel l = new JLabel("System Report Generation : ");
        l.setBounds(20,20,200,20);
        panel.add(l);

        reportsBox.setBounds(20,60,400,20);
        panel.add(reportsBox);

        JLabel month_L = new JLabel("If selecting 'No. of Bugs reported in a month', please choose the month as well.");
        month_L.setBounds(20,100,600,20);
        panel.add(month_L);
        JLabel month_L_2 = new JLabel("If not please ignore the dropdown box below:");
        month_L_2.setBounds(20,120,600,20);
        panel.add(month_L_2);

        monthBox.setBounds(20,160,100,20);
        panel.add(monthBox);

        generate.setBounds(20,200,250,20);
        generate.addActionListener(this);
        panel.add(generate);

        

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        TriagerGenerateReportController tgrc = new TriagerGenerateReportController();

        //check which report they want
        if(reportsBox.getSelectedIndex() == 0)
        {
            int count = tgrc.getBugsInMonth(monthBox.getSelectedIndex());
            JOptionPane.showMessageDialog(frame, "For the month of " + monthBox.getSelectedItem().toString() + ", " + count + 
                                            " bugs have been reported. ", "No. of Bugs reported in a month", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(reportsBox.getSelectedIndex() == 1)
        {
            //no. of bugs resolved in last 7 days
            int count = tgrc.getResolvedInWeek();
            JOptionPane.showMessageDialog(frame, "For the last 7 days, " + count + 
                                            " bugs have been resolved. ", "No. of Bugs resolved in the last week", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(reportsBox.getSelectedIndex() == 2)
        {
            //best performing developer (the developer with the most closed bug reports)
            String dev = tgrc.getBestDeveloper();
            int no = tgrc.getDevNum();
            JOptionPane.showMessageDialog(frame, "The developer that performed the best is '" + dev + 
                                            "' for resolving " + no +" bugs. ", "Best developer", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            String rep = tgrc.getBestReporter();
            int no = tgrc.getRepNum();
            JOptionPane.showMessageDialog(frame, "The reporter that performed the best is '" + rep + 
                                            "' for reporting " + no + " bugs. ", "Best reporter", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
