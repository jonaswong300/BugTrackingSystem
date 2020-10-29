package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        /*
        User u = new User("A", "password");
        Reporter r = new Reporter("A", "password");
        Triager t = new Triager("A", "password");
        Developer d = new Developer("A", "password");
        Reviewer re = new Reviewer("A", "password");

        System.out.println(u.toString());
        System.out.println(r.toString());
        System.out.println(t.toString());
        System.out.println(d.toString());
        System.out.println(re.toString());
         */

        UserAccessDatabase uad = new UserAccessDatabase();
        //LoginUI loginUI = new LoginUI();

        //BugDatabase bd = new BugDatabase();
        //bd.initializeMaps();
        //HashMap<Integer, Bug> bugMap = bd.getBugMap();

        //when user is bug reporter
        //how we know when its bug reporter, do we auto launch from the user class?
        //YES, we will do login then point to the UI via if else
        //BugReporterUI bugUI = new BugReporterUI();

        TriagerUI tUI = new TriagerUI();

        //BugDeveloperUI bdUI = new BugDeveloperUI();

        //searchUI test
        //searchUI search = new searchUI();
        //BugDatabase bd = new BugDatabase();
        //bd.writeNewFileToDatabase("02/03/2019");

    }
}
