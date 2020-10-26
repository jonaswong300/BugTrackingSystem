package com.company;

import java.text.SimpleDateFormat;  
import java.util.Date;

class userAddBugController
{
    String title;
    String keywords;
    String description;

    public userAddBugController()
    {

    }

    public userAddBugController(String title, String keywords, String description)
    {
        
        this.title = title;
        this.keywords = keywords;
        this.description = description;
        //addBugsDetails();
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
        String date_S = formatter.format(date);
        return date_S;
    }

    public void addBugsDetails()
    {
        String randomDev = "randomDev";
        boolean solved = false;
        BugDatabase bd = new BugDatabase();
        String bugID = bd.getNewBugID();
        bd.writeNewFileToDatabase(getDate());
        Bug report = new Bug(title, keywords, description, bugID, randomDev, solved);
    }

}