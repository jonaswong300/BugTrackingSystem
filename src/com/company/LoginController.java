package com.company;

import java.util.HashMap;

class LoginController {

    public LoginController(){

    }

    public boolean authenticUser(UserAccessDatabase uad, String userName, String password){

        HashMap<String, String> userAccountMap = uad.getUserAccountMap();

        if(userAccountMap.containsKey(userName)){
            return userAccountMap.get(userName).equals(password);
        }

        return false;
    }
}
