package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

class SearchController
{
    String searchTerms;

    public SearchController()
    {

    }

    public SearchController(String searchTerms)
    {
        this.searchTerms =searchTerms;
    }
    
    public HashMap<String, String> getComDatabaseBCFNM()
    {
        CommentAccess cd = new CommentAccess();
        return cd.getBugCommentFileNameMap();
    }

    public HashMap<String, Comment> getComDatabaseCLM()
    {
        CommentAccess cd = new CommentAccess();
        return cd.getCommentLinkMap();
    }

    public String searchByTitle()
    {
        String fileReturn = "";
        BugFileAccess bd = new BugFileAccess();
        HashMap<String, String> tMap = new HashMap<>();
        tMap = bd.getTitleMap();


        for (Map.Entry<String, String> entry : tMap.entrySet()) {
            if(entry.getValue().equals(searchTerms))
            {
                fileReturn = entry.getKey();
            }
        }
        return fileReturn;
    }
    public boolean checkValidKeyword()
    {
        boolean valid = false;
        BugFileAccess bd = new BugFileAccess();
        ArrayList<String> keywords = bd.getKeywordsList();

        for(String s : keywords)
        {
            String [] split = s.split(" : ");
            if (split[0].equals(searchTerms)) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public ArrayList<String> searchByKeywords()
    {
        BugFileAccess bd = new BugFileAccess();
        ArrayList<String> keywords = bd.getKeywordsList();
        ArrayList<String> keywordsFilesList = new ArrayList<String>();

        for(String s : keywords)
        {
            String [] split = s.split(" : ");
            if(split[0].equals(searchTerms))
            {
                keywordsFilesList.addAll(Arrays.asList(split).subList(1, split.length));
            }
        }

        return keywordsFilesList;

    }

    public boolean checkDevExists()
    {
        boolean exists = false;
        BugFileAccess bd = new BugFileAccess();
        HashMap<String, String> devMap = new HashMap<>();
        devMap = bd.getDevMap();
        
        for(Map.Entry me : devMap.entrySet())
        {
            if(me.getValue().equals(searchTerms))
            {
                exists = true;
                break;
            }
        }

        return exists;
    }

    public ArrayList<String> searchByDev()
    {

        //get hash map of all the devs eg. <Bug0001.txt, devName>
        //then using the search term, if devname is correct, add the file to the arraylist.
        //display gui of buttons with the bug file title name, then, if the button gets clicked on, show the bug.
        ArrayList<String> devFilesList = new ArrayList<>();
        BugFileAccess bd = new BugFileAccess();
        HashMap<String, String> devMap = new HashMap<>();
        devMap = bd.getDevMap();
        for(Map.Entry<String, String> entry : devMap.entrySet())
        {
            if(entry.getValue().trim().equals(searchTerms))
            {
                devFilesList.add(entry.getKey());
            }
        }
        return devFilesList;

    }

    public boolean checkSeverityLevel(){
        int tempLevel = Integer.parseInt(searchTerms);

        return tempLevel >= 0 && tempLevel < 4;
    }

    public ArrayList<String> searchBySeverityLevel () {
        BugFileAccess bd = new BugFileAccess();
        HashMap<String, SeverityLevel> severityMap = bd.getSevereMap();

        ArrayList<String> severeFilesList = new ArrayList<>();

        SeverityLevel level;

        switch (searchTerms) {
            case "0":
                level = SeverityLevel.LOW;
                break;
            case "1":
                level = SeverityLevel.MEDIUM;
                break;
            case "2":
                level = SeverityLevel.HIGH;
                break;
            case "3":
                level = SeverityLevel.CRITICAL;
                break;
            default:
                level = SeverityLevel.valueOf("NULL");
                break;
        }

        for (String s : severityMap.keySet()) {
            if (severityMap.get(s).equals(level)) {
                severeFilesList.add(s);
            }
        }

        return severeFilesList;
    }
}
