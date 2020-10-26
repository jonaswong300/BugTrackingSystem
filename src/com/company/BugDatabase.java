package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;

class BugDatabase {

    //private String bugReporterName = "";


    //bugFileStoring, int is bugid and string is filename
    //eg. <0,Bug0000.txt> <1,Bug0001.txt>
    private HashMap<Integer, String> fileMap = new HashMap<>();

    //holds the titles of all files
    //eg. <Bug0000.txt, title>
    private HashMap<String, String> titleMap = new HashMap<>();

    public BugDatabase()
    {
        initializeMaps();
    }


    public void initializeMaps(){
        try{
            FileReader fr = new FileReader("BugFileDatabase.txt");
            Scanner input = new Scanner(fr);
            Scanner in;

            int idCounter = 0;
            String [] temp, split;

            while(input.hasNextLine()){
                temp = input.nextLine().split(",");
                fileMap.put(idCounter, temp[0]);
                idCounter++;

                //Edit this line if folder name changes
                FileReader reader = new FileReader("Bugs/" + temp[0]);
                in = new Scanner(reader);
                split = in.nextLine().split(" : ");
                titleMap.put(temp[0], split[1]);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Rewrote this function to combine to initializeMap
    public HashMap<Integer, String> getFileMap()
    {

        return fileMap;
    }

    public String getNewBugID()
    {
        int idCount = 0;
        String str = "";
        String pad = "0000";
        try{
            //File bugFD = new File("BugFileDatabase.txt");
            FileReader fr = new FileReader("BugFileDatabase.txt");
            Scanner input = new Scanner(fr);

            while(input.hasNextLine())
            {
                input.nextLine();   //FOR SOME REASON, THE CODE DOESN'T WORK WITHOUT THIS LINE, DONT REMOVE IT
                idCount++;
            }

            fr.close();
            input.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        str = String.valueOf(idCount);
        return pad.substring(0, pad.length() - str.length()) + str;
    }

    public void writeNewFileToDatabase(String date)
    {
        String fileName = "Bug" + getNewBugID() + ".txt";
        try
        {
            FileWriter fw = new FileWriter("BugFileDatabase.txt", true);
            String toWrite = fileName + ", " + date + "\n";
            fw.write(toWrite);

            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    /*
    public void addBugToDatabase(Bug report)
    {

        try{
            FileWriter fw = new FileWriter("bugDatabase.txt", true);

            fw.write("Title : " + report.getTitle() + " Keywords : " + report.getKeywords() + 
                    " Description : " + report.getDescription() + " Assignee : " + report.getAssignDeveloper() +
                    " Solved : " + report.isSolved() + ", \n");
            
            //Bug newBug = new Bug(title, keywords, description, assignDeveloper, false);
            bugMap.put(bugReporterName, report);

            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
*/

    //Rewrote this function to combine to initializeMap
    public HashMap<String, String> getTitleMap() 
    {
        return titleMap;
    }



    public String returnAddBugResponse()
    {
        return "";
    }

}
 