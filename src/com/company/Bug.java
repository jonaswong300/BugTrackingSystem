package com.company;

import java.util.ArrayList;

class Bug {
    private String title;
    //i changed the keywords to arraylist so can search easier later
    private ArrayList<String> keywords_AL = new ArrayList<>();
    private String description;
    private String assignDeveloper;
    private boolean solved;

    public Bug(){}

    public Bug(String title, String keywords, String description, String assignDeveloper, boolean solved){
        this.title = title;
        this.description = description;
        this.assignDeveloper = assignDeveloper;
        this.solved = solved;

        //seperate the keyword string into arraylist, delimiter ","
        String [] splitKeywords = keywords.split(" ");
        for(String key : splitKeywords)
        {
            keywords_AL.add(key);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getKeywords()
    {
        return keywords_AL;
    }

    public void setKeywords(String keywords)
    {
        String [] splitKeywords = keywords.split(" ");
        for(String key : splitKeywords)
        {
            keywords_AL.add(key);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignDeveloper() {
        return assignDeveloper;
    }

    public void setAssignDeveloper(String assignDeveloper) {
        this.assignDeveloper = assignDeveloper;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }


}
