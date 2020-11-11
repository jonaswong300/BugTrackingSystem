package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class TriagerSetBugSeverityUI<width> implements ActionListener {

    final int WIDTH = 1000, HEIGHT = 500;

    JFrame frame = new JFrame();

    JPanel panel = new JPanel();

    JLabel uiLabel = new JLabel("Set/Change Severity Level Of Bugs : ");
    JLabel bugL = new JLabel("Bugs : ");
    JLabel sevL = new JLabel("Severity Level : ");
    JLabel levelAnnouncment0 = new JLabel("0 = Low");
    JLabel levelAnnouncment1 = new JLabel("1 = Medium");
    JLabel levelAnnouncment2 = new JLabel("2 = High");
    JLabel levelAnnouncment3 = new JLabel("3 = Critical");

    JButton setButton = new JButton("Set Level");

    Object [] bugsDropDown;
    JComboBox<Object>bugsBox;

    String[] terms = {"0", "1", "2", "3"};
    JComboBox<String> sevBox = new JComboBox<String>(terms);

    String bugSelect, levelSelect;

    public TriagerSetBugSeverityUI(){showUI();}

    public void showUI(){
        TriagerSetBugSeverityController tsbsc = new TriagerSetBugSeverityController();

        frame.setTitle("Set Bug Severity UI");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        panel.setLayout(null);

        uiLabel.setBounds(20, 20, 250, 20);
        panel.add(uiLabel);

        bugL.setBounds(20, 60, 150, 20);
        panel.add(bugL);

        ArrayList<String> bugseverityList = tsbsc.getAllBugFileList();
        bugsDropDown = bugseverityList.toArray();
        bugsBox = new JComboBox<Object>(bugsDropDown);
        bugsBox.setBounds(200, 60, 600, 20);
        panel.add(bugsBox);

        sevL.setBounds(20, 100, 150, 20);
        panel.add(sevL);

        sevBox.setBounds(200, 100, 300, 20);
        panel.add(sevBox);



        setButton.setBounds(20, 140, 100, 20);
        setButton.addActionListener(this);
        panel.add(setButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        TriagerSetBugSeverityController tsbsc = new TriagerSetBugSeverityController();

        bugSelect = String.valueOf(bugsBox.getSelectedItem());
        levelSelect = String.valueOf(sevBox.getSelectedItem());

        System.out.println(bugSelect + "\t" + levelSelect);
        tsbsc.setSeverityLevel(bugSelect, levelSelect);

        JOptionPane.showMessageDialog(frame, "You have assigned a severity level of " + levelSelect +
                                                     " to " + bugSelect,
                                     "Assigned Level", JOptionPane.INFORMATION_MESSAGE);


    }

}
