package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

class TriagerViewAllBugsUI implements ActionListener
{

    //triager must be able to check for duplicate bugs/invalid bugs...    
    //let triager view all bugs in a button array similar to the searchui
    //after triager open, let the triager click a button to flag as duplicate, therefore marking it as solved. 
    //in the file, instead of solved, it'll be solved(Duplicate of another bug)


    JFrame viewFrame = new JFrame();
    JPanel viewPanel = new JPanel();

    final static int width = 1050, height = 600;

    JTextField search = new JTextField(600);
    String[] terms = { "Title","Keywords", "Assigned Developer"};
    final JComboBox<String> options = new JComboBox<String>(terms);
    JButton submit = new JButton("Search");

    public TriagerViewAllBugsUI()
    {
        showAllBugs();
    }

    public void showAllBugs()
    {
        viewFrame.setSize(width, height);
        viewFrame.add(viewPanel);
        viewPanel.setLayout(null);

        TriagerViewAllBugsController tvabc = new TriagerViewAllBugsController();
        HashMap<Integer, String> filesMap = tvabc.getFilesMap();
        HashMap<String, String> titlesMap = tvabc.getTitlesMap();
        //System.out.println(titlesMap.get("Bug0001.txt"));


        viewFrame.setSize(1200,750);

        viewFrame.add(viewPanel);
        viewPanel.setLayout(new FlowLayout());

        JButton [] bugButtons = new JButton[filesMap.size()];
        String title = "";
        
        for(int i = 0; i < filesMap.size(); i++)
        {
            for(Map.Entry me : titlesMap.entrySet())
            {
                //compare if bugMap key is same as titleMap key
                if(me.getKey().equals(filesMap.get(i)))
                {
                    title = String.valueOf(me.getValue());
                }
            }
            bugButtons[i] = new JButton(title);
            bugButtons[i].setSize(300, 50);
            bugButtons[i].addActionListener(this);
            viewPanel.add(bugButtons[i]);
        }

        viewFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        IndividualBugUI i = new IndividualBugUI(e.getActionCommand());
    }
}
