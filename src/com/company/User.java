package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class User{

    private String userName;
    private String password;
    private ArrayList<String> userList = new ArrayList<String>();
    private HashMap<String, String> uPwList = new HashMap<String, String>();


    public User()
    {
        getAllUsers();
    }

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        getAllUsers();
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String toString(){
        return "I am in user";
    }

    public ArrayList<String> getAllUsers()
    {
        try{
            System.out.println("\nReading from file userAccount.txt");
            FileReader fw = new FileReader("userAccount.txt");

            Scanner input = new Scanner(fw);

            while(input.hasNextLine()){
                userList.add(input.nextLine());
            }

            fw.close();
            input.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        return userList;

    }

    public void matchUserandPw()
    {
        for(String s : userList)
        {
            String [] k = s.split("	");
            uPwList.put(k[0], k[1]);
        }

    }

    public boolean checkUser(String username, String password)
    {
        boolean check = false;

        matchUserandPw();
        if(uPwList.containsKey(username))
        {
            check = checkPW(username, password);
        }
        return check;
    }

    public boolean checkPW(String username, String password)
    {
        boolean check;
        check = uPwList.get(username).equals(password);
        return check;
    }
}

class Reporter extends User{

    public Reporter(String userName, String password) {
        super(userName, password);
    }

    public String toString(){
        return "I am in Reporter";
    }
    

}

class Triager extends User{

    public Triager(String userName, String password) {
        super(userName, password);
    }

    public String toString(){
        return "I am in Triager";
    }
}

class Developer extends User{

    public Developer(String userName, String password) {
        super(userName, password);
    }

    public String toString(){
        return "I am in Developer";
    }
}

class Reviewer extends User{

    public Reviewer(String userName, String password) {
        super(userName, password);
    }

    public String toString(){
        return "I am in Reviewer";
    }
}
