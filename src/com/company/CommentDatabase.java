package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class CommentDatabase {

    //Holds all the title of all files
    //Bug id, comment file name
    //eg. <1, comment0000.txt>
    private final HashMap<Integer, String> commentMap = new HashMap<>();

    //Holds all the comments in a file based on commentID
    //Comment id, linklist of comments
    private final HashMap<Integer, LinkedList<Comment>> commentLinkMap = new HashMap<>();

    public CommentDatabase(){
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

                //Edit this line if folder name changes
                String commentFileName = "Comments/" + temp[1];

                createCommentObject(commentFileName, idCounter);
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
            FileReader fr = new FileReader(commentFileName);
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
                System.out.println(c);

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
