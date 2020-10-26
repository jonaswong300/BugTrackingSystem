package com.company;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class Bug {
    private String title;
    private String ID;
    //i changed the keywords to arraylist so can search easier later
    private ArrayList<String> keywords_AL = new ArrayList<>();
    private String description;
    private String assignDeveloper;
    private boolean solved;
    private String filename;

    public Bug()
    {
    }

    public Bug(String fileName)
    {
        filename = fileName;   
    }

    public Bug(String title, String keywords, String description, String ID, String assignDeveloper, boolean solved){
        this.title = title;
        this.assignDeveloper = assignDeveloper;
        this.description = description;
        this.solved = solved;
        this.ID = ID;

        //seperate the keyword string into arraylist, delimiter ","
        String [] splitKeywords = keywords.split(" ");
        for(String key : splitKeywords)
        {
            keywords_AL.add(key);
        }

        
        writeBugToFile();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID()
    {
        return ID;
    }

    public ArrayList<String> getKeywords()
    {
        return keywords_AL;
    }

    public void setKeywords(String keywords)
    {
        String [] splitKeywords = keywords.split(" ");
        for(String key : splitKeywords)
        {
            keywords_AL.add(key);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignDeveloper() {
        return assignDeveloper;
    }

    public void setAssignDeveloper(String assignDeveloper) {
        this.assignDeveloper = assignDeveloper;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public void writeBugToFile()
    {
        String fileName = "Bug" + ID + ".txt";
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            StringBuilder toWrite = new StringBuilder("Title : " + title + "\n"
                    + "Keywords : ");
            for(String s : keywords_AL)
            {
                toWrite.append(s).append(",");
            }

            toWrite.append("\nAssigned Developer : ").append(assignDeveloper).append("\n");
            String solved_S = "";
            if(solved)
            {
                solved_S = "closed";
            }
            else
            {
                solved_S = "open";
            }
            toWrite.append("\nSolved status : ").append(solved_S);
            toWrite.append("\nBug : ").append(description);
            fw.write(toWrite.toString());

            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

}
