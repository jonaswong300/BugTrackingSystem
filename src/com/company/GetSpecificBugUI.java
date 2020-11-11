package com.company;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
    JTextArea commentArea = new JTextArea(5, 20);
    JButton addC = new JButton("Submit Comment");
    String tempCommentFileName = "";
    HashMap<String, String> bugCommentFileNameMap;
    HashMap<String, Comment> commentLinkMap;

    JScrollPane jsp = new JScrollPane(bug);
    JScrollPane scrollC = new JScrollPane(comments);

    public GetSpecificBugUI(String whichAction, String title)
    {
        this.whichAction = whichAction;
        bugFile = title;
    }

    public void getForm_T()
    {
        frame.setTitle("Get Specific Bug UI");
        bugThread.setSize(1500,900); 
        bugThread.setVisible(true);

        bugThread.add(bugPanel);
        //bugPanel.setLayout(new GridLayout(1,0));
        bugPanel.setLayout(null);

        bugLabel.setBounds(10, 10, 150, 20);
        bugPanel.add(bugLabel);

        jsp.setBounds(130,10,1250,400);
        //bug.setSize(1250,450);
        bug.setLineWrap(true);
        bug.setEditable(false);

        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //bugPanel.add(bug);
        bugPanel.add(jsp);

        commentLabel.setBounds(10, 500, 150, 20);
        bugPanel.add(commentLabel);


        scrollC.setBounds(130,500,1250,200);
        //comments.setSize(1250,200);
        comments.setLineWrap(true);
        comments.setEditable(false);

        scrollC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //bugPanel.add(comments);
        bugPanel.add(scrollC);

        comments.setVisible(true);

        SearchController sc = new SearchController();

        bugCommentFileNameMap = sc.getComDatabaseBCFNM();
        commentLinkMap = sc.getComDatabaseCLM();
        

        String bugFileName = "Bugs/" + bugFile;
        String allComments = "";

        try
        {
            FileReader fr = new FileReader(bugFileName);
            bug.read(fr, bugFileName);
            if(bugCommentFileNameMap.containsKey(bugFile)){
                tempCommentFileName = bugCommentFileNameMap.get(bugFile);

                if(commentLinkMap.containsKey(tempCommentFileName)){
                    String commentString = String.valueOf(commentLinkMap.get(tempCommentFileName));
                    allComments = allComments + commentString;
                }
            }

            comments.setText(allComments);
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

    public void getForm()
    {
        frame.setTitle("Get Specific Bug UI");
        bugThread.setSize(1500,900); 
        bugThread.setVisible(true);

        bugThread.add(bugPanel);
        //bugPanel.setLayout(new GridLayout(1,0));
        bugPanel.setLayout(null);

        bugLabel.setBounds(10, 10, 150, 20);
        bugPanel.add(bugLabel);

        jsp.setBounds(130,10,1250,400);
        //bug.setSize(1250,450);
        bug.setLineWrap(true);
        bug.setEditable(false);

        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //bugPanel.add(bug);
        bugPanel.add(jsp);

        commentLabel.setBounds(10, 500, 150, 20);
        bugPanel.add(commentLabel);


        scrollC.setBounds(130,500,1250,200);
        //comments.setSize(1250,200);
        comments.setLineWrap(true);
        comments.setEditable(false);

        scrollC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //bugPanel.add(comments);
        bugPanel.add(scrollC);

        comments.setVisible(true);


        SearchController sc = new SearchController(bugFile);
        String specificBugFile = sc.searchByTitle();

        bugCommentFileNameMap = sc.getComDatabaseBCFNM();
        commentLinkMap = sc.getComDatabaseCLM();
        

        String bugFileName = "Bugs/" + specificBugFile;
        String allComments = "";


        try
        {
            FileReader fr = new FileReader(bugFileName);
            bug.read(fr, bugFileName);
            if(bugCommentFileNameMap.containsKey(specificBugFile)){
                tempCommentFileName = bugCommentFileNameMap.get(specificBugFile);
                System.out.println("new check : " + tempCommentFileName);

                if(commentLinkMap.containsKey(tempCommentFileName)){
                    String commentString = String.valueOf(commentLinkMap.get(tempCommentFileName));
                    allComments = allComments + commentString;
                }
            }

            comments.setText(allComments);
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
        String comm = commentArea.getText();
        AddCommentController acc = new AddCommentController(comm);

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
            bugThread.dispose();
        }
        else
        {
            //open comment frame
            addComment();
        }
    }
    
}

