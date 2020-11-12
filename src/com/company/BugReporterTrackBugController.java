package com.company;

import java.util.HashMap;

class BugReporterTrackBugController {

    private BugFileAccess bd = new BugFileAccess();

    public BugReporterTrackBugController(){

    }

    public void searchByBugTitle(String bugTitle){
        HashMap<String, String> titleMap = bd.getTitleMap();

        //for(String s : titleMap.keySet())
            //System.out.println(s + " " + titleMap.get(s));

        if(titleMap.containsKey(bugTitle)){
            System.out.println(bugTitle + " " + titleMap.get(bugTitle));
        }else{
            System.out.println("Error. Bug title does not exist in the database system.");
        }

    }

    public void searchByBugId(String bugID){
        HashMap<Integer, String> fileMap = bd.getFileMap();

        //for(Integer i : fileMap.keySet())
            //System.out.println(i + " " + fileMap.get(i));

        if(fileMap.containsKey(Integer.parseInt(bugID))){
            System.out.println(bugID + " " + fileMap.get(Integer.parseInt(bugID)));
        }else{
            System.out.println("Error. Bug title does not exist in the database system.");
        }

    }
}
