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

        BugDatabase bd = new BugDatabase();
        HashMap<Integer, String> filesMap = bd.getFileMap();
        HashMap<String, String> titlesMap = bd.getTitleMap();
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
            bugButtons[i].addActionListener(new getBug());
            viewPanel.add(bugButtons[i]);
            
        }

        viewFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
class getBug implements ActionListener
{
    String bugFile;
    JFrame bugThread = new JFrame();
    JPanel bugPanel = new JPanel(); 
    JLabel bugLabel = new JLabel("Bug Descriptions : ");
    JTextArea bug = new JTextArea();
    JLabel commentLabel = new JLabel("Comments : ");
    JTextArea comments = new JTextArea();
    JButton flag = new JButton("Flag as duplicate bug");

    public getBug()
    {

    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {

        bugThread.setSize(1400,750); 
        bugThread.setVisible(true);

        bugThread.add(bugPanel);
        bugPanel.setLayout(null);

        bugLabel.setBounds(10, 10, 150, 20);
        bugPanel.add(bugLabel);

        bug.setBounds(130,10,1250,400);
        bugPanel.add(bug);

        commentLabel.setBounds(10, 500, 150, 20);
        bugPanel.add(commentLabel);

        comments.setBounds(130,500,1250,200);
        bugPanel.add(comments);
        comments.setVisible(true);

        bugFile = e.getActionCommand();
        System.out.println(bugFile);

        SearchController sc = new SearchController(bugFile);
        String specificBugFile = sc.searchByTitle();

        CommentDatabase cd = new CommentDatabase();
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
                    System.out.println(commentString);
                    comments.append(commentString);
                }
            }

            fr.close();
        }
        catch (IOException except) 
        {
            except.printStackTrace();
        }
        flag.setBounds(130,650,200,50);
        flag.addActionListener(new flagDuplicate(bugFileName, bugThread));
        bugPanel.add(flag);
    }
    
}

class flagDuplicate implements ActionListener
{
    String bugFile;
    JFrame frame = new JFrame();
    public flagDuplicate(String bugFile, JFrame frame)
    {
        this.bugFile = bugFile;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        TriagerViewAllBugsController flagControl = new TriagerViewAllBugsController(bugFile);
        frame.dispose();
    }
    
}