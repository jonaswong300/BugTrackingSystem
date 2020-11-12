package com.company;

class User{

    private String userName;
    private String password;
    private String role;

    public User()
    {

    }

    public User(String userName, String password, String role){
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){return role;}

    public String toString(){
        return "I am in user";
    }

}

class Reporter extends User{

    public Reporter(String userName, String password, String role) {
        super(userName, password, role);
    }

    public String toString(){
        return "I am in Reporter";
    }
    

}

class Triager extends User{

    public Triager(String userName, String password, String role) {
        super(userName, password, role);
    }

    public String toString(){
        return "I am in Triager";
    }
}

class Developer extends User{

    public Developer(String userName, String password, String role) {
        super(userName, password, role);
    }

    public String toString(){
        return "I am in Developer";
    }
}

class Reviewer extends User{

    public Reviewer(String userName, String password, String role) {
        super(userName, password, role);
    }

    public String toString(){
        return "I am in Reviewer";
    }
}
