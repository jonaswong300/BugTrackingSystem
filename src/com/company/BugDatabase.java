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
    private HashMap<String, String> titles = new HashMap<>();

    public BugDatabase()
    {
        getFileMap();
    }

    /*public BugDatabase(String bugReporter)
    {
        bugReporterName = bugReporter;
    }*/

    public void getFileMap()
    {
        try{
            //File bugFD = new File("BugFileDatabase.txt");
            FileReader fr = new FileReader("BugFileDatabase.txt");
            Scanner input = new Scanner(fr);

            int idCount = 0;
            String str = "";
            String pad = "0000";
            while(input.hasNextLine())
            {
                input.nextLine();
                str = String.valueOf(idCount);
                String file = "Bug" + (pad.substring(0, pad.length() - str.length()) + str) + ".txt";
                fileMap.put(idCount, file);
                idCount++;
            }

            fr.close();
            input.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

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
*/
    public HashMap<String, String> getTitleMap() 
    {
        for(Map.Entry<Integer, String> test : fileMap.entrySet())
        {
            try
            {
                
                FileReader fr = new FileReader(test.getValue());
                Scanner input = new Scanner(fr);
                String title = input.nextLine();
                String [] split = title.split(" : ");
                titles.put(test.getValue(), split[1]);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        return titles;
    }

    public String returnAddBugResponse()
    {
        return "";
    }

}
 