package com.company;

import java.util.HashMap;

class LoginController {

    private final UserAccessDatabase uad = new UserAccessDatabase();
    private final HashMap<String, String> userAccountMap = uad.getUserAccountMap();
    private final HashMap<String, String> userRoleMap = uad.getUserRoleMap();

    public LoginController(){

    }

    public boolean authenticateUser(String userName, String password){

        if(userAccountMap.containsKey(userName)){
            return userAccountMap.get(userName).equals(password);
        }

        return false;
    }

    public void userRoleGUI(String userName){

        String userRole;

        if(userRoleMap.containsKey(userName)){
            userRole = userRoleMap.get(userName);

            switch (userRole) {
                case "Reporter":
                    BugReporterUI brUI = new BugReporterUI(userName);
                    break;
                case "Developer":
                    BugDeveloperUI bdUI = new BugDeveloperUI();
                    break;
                case "Triager":
                    TriagerUI tUI = new TriagerUI();
                    break;
                case "Reviewer":
                    BugReviewerUI brvUI = new BugReviewerUI();
                    break;
                default:
                    break;
            }
        }else{
            System.out.println("User account: " + userName + " cannot be found. Please try again");
        }
    }
}
