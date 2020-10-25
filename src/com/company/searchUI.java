package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

class searchUI extends JFrame implements ActionListener
{
    final static int width = 1050, height = 600;

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    JTextField search = new JTextField(600);
    String[] terms = { "Title","Keywords", "Assigned Developer"};
    final JComboBox<String> options = new JComboBox<String>(terms);
    JButton submit = new JButton("Search");

    public searchUI()
    {
        frame.setSize(width, height);
        frame.add(panel);
        panel.setLayout(null);

        JLabel searchL = new JLabel("Search Terms : ");
        searchL.setFont(searchL.getFont().deriveFont(32.0f));
        searchL.setBounds(170,150,400,50);
        panel.add(searchL);

        search.setFont(search.getFont().deriveFont(20.0f));
        search.setBounds(170,200,650,50);
        panel.add(search);


        options.setFont(options.getFont().deriveFont(18.0f));
        options.setBounds(170,260,300,50);
        panel.add(options);

        submit.setFont(submit.getFont().deriveFont(18.0f));
        submit.setBounds(170,350,200,50);
        submit.addActionListener(this);
        panel.add(submit);


        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {   

        searchController sc = new searchController(search.getText());
        if(options.getSelectedItem().equals("Title"))
        {
            String bugFile = sc.searchByTitle();

            if (bugFile.isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "You have entered a title for a bug report that does not exist, please check for spelling errors. "
                                            + "If you want to create a bug report with this title, please go back to the main menu and add a bug report. "
                                            , "Invalid Bug Title Search", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                //open thread in new frame

                JFrame bugThread = new JFrame();
                JPanel bugPanel = new JPanel();                
                bugThread.setSize(1200,750); 
                bugThread.setVisible(true);

                bugThread.add(bugPanel);
                panel.setLayout(null);

                JLabel bugLabel = new JLabel("Bug Descriptions : ");
                bugLabel.setBounds(10, 10, 150, 20);
                bugPanel.add(bugLabel);
                JTextArea bug = new JTextArea();
                bug.setBounds(130,10,200,200);
                bugPanel.add(bug);



                try 
                {
                    FileReader fr = new FileReader(bugFile);
                    bug.read(fr, bugFile);
                    fr.close();
                }
                catch (IOException except) 
                {
                    except.printStackTrace();
                }
            }
        }
        else if(options.getSelectedItem().equals("Keywords"))
        {
            sc.searchByKeywords();
        }
        else
        {
            sc.searchByDev();
        }

    }
}
