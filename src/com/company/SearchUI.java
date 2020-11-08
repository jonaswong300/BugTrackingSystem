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
        JLabel commentLabel = new JLabel("Comments : ");
        JTextArea comments = new JTextArea();

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

                bug.setBounds(130,10,1250,400);
                bugPanel.add(bug);

                commentLabel.setBounds(10, 500, 150, 20);
                bugPanel.add(commentLabel);

                comments.setBounds(130,500,1250,200);
                bugPanel.add(comments);
                comments.setVisible(true);

                CommentDatabase cd = new CommentDatabase();
                HashMap<String, String> bugCommentFileNameMap = cd.getBugCommentFileNameMap();
                HashMap<String, Comment> commentLinkMap = cd.getCommentLinkMap();

                try 
                {
                    FileReader fr = new FileReader("Bugs/" + bugFile);
                    bug.read(fr, bugFile);

                    if(bugCommentFileNameMap.containsKey(bugFile)){
                        String tempCommentFileName = bugCommentFileNameMap.get(bugFile);

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
                    bugButtons[i].addActionListener(new GetSpecificBugUI("e", title));
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
                    bugButtons[i].addActionListener(new GetSpecificBugUI("e", title));
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
class GetSpecificBugUI implements ActionListener
{
    String whichAction;
    String bugFile;
    JFrame bugThread = new JFrame();
    JFrame frame = new JFrame();
    JPanel bugPanel = new JPanel(); 
    JPanel panel = new JPanel();
    JLabel bugLabel = new JLabel("Bug Descriptions : ");
    JTextArea bug = new JTextArea();
    JLabel commentLabel = new JLabel("Comments : ");
    JTextArea comments = new JTextArea();
    JScrollPane scrollB = new JScrollPane (bug);
    JScrollPane scrollC = new JScrollPane (comments);
    JTextArea commentArea = new JTextArea(5, 20);
    JScrollPane scrollPane = new JScrollPane(commentArea); 
    JButton addC = new JButton("Submit Comment");
    String tempCommentFileName = "";
    HashMap<String, String> bugCommentFileNameMap;
    HashMap<String, Comment> commentLinkMap;

    public GetSpecificBugUI(String whichAction, String title)
    {
        this.whichAction = whichAction;
        bugFile = title;
        //System.out.println("Hitting : " + title);
    }

    public void getForm()
    {
        
        bugThread.setSize(15000,900); 
        bugThread.setVisible(true);

        bugThread.add(bugPanel);
        bugPanel.setLayout(null);

        bugLabel.setBounds(10, 10, 150, 20);
        bugPanel.add(bugLabel);

        bug.setBounds(130,10,1250,450);
        bug.setSize(1250,450);
        bug.setLineWrap(true);
        bug.setEditable(false);
        scrollB.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bugPanel.add(bug);
        bugPanel.add(scrollB);

        commentLabel.setBounds(10, 500, 150, 20);
        bugPanel.add(commentLabel);

        comments.setBounds(130,500,1250,400);
        comments.setSize(1250,200);
        comments.setLineWrap(true);
        comments.setEditable(false);
        scrollC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bugPanel.add(comments);
        bugPanel.add(scrollC);
        comments.setVisible(true);


        SearchController sc = new SearchController(bugFile);
        String specificBugFile = sc.searchByTitle();

        CommentDatabase cd = new CommentDatabase();
        bugCommentFileNameMap = cd.getBugCommentFileNameMap();
        commentLinkMap = cd.getCommentLinkMap();
        

        String bugFileName = "Bugs/" + specificBugFile;
        try
        {
            FileReader fr = new FileReader(bugFileName);
            bug.read(fr, bugFileName);

            if(bugCommentFileNameMap.containsKey(specificBugFile)){
                tempCommentFileName = bugCommentFileNameMap.get(specificBugFile);

                if(commentLinkMap.containsKey(tempCommentFileName)){
                    String commentString = String.valueOf(commentLinkMap.get(tempCommentFileName));
                    //System.out.println(commentString);
                    comments.append(commentString);
                }
            }

            fr.close();
        }
        catch (IOException except)
        {
            except.printStackTrace();
        }

        JButton comment = new JButton("Add Comment Here");
        comment.setBounds(130,720,200,20);
        
        comment.addActionListener(this);
        bugPanel.add(comment);
    }

    public void addComment()
    {
        frame.setSize(1100, 600);
        frame.add(panel);
        panel.setLayout(null);

        JLabel addCommentL = new JLabel("Add your comment in the text field : ");
        addCommentL.setBounds(10, 10, 350, 20);
        panel.add(addCommentL);


        commentArea.setEditable(true);
        commentArea.setBounds(10,50,850,200);
        panel.add(commentArea);

        addC.setBounds(710,280,150,20);
        addC.addActionListener(this);
        panel.add(addC);

        frame.setVisible(true);
    }

    public void callCommentController()
    {
        AddCommentController acc = new AddCommentController(commentArea.getText());


        acc.writeCommentToFile(tempCommentFileName);
    }
    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        
        if(!evt.getActionCommand().equals("Add Comment Here") && !evt.getActionCommand().equals("Submit Comment"))
        {
            getForm();
        }
        else if (evt.getActionCommand().equals("Submit Comment"))
        {
            
            //write to files
            callCommentController();
            JOptionPane.showMessageDialog(frame, "Close and open bug thread again for your comment to be shown.", 
                                        "Comment Added", JOptionPane.INFORMATION_MESSAGE);
            //close comment frame
            frame.dispose();
        }
        else
        {
            //open comment frame
            addComment();
        }
    }
    
}

