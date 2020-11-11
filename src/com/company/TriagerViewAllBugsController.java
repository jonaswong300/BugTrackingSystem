package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TriagerViewAllBugsController 
{
    String bugFile;

    public TriagerViewAllBugsController()
    {

    }

    public TriagerViewAllBugsController(String bugFile)
    {
        this.bugFile = bugFile;
        //flagDup();
    }

    public HashMap<Integer, String> getFilesMap()
    {
        BugFileAccess bd = new BugFileAccess();
        return bd.getFileMap();
    } 
    public HashMap<String, String> getTitlesMap()
    {
        BugFileAccess bd = new BugFileAccess();
        return bd.getTitleMap();
    }

    public String flagInv()
    {
        BugFileAccess bd = new BugFileAccess();
        HashMap<String, Bug> bugMap = bd.getBugMap();
        SearchController sc = new SearchController(bugFile);
        bugFile = sc.searchByTitle();
        Bug b = new Bug();

        if(bugFile.contains("!"))
        {

            String fullFile = "Bugs/" + bugFile;
            for(Map.Entry me : bugMap.entrySet())
            {
                if(me.getKey().equals(fullFile))
                {
                    b = (Bug) me.getValue();
                    b.setSolved("closed (FLAGGED AS INVALID)");
                    b.setAssignDeveloper("N/A due to invalid bug");
                    String fileID = b.getID();
                    File f = new File(bugFile);
                    f.delete();
                    b.writeBugToFile();
    
                }
            }
            
            //get rid of ! in bugfiledatabase.txt
            CommentAccess cd = new CommentAccess();
            bd.setFileName(bugFile);
            cd.setFileName(bugFile);
    
            //delete the old file with the "!"
            File toDel = new File("Bugs/" + bugFile);
            toDel.delete();
            return "true";
        }
        else
        {
            String s = "";
            String fullFile = "Bugs/" + bugFile;
            for(Map.Entry me : bugMap.entrySet())
            {
                if(me.getKey().equals(fullFile))
                {
                    b = (Bug) me.getValue();
                    s = b.getAssignDeveloper();
                }
            }

            if(s.isEmpty())
            {
                return "false";
            }
            else
            {
                return "dev";
            }
        }
    }

    public String flagDup()
    {
        //get the bugfile
        //match to the corresponding bug in the bugmap 
        //set the solved status
        BugFileAccess bd = new BugFileAccess();
        HashMap<String, Bug> bugMap = bd.getBugMap();
        SearchController sc = new SearchController(bugFile);
        bugFile = sc.searchByTitle();
        Bug b = new Bug();

        if(bugFile.contains("!"))
        {

            String fullFile = "Bugs/" + bugFile;
            for(Map.Entry me : bugMap.entrySet())
            {
                if(me.getKey().equals(fullFile))
                {
                    b = (Bug) me.getValue();
                    b.setSolved("closed (FLAGGED AS DUPLICATE)");
                    b.setAssignDeveloper("N/A due to duplicate bug");
                    String fileID = b.getID();
                    File f = new File(bugFile);
                    f.delete();
                    b.writeBugToFile();
    
                }
            }
            
            //get rid of ! in bugfiledatabase.txt
            CommentAccess cd = new CommentAccess();
            bd.setFileName(bugFile);
            cd.setFileName(bugFile);
    
            //delete the old file with the "!"
            File toDel = new File("Bugs/" + bugFile);
            toDel.delete();
            return "true";
        }
        else
        {
            String s = "";
            String fullFile = "Bugs/" + bugFile;
            for(Map.Entry me : bugMap.entrySet())
            {
                if(me.getKey().equals(fullFile))
                {
                    b = (Bug) me.getValue();
                    s = b.getAssignDeveloper();
                }
            }

            if(s.isEmpty())
            {
                return "false";
            }
            else
            {
                return "dev";
            }
        }
    }
}
