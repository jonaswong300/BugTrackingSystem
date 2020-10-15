package com.company;

class userAddBugController
{
    String title;
    String keywords;
    String description;

    public userAddBugController(String title, String keywords, String description)
    {
        this.title = title;
        this.keywords = keywords;
        this.description = description;
        addBugsDetails();
    }

    public void addBugsDetails()
    {
        String randomDev = "randomDev";
        boolean solved = false;
        Bug report = new Bug(title, keywords, description, randomDev, solved);
        BugDatabase bd = new BugDatabase();
        bd.addBugToDatabase(report);
    }

}