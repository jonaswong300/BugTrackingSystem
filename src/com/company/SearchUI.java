package com.company;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

class SearchUI implements ActionListener
{
    final static int width = 1050, height = 600;

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    JTextField search = new JTextField(600);
    String[] terms = { "Title","Keywords", "Assigned Developer"};
    final JComboBox<String> options = new JComboBox<String>(terms);
    JButton submit = new JButton("Search");

    public SearchUI()
    {
        showForm();
    }

    public void showForm(){
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

        JFrame bugThread = new JFrame();
        JPanel bugPanel = new JPanel(); 
        JLabel bugLabel = new JLabel("Bug Descriptions : ");
        JTextArea bug = new JTextArea();

        SearchController sc = new SearchController(search.getText());
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
               
                bugThread.setSize(1330,750); 
                bugThread.setVisible(true);

                bugThread.add(bugPanel);
                bugPanel.setLayout(null);

                bugLabel.setBounds(10, 10, 150, 20);
                bugPanel.add(bugLabel);

                bug.setBounds(130,10,1250,750);
                bugPanel.add(bug);


                try 
                {
                    FileReader fr = new FileReader("Bugs/" + bugFile);
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
            boolean valid = sc.checkValidKeyword();
            if(valid)
            {
                BugDatabase bd = new BugDatabase();
                ArrayList<String> keywordFiles = sc.searchByKeywords();
                HashMap<String, String> titlesMap = bd.getTitleMap();
                
                bugThread.setSize(1200,750); 
                bugThread.setVisible(true);
    
                bugThread.add(bugPanel);
                bugPanel.setLayout(new FlowLayout());
    
                String title = "";
                JButton [] bugButtons = new JButton[keywordFiles.size()];

                for(int i = 0; i < keywordFiles.size(); i++)
                {
                    for(Map.Entry<String,String> me : titlesMap.entrySet())
                    {

                        System.out.println(me.getKey());
                        if(("Bugs/" + me.getKey()).equals(keywordFiles.get(i)))
                        {
                            title = me.getValue();
                        }
                    }
                    //String title = titlesMap.get(devFilesList.get(i));
                    bugButtons[i] = new JButton(title);
                    bugButtons[i].setSize(300, 50);
                    bugButtons[i].addActionListener(new getSpecificBug());
                    bugPanel.add(bugButtons[i]);
                    
                }

            }
            else
            {
                JOptionPane.showMessageDialog(frame, "You have entered an invalid keyword. Please check for spelling errors. "
                                            , "Invalid Keyword Search", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            if(sc.checkDevExists())
            {
                BugDatabase bd = new BugDatabase();
                ArrayList<String> devFilesList = sc.searchByDev();
                HashMap<String, String> titlesMap = bd.getTitleMap();
                //System.out.println(titlesMap.get("Bug0001.txt"));


                bugThread.setSize(1200,750); 
                bugThread.setVisible(true);
    
                bugThread.add(bugPanel);
                bugPanel.setLayout(new FlowLayout());
    
                String title = "";
                JButton [] bugButtons = new JButton[devFilesList.size()];

                for(int i = 0; i < devFilesList.size(); i++)
                {
                    for(Map.Entry<String,String> me : titlesMap.entrySet())
                    {
                        if(me.getKey().equals(devFilesList.get(i)))
                        {
                            title = me.getValue();
                        }
                    }
                    //String title = titlesMap.get(devFilesList.get(i));
                    bugButtons[i] = new JButton(title);
                    bugButtons[i].setSize(300, 50);
                    bugButtons[i].addActionListener(new getSpecificBug());
                    bugPanel.add(bugButtons[i]);
                    
                }
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "You have entered an invalid developer username. Please check for spelling errors. "
                                            , "Invalid Developer Search", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}



// USED FOR SEARCHBYDEV AND SEARCHBYKEYWORDS
// FOR THE BUTTONS OF BUGS LISTED
class getSpecificBug implements ActionListener
{
    String bugFile;
    JFrame bugThread = new JFrame();
    JPanel bugPanel = new JPanel(); 
    JLabel bugLabel = new JLabel("Bug Descriptions : ");
    JTextArea bug = new JTextArea();

    public getSpecificBug()
    {

    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {

        bugThread.setSize(1330,750); 
        bugThread.setVisible(true);

        bugThread.add(bugPanel);
        bugPanel.setLayout(null);

        bugLabel.setBounds(10, 10, 150, 20);
        bugPanel.add(bugLabel);

        bug.setBounds(130,10,1250,750);
        bugPanel.add(bug);

        bugFile = e.getActionCommand();

        SearchController sc = new SearchController(bugFile);
        String specificBugFile = sc.searchByTitle();
        
        specificBugFile = "Bugs/" + specificBugFile;
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