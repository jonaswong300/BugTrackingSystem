package com.company;

import java.util.HashMap;
import java.util.Map;

class searchController 
{
    String searchTerms;

    public searchController()
    {

    }

    public searchController(String searchTerms)
    {
        this.searchTerms =searchTerms;
    }

    public String searchByTitle()
    {
        String fileReturn = "";
        BugDatabase bd = new BugDatabase();
        HashMap<String, String> tMap = new HashMap<>();
        tMap = bd.getTitleMap();

        for(String s : tMap.keySet())
            System.out.println(s);

        for (Map.Entry<String, String> entry : tMap.entrySet()) {
            if(entry.getValue().trim().equals(searchTerms))
            {
                fileReturn = entry.getKey();
            }
        }

        return fileReturn;
    }
    public void searchByKeywords()
    {

    }
    public void searchByDev()
    {

    }
}
