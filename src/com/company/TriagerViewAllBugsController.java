package com.company;

import java.io.File;
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
        Bug b = new Bug();

        for(Map.Entry me : bugMap.entrySet())
        {
            if(me.getKey().equals(bugFile))
            {
                b = (Bug) me.getValue();
                System.out.println(b);
                b.setSolved("closed (FLAGGED AS DUPLICATE)");
                b.setAssignDeveloper("N/A due to duplicate bug");
                String fileID = b.getID();
                System.out.println(fileID);
                File f = new File(bugFile);
                f.delete();
                b.writeBugToFile();

            }
        }
    }
}
