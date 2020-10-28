package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

class Comment 
{
    HashMap<Integer, String> commentMap = new HashMap<>();
    LinkedList <String> linkedComments = new LinkedList<>();

    String filename;
    public Comment()
    {
    }   
    public Comment(String filename, String comment)
    {
        //enter into a hashmap
        commentMap.put(getCommentID(), comment);
        this.filename = filename;
    }

    public int getCommentID()
    {
        int id = 0;
        try{
            File bugF = new File(filename + ".txt");
            FileReader fr = new FileReader(bugF);
            Scanner input = new Scanner(fr);

            while(input.hasNextLine())
            {
                id++;
            }

            fr.close();
            input.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return id;
    }

    public HashMap<Integer, String> getCommentHash()
    {
        return commentMap;
    }
}
