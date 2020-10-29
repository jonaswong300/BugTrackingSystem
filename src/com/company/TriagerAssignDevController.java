package com.company;

import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class TriagerAssignDevController 
{
    //get bugfilesdatabase.txt and check for if there's '!' in front of the filename.
    //if have then put into an arraylist of available bugs to assign
    //get a list of developers
    //dropdown for available bugs and dropdown for devs
    //let the triager see the full bug report before they assign 
    //check and assign button
    //check button is to check the bug report, assign is to set developer
    //check is like extra but if dont have then a bit strange

    //hold all the bugfile names eg. [Bug0001.txt, Bug0002.txt, etc]
    ArrayList<String> allUnassignedFiles = new ArrayList<>();
    //get all developers
    ArrayList<String> allDevelopers = new ArrayList<>();

    public TriagerAssignDevController()
    {
        initialiseMaps();
    }

    public void initialiseMaps()
    {
        //initialise allUnassignedFiles
        BugDatabase bd = new BugDatabase();
        for(Map.Entry entry : bd.getFileMap().entrySet())
        {
            String check =  String.valueOf(entry.getValue());
            if(check.charAt(0) == '!')
            {
                allUnassignedFiles.add(check);
            }
        }


        //initialise allDevelopers
        //get userAccount.txt and split by whitespace to get all devs
        try
        {
            FileReader fr = new FileReader("userAccount.txt");
            Scanner input = new Scanner(fr);
            while(input.hasNextLine())
            {
                String [] split = input.nextLine().split("\t");
                String name = split[0];
                String dev = split[2];
                if(!allDevelopers.contains(dev) && dev.equals("Developer"))
                {
                    allDevelopers.add(name);
                }
            }
        }
        catch(IOException e )
        {
            e.printStackTrace();
        }
        //get userAccount.txt and split by whitespace to get all devs
        try
        {
            FileReader fr = new FileReader("userAccount.txt");
            Scanner input = new Scanner(fr);
            while(input.hasNextLine())
            {
                String [] split = input.nextLine().split("\t");
                String name = split[0];
                String dev = split[2];
                if(!allDevelopers.contains(dev) && dev.equals("Developer"))
                {
                    allDevelopers.add(name.trim());
                }
            }
            fr.close();
            input.close();
        }
        catch(IOException e )
        {
            e.printStackTrace();
        }
        
    }

    public ArrayList<String> getAllUnassigned()
    {
        return allUnassignedFiles;
    }

    public ArrayList<String> getAllDevelopers()
    {
        
        return allDevelopers;
    }

    //fileName here is with !
    public void setDev(String fileName, String devName)
    {

        for(String s : allUnassignedFiles)
        {
            if(s.equals(fileName))
            {
                try
                {
                    //get rid of ! in bugfiledatabase.txt
                    BugDatabase bd = new BugDatabase();
                    String newFileName = fileName.replace("!", "");
                    bd.setFileName(newFileName);

                    //now read the specific bugfile contents into a stringbuffer
                    //replace the empty assigned developer with the dev name
                    //write into a new file without the "!"

                    FileReader fr = new FileReader("Bugs/" + fileName);
                    FileWriter fw  = new FileWriter("Bugs/" + newFileName);
                    Scanner input = new Scanner (fr);
                    StringBuffer buffer = new StringBuffer();

                    while (input.hasNextLine()) 
                    {
                        buffer.append(input.nextLine()+System.lineSeparator());
                    }
                    String fileContents = buffer.toString();
                    String emptyDev = "Assigned Developer : ";
                    String newDev = emptyDev + devName;
                    fileContents = fileContents.replaceAll(emptyDev, newDev);
                    fw.append(fileContents);

                    //close all the io
                    fw.close();
                    fr.close();
                    input.close();

                    //delete the old file with the "!"
                    File toDel = new File("Bugs/" + fileName);
                    toDel.delete();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
