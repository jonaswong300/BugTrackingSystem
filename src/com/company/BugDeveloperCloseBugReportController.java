package com.company;

import java.util.HashMap;

class BugDeveloperCloseBugReportController {

    public BugDeveloperCloseBugReportController(){

    }

    public void closeReport(String bugID, String title, String remarks){
        BugDatabase bd = new BugDatabase();

        HashMap<String, Bug> bugMap = bd.getBugMap();

        if(bugMap.containsKey(bugID)){
            bugMap.get(bugID).setSolved("closed");
            System.out.println("Bug ID: " + bugID);
            if(title.equals("")){
               title = bugMap.get(bugID).getTitle();
            }
            System.out.println("Bug Title:" + title);

            System.out.println("Bug has been resolved and closed by bug developer");
        }else{
            System.out.println("Bug database does not contain this Bug of ID " + bugID);
            System.out.println("Please try again.");
        }
    }

}
