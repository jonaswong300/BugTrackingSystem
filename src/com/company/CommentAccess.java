package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class CommentAccess {

    //Holds all the title of all files
    //Bug id, comment file name
    //eg. <1, comment0000.txt>
    private final HashMap<Integer, String> commentMap = new HashMap<>();

    //Hold bug file name to comment file name
    private final HashMap<String, String> BugCommentFileNameMap = new HashMap<>();

    //Holds all the comments in a file based on commentID
    //Comment id, linklist of comments
    private final HashMap<String, Comment> commentLinkMap = new HashMap<>();

    public CommentAccess(){
        initializeMaps();
    }

    public void initializeMaps(){
        try{

            FileReader fr = new FileReader("CommentFileDatabase.txt");
            Scanner input = new Scanner(fr);

            int idCounter = 0;
            String [] temp;

            while(input.hasNextLine()){
                temp = input.nextLine().split(",");
                commentMap.put(idCounter, temp[1]);
                BugCommentFileNameMap.put(temp[0], temp[1]);

                createCommentObject(temp[1], idCounter);
                idCounter++;
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void createCommentObject(String commentFileName, int commentID){
        String date, time;
        StringBuilder description;
        String [] temp;

        try{
            //EDIT THIS LINE IF FOLDER NAME CHANGE
            FileReader fr = new FileReader("Comments/" + commentFileName);
            Scanner input = new Scanner(fr);


            if(input.hasNextLine()){
                temp = input.nextLine().split(":");
                date = temp[1].split(" ")[1];
                time = temp[1].split(" ")[2] + ":" + temp[2] + ":" + temp[3];

                description = new StringBuilder(input.nextLine().split(":")[1]);

                String tempDescription;
                while(input.hasNextLine()){

                    tempDescription = input.nextLine();
                    if(tempDescription.contains("Date")){
                        description.append("\n\n").append(tempDescription).append("\n").append(input.nextLine());
                    }

                }

                Comment c = new Comment(commentFileName, String.valueOf(commentID), date, time, description.toString());
                commentLinkMap.put(commentFileName, c);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setFileName(String fileName)
    {
        try
        {
            FileReader fr = new FileReader("CommentFileDatabase.txt");
            Scanner input = new Scanner(fr);
            StringBuilder holdAll = new StringBuilder();
            String toChange = fileName.replace("!", "");

            while(input.hasNextLine())
            {
                holdAll.append(input.nextLine()).append(System.lineSeparator());
            }
            
            String fileContents = holdAll.toString();
            fileContents = fileContents.replace(fileName, toChange);
            

            //System.out.println(fileContents);
            FileWriter writer = new FileWriter("CommentFileDatabase.txt");
            //System.out.println(fileContents);
            writer.append(fileContents);
            writer.close();
            

            fr.close();
            input.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public HashMap<Integer, String> getCommentMap(){
        return commentMap;
    }

    public HashMap<String, String> getBugCommentFileNameMap(){
        return BugCommentFileNameMap;
    }

    public HashMap<String, Comment> getCommentLinkMap(){
        return commentLinkMap;
    }
}
