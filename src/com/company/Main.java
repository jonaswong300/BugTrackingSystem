package com.company;

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
        LoginUI loginUI = new LoginUI();

        //when user is bug reporter
        //how we know when its bug reporter, do we auto launch from the user class?
        //BugReporterUI bugUI = new BugReporterUI();

    }
}
