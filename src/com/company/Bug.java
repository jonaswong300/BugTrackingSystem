package com.company;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

//LOW, MEDIUM, HIGH, CRITICAL
//0  , 1,    , 2,  , 3
enum SeverityLevel {
    LOW, MEDIUM, HIGH, CRITICAL
}

class Bug {
    private String title;
    private String ID;
    //i changed the keywords to arraylist so can search easier later
    private ArrayList<String> keywords_AL = new ArrayList<>();
    private String description;
    private String assignDeveloper;
    private String reporter;
    private String solved;
    private String filename;
    //private SeverityLevel level;
    private String level;


    public Bug()
    {
    }

    public Bug(String fileName)
    {
        filename = fileName;   
    }

    public Bug(String ID, String title, ArrayList<String> keywords, String description, String reporter, String assignDeveloper, String solved){
        this.ID = ID;
        this.title = title;
        this.keywords_AL = keywords;
        this.reporter = reporter;
        this.assignDeveloper = assignDeveloper;
        this.description = description;
        this.solved = solved;
    }

    public Bug(String ID, String title, ArrayList<String> keywords, String description, String reporter, String assignDeveloper, String level, String solved){
        this.ID = ID;
        this.title = title;
        this.keywords_AL = keywords;
        this.reporter = reporter;
        this.assignDeveloper = assignDeveloper;
        this.description = description;
        this.solved = solved;
        this.level = level;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID()
    {
        String str = ID;
        String pad = "0000";
        ID = pad.substring(0, pad.length() - str.length()) + str;
        return ID;
    }

    public ArrayList<String> getKeywords()
    {
        return keywords_AL;
    }

    public void setKeywords(String keywords)
    {
        String [] splitKeywords = keywords.split(" ");
        keywords_AL.addAll(Arrays.asList(splitKeywords));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReporter()
    {
        return reporter;
    }

    public String getAssignDeveloper() {
        return assignDeveloper;
    }

    public void setAssignDeveloper(String assignDeveloper) {
        this.assignDeveloper = assignDeveloper;
    }

    public String isSolved() {
        return solved;
    }

    public void setSolved(String solved) 
    {
        this.solved = solved;
    }

    public void writeEmptyCommentFile()
    {
        String fileName = "Comments/Comment" + getID() + ".txt";
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            String empty = "";
            fw.write(empty);
            fw.close();
        }
        catch(IOException e )
        {
            e.printStackTrace();
        }
    }

    public void writeBugToFile()
    {
        String fileName = "Bug" + getID() + ".txt";
        if(assignDeveloper.isEmpty())
        {
            fileName = "Bugs/!" + fileName;
        }
        else
        {
            fileName = "Bugs/" + fileName;
        }
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            StringBuilder toWrite = new StringBuilder("Title : " + title + "\n"
                    + "Keywords : ");
            for(String s : keywords_AL)
            {
                toWrite.append(s).append(",");
            }

            toWrite.append("\nReporter : ").append(reporter);
            toWrite.append("\nAssigned Developer : ").append(assignDeveloper).append("\n");
            toWrite.append("Severity Level : ").append(level).append("\n");
            toWrite.append("Solved status : ").append(solved).append("\n");
            toWrite.append("Bug : ").append(description);
            fw.write(toWrite.toString());

            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public String toString(){
        return  "Bug Id: " + ID + "\n" +
                "Bug Title: " + title + "\n" +
                "Keywords: " +  keywords_AL + "\n" +
                "Description: " + description + "\n" +
                "Reporter : " + reporter + "\n" +
                "Assigned Developer: " + assignDeveloper + "\n" +
                "Solved: " + solved + "\n";
    }
}
