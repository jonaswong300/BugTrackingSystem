package com.company;

class User{

    private String userName;
    private String password;

    public User(){

    }

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
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
