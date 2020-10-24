package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

class BugDatabase {

    //btw i made the delimiter for the bug database ", \n"

    private HashMap<String, Bug> bugMap = new HashMap<>();
    private String bugReporterName = "";


    //bugFileStoring, int is bugid and string is filename
    private HashMap<Integer, String> fileMap = new HashMap<>();

    public BugDatabase()
    {

    }

    public BugDatabase(String bugReporter)
    {
        bugReporterName = bugReporter;
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
                System.out.println(input.nextLine());   //FOR SOME REASON, THE CODE DOESN'T WORK WITHOUT THIS LINE, DONT REMOVE IT
                idCount++;
            }

            fr.close();
            input.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        str = String.valueOf(idCount);
        String ans = pad.substring(0, pad.length() - str.length()) + str;
        return ans;
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

    public HashMap<String, Bug> getBugMap() {
        return bugMap;
    }*/

    public String returnAddBugResponse()
    {
        return "";
    }

}
 