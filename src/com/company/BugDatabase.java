package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

class BugDatabase {

    //btw i made the delimiter for the bug database ", \n"

    private HashMap<String, Bug> bugMap = new HashMap<>();
    private String bugReporterName = "";

    public BugDatabase()
    {

    }

    public BugDatabase(String bugReporter)
    {
        bugReporterName = bugReporter;
    }

    public void addBugToDatabase(Bug report)
    {

        try{
            FileWriter fw = new FileWriter("bugDatabase.txt", true);

            fw.write("Title : " + report.getTitle() + " Keywords : " + report.getKeywords() + 
                    " Description : " + report.getDescription() + " Assignee : " + report.getAssignDeveloper() +
                    " Solved : " + report.isSolved() + ", \n");
            
            //Bug newBug = new Bug(title, keywords, description, assignDeveloper, false);
            bugMap.put(bugReporterName, report);

            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public HashMap<String, Bug> getBugMap() {
        return bugMap;
    }

    public String returnAddBugResponse()
    {
        return "";
    }
}
