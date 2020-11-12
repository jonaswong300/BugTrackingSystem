package com.company;

class Comment 
{
    String ID;
    String fileName;
    String date;
    String time;
    String description;

    public Comment()
    {
    }


    public Comment (String fileName,String ID, String date, String time, String description){
        this.ID = ID;
        this.fileName = fileName;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public Comment(String date, String time, String description){
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString(){
        return  "===================================" + "\n" +
                "Comment ID: " + ID + "\n" +
                "Date: " + date + " " + time + "\n" +
                "Comments: " +  description + "\n";
    }
}
