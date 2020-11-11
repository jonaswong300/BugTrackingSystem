package com.company;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class IndividualBugUI implements ActionListener
{
    String bugFile;
    JFrame bugThread = new JFrame();
    JPanel bugPanel = new JPanel(); 
    JLabel bugLabel = new JLabel("Bug Descriptions : ");
    JTextArea bug = new JTextArea();
    JLabel commentLabel = new JLabel("Comments : ");
    JTextArea comments = new JTextArea();
    JButton flagD = new JButton("Flag as duplicate bug");
    JButton flagI = new JButton("Flag as invalid bug");

    public IndividualBugUI(String bug)
    {
        showBugsPanels(bug);
    }


    public void showBugsPanels(String bugName)
    {
        bugThread.setTitle("Individual Bug UI");
        bugThread.setSize(1500,750); 
        bugThread.setVisible(true);

        bugThread.add(bugPanel);
        bugPanel.setLayout(null);

        bugLabel.setBounds(10, 10, 150, 20);
        bugPanel.add(bugLabel);

        bug.setBounds(130,10,1250,400);
        bugPanel.add(bug);

        commentLabel.setBounds(10, 450, 150, 20);
        bugPanel.add(commentLabel);

        comments.setBounds(130,450,1250,200);
        bugPanel.add(comments);
        comments.setVisible(true);

        bugFile = bugName;

        SearchController sc = new SearchController(bugFile);
        String specificBugFile = sc.searchByTitle();

        CommentAccess cd = new CommentAccess();
        HashMap<String, String> bugCommentFileNameMap = cd.getBugCommentFileNameMap();
        HashMap<String, Comment> commentLinkMap = cd.getCommentLinkMap();

        String bugFileName = "Bugs/" + specificBugFile;
        try 
        {
            FileReader fr = new FileReader(bugFileName);
            bug.read(fr, bugFileName);

            if(bugCommentFileNameMap.containsKey(specificBugFile)){
                String tempCommentFileName = bugCommentFileNameMap.get(specificBugFile);

                if(commentLinkMap.containsKey(tempCommentFileName)){
                    String commentString = String.valueOf(commentLinkMap.get(tempCommentFileName));
                    comments.append(commentString);
                }
            }

            fr.close();
        }
        catch (IOException except) 
        {
            except.printStackTrace();
        }
        flagD.setBounds(130,660,200,50);
        flagD.addActionListener(this);
        bugPanel.add(flagD);

        flagI.setBounds(400,660,200,50);
        flagI.addActionListener(this);
        bugPanel.add(flagI);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        TriagerViewAllBugsController flagControl = new TriagerViewAllBugsController(bugFile);
        
        if(e.getActionCommand().equals("Flag as duplicate bug"))
        {
            String check = flagControl.flagDup();
            if(check.equals("true"))
            {
                JOptionPane.showMessageDialog(bugThread, "System rewriting to adjust for duplicated bug report. Please exit back to main menu.", 
                                                "Flagged Duplicate", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(check.equals("false"))
            {
                JOptionPane.showMessageDialog(bugThread, "Bug has already been flagged as duplicate.", 
                                                "Flagged Duplicate", JOptionPane.ERROR_MESSAGE);
            }
            else if(check.equals("dev"))
            {
                JOptionPane.showMessageDialog(bugThread, "Bug has already been checked and assigned a developer.", 
                                            "Flagged Duplicate", JOptionPane.ERROR_MESSAGE);
            }
            bugThread.dispose();
        }
        else if(e.getActionCommand().equals("Flag as invalid bug"))
        {
            String check = flagControl.flagInv();
            if(check.equals("true"))
            {
                JOptionPane.showMessageDialog(bugThread, "System rewriting to adjust for invalid bug report. Please exit back to main menu.", 
                                                "Flagged Invalid", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(check.equals("false"))
            {
                JOptionPane.showMessageDialog(bugThread, "Bug has already been flagged as invalid.", 
                                                "Flagged Invalid", JOptionPane.ERROR_MESSAGE);
            }
            else if(check.equals("dev"))
            {
                JOptionPane.showMessageDialog(bugThread, "Bug has already been checked and assigned a developer.", 
                                            "Flagged Invalid", JOptionPane.ERROR_MESSAGE);
            }
            bugThread.dispose();
        }
    }
    
}
