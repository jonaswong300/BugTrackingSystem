package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

class UserAddBugController
{
    String title;
    String keywords;
    String description;
    SeverityLevel level;
    BugDatabase bd;

    public UserAddBugController(String title, String keywords, String description, SeverityLevel level) {
        this.title = title;
        this.keywords = keywords;
        this.description = description;
        this.level = level;
    }

    public boolean checkEmpty()
    {
        boolean empty = false;
        if(title.isEmpty() || keywords.isEmpty() || description.isEmpty())
        {
            empty = true;
        }
        return empty;
    }

    public String getDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();
        return formatter.format(date);
    }

    public void addBugsDetails()
    {
        String emptyDev = "";
        String solved = "open";
        bd = new BugDatabase();
        String bugID = bd.getNewBugID();
        bd.writeNewFileToDatabase(getDate());

        Bug report = new Bug(bugID,title, new ArrayList<>(Arrays.asList(keywords.split(" "))), description, emptyDev, level, solved);
        report.writeBugToFile();
        report.writeEmptyCommentFile();
    }

}