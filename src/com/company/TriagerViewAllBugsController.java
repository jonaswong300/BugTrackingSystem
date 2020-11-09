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
        flagDup();
    }

    public void flagDup()
    {
        //get the bugfile
        //match to the corresponding bug in the bugmap 
        //set the solved status
        BugDatabase bd = new BugDatabase();
        HashMap<String, Bug> bugMap = bd.getBugMap();
        SearchController sc = new SearchController(bugFile);
        bugFile = sc.searchByTitle();
        Bug b = new Bug();

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
        CommentDatabase cd = new CommentDatabase();
        bd.setFileName(bugFile);
        cd.setFileName(bugFile);

        //delete the old file with the "!"
        File toDel = new File("Bugs/" + bugFile);
        toDel.delete();
        
    }
}
