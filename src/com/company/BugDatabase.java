package com.company;

import java.util.HashMap;

class BugDatabase {

    private HashMap<String, Bug> bugMap = new HashMap<>();

    public BugDatabase(){

    }

    private void readBugFromFile(){

    }

    public HashMap<String, Bug> getBugMap() {
        return bugMap;
    }
}
