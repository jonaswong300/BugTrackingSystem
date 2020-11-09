package com.company;

import java.util.HashMap;
import java.io.File;

class BugReviewerCloseBugReportController {

    public BugReviewerCloseBugReportController(){

    }

    public void closeReport(String bugID, String title, String remarks){
        BugDatabase bd = new BugDatabase();

        HashMap<String, Bug> bugMap = bd.getBugMap();
        String pad = "0000";
        bugID = pad.substring(0, pad.length() - bugID.length()) + bugID;
        bugID = "Bugs/Bug" + bugID + ".txt";

        if(bugMap.containsKey(bugID)){
            bugMap.get(bugID).setSolved("closed");
            System.out.println("Bug ID: " + bugID);
            if(title.equals("")){
               title = bugMap.get(bugID).getTitle();
               
            }

            File f = new File(bugID);
            f.delete();
            bugMap.get(bugID).writeBugToFile();
            
            System.out.println("Bug Title:" + title);

            System.out.println("Bug has been resolved and closed by bug developer");

                

        }else{
            System.out.println("Bug database does not contain this Bug of ID " + bugID);
            System.out.println("Please try again.");
        }
    }

}
