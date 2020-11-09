package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

class TriagerAssignDevUI implements ActionListener
{
    JFrame devFrame = new JFrame();
    JPanel devPanel = new JPanel(); 
    JLabel uiLabel = new JLabel("Assign developer to unassigned bugs : ");
    JLabel bugL = new JLabel("Unassigned Bugs : ");
    JLabel devL = new JLabel("Developers : ");
    JButton checkButton = new JButton("Check Bug");
    JButton assignButton = new JButton("Assign Bug");
    Object [] devDropDown;
    Object [] bugsDropDown;
    JComboBox<Object>bugsBox;
    JComboBox<Object>devBox;
    String bugSelect;
    String devName;

    public TriagerAssignDevUI()
    {
        showUI();
    }

    public void showUI()
    {
        TriagerAssignDevController tadc = new TriagerAssignDevController();

        devFrame.setSize(1000,500);
        devFrame.add(devPanel);
        devPanel.setLayout(null);

        uiLabel.setBounds(20,20, 250, 20);
        devPanel.add(uiLabel);

        bugL.setBounds(20,60,150,20);
        devPanel.add(bugL);

        ArrayList<String> unassignedBugs = tadc.getAllUnassigned();
        bugsDropDown = unassignedBugs.toArray();
        bugsBox = new JComboBox<Object>(bugsDropDown);
        bugsBox.setBounds(200,60,600,20);
        devPanel.add(bugsBox);

        devL.setBounds(20,100,150,20);
        devPanel.add(devL);

        ArrayList<String> devList = tadc.getAllDevelopers();
        devDropDown = devList.toArray();
        devBox = new JComboBox<Object>(devDropDown);
        devBox.setBounds(200,100,300,20);
        devPanel.add(devBox);

        checkButton.setBounds(20,140,100,20);
        checkButton.addActionListener(this);
        devPanel.add(checkButton);

        assignButton.setBounds(200,140,100,20);
        assignButton.addActionListener(this);
        devPanel.add(assignButton);
        

        devFrame.setVisible(true);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Assign Bug"))
        {
            bugSelect = String.valueOf(bugsBox.getSelectedItem());
            devName = String.valueOf(devBox.getSelectedItem());
    
            TriagerAssignDevController tadc = new TriagerAssignDevController();
            tadc.setDev(bugSelect, devName);
    
            JOptionPane.showMessageDialog(devFrame, "You have assigned '" + bugSelect + "' to '" + devName + "'"
                                                 , "Assigned Bug", JOptionPane.INFORMATION_MESSAGE);
                                       
            devFrame.dispose();
        }
        else if(e.getActionCommand().equals("Check Bug"))
        {
            JFrame bugThread = new JFrame();
            JPanel bugPanel = new JPanel(); 
            JLabel bugLabel = new JLabel("Bug Descriptions : ");
            JTextArea bug = new JTextArea();

            bugThread.setSize(1330,750); 
            bugThread.setVisible(true);
    
            bugThread.add(bugPanel);
            bugPanel.setLayout(null);
    
            bugLabel.setBounds(10, 10, 150, 20);
            bugPanel.add(bugLabel);
    
            bug.setBounds(130,10,1250,750);
            bugPanel.add(bug);
            
            String specificBugFile = "Bugs/" + bugsBox.getSelectedItem();
            try 
            {
                FileReader fr = new FileReader(specificBugFile);
                bug.read(fr, specificBugFile);
                fr.close();
            }
            catch (IOException except) 
            {
                except.printStackTrace();
            }
    
        }
    }
    
}