package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.IOException;

class AddCommentController implements ActionListener
{
    String comment;
    Date d;
    Date t;
    String d_S;
    String t_S;

    public AddCommentController()
    {
        
    }

    public AddCommentController(String comment)
    {
        this.comment = comment;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        SimpleDateFormat t_formet = new SimpleDateFormat("HH:mm:ss");
        d = new Date();
        t = new Date();
        d_S = formatter.format(d);
        t_S = t_formet.format(t);

        //new Comment object
        Comment c = new Comment(d_S, t_S, comment);
        //System.out.println(comment);
    }

    public String getID(int id)
    {
        String id_S = "";
        return id_S;
    }

    public void writeCommentToFile(String id)
    {
        if(id.trim().isEmpty())
        {
            id = "Comment0000.txt";
        }
        String fileName = "Comments/" + id;
        String current_Comments = "";
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            String toWrite = "Date : " + d_S + " " + t_S + "\n" + "Comment : " + comment + ",\n";
            //System.out.println("HERE : \n" + toWrite);
            fw.write(toWrite);

            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        //c.setID();
        //c.writeCommentToFile();


    }

}

