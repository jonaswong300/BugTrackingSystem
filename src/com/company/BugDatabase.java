package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class BugDatabase {

    //private String bugReporterName = "";


    //bugFileStoring, int is bugid and string is filename
    //eg. <0,Bug0000.txt> <1,Bug0001.txt>
    private final HashMap<Integer, String> fileMap = new HashMap<>();

    //holds the titles of all files
    //eg. <Bug0000.txt, title>
    private final HashMap<String, String> titleMap = new HashMap<>();

    //holds the dev of all files
    //eg. <Bug0001.txt, devName> 
    private final HashMap<String, String> devMap = new HashMap<>();

    //Holds severity level of bug
    //Eg. <Bug0001.txt, level>
    private final HashMap<String, SeverityLevel> severeMap = new HashMap<>();

    //Holds all the Bug objects
    //instead of integer, just the filename is easier to manipulate
    //eg. <Integer, Bug> <1, Bug> --> <Bugs/Bug0001.txt, Bug>
    private final HashMap<String, Bug> bugMap = new HashMap<>();

    //holds all keywords
    private final ArrayList<String> keywordsList = new ArrayList<>();

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
            String [] temp, split, keySplit, keywords, devSplit, severitySplit;
            SeverityLevel level;

            while(input.hasNextLine()){
                temp = input.nextLine().split(",");
                fileMap.put(idCounter, temp[0]);

                //TITLE
                //Edit this line if folder name changes
                String bugFileName = "Bugs/" + temp[0];
                FileReader reader = new FileReader(bugFileName);

                createBugObject(bugFileName, idCounter);

                in = new Scanner(reader);
                split = in.nextLine().split(" : ");
                titleMap.put(temp[0], split[1]);

                idCounter++;

                //KEYWORDS
                // will hold array list as eg.
                // [keyword : file1 : file2,
                //  keyword2 : file1 : file2, file3,
                //  keyword3 : file1]

                keySplit = in.nextLine().split(" :");
                keywords = keySplit[1].split(",");
                
                for(int i = 0 ; i < keywords.length; i++)
                {
                    if(!keywordsList.contains(keywords[i]))
                    {
                        keywordsList.add(keywords[i] + " : Bugs/" + temp[0]);
                    }
                    else
                    {
                        if(keywordsList.get(i).equals(keywords[i]))
                        {
                            String addFile = keywordsList.get(i) + (" : Bugs/" + temp[0]);
                            keywordsList.set(i, addFile);
                        }
                    }
                }

                //DEVELOPER
                devSplit = in.nextLine().split(" : ");
                if(temp[0].charAt(0) != '!') {
                    devMap.put(temp[0], devSplit[1]);
                }else{
                    devMap.put(temp[0], "");
                }

                severitySplit = in.nextLine().split(" : ");
                switch (severitySplit[1]) {
                    case "0":
                        level = SeverityLevel.LOW;
                        break;
                    case "1":
                        level = SeverityLevel.MEDIUM;
                        break;
                    case "2":
                        level = SeverityLevel.HIGH;
                        break;
                    case "3":
                        level = SeverityLevel.CRITICAL;
                        break;
                    default:
                        level = null;
                        break;
                }
                severeMap.put(temp[0], level);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void createBugObject(String bugFileName, int idCounter){
        String title, ID, assignDeveloper, solved;
        SeverityLevel level;
        StringBuilder description;
        ArrayList<String> keywords_AL = new ArrayList<>();
        String [] split, keywordsTemp;

        try{
            FileReader fr = new FileReader(bugFileName);
            Scanner fileInput = new Scanner(fr);

            if(fileInput.hasNextLine()){
                //Assign ID
                ID = String.valueOf(idCounter);

                //Split title
                split = fileInput.nextLine().split(":");
                title = split[1];

                //Splitting keywords
                split = fileInput.nextLine().split(":");
                keywordsTemp = split[1].split(",");
                Collections.addAll(keywords_AL, keywordsTemp);

                //Split Assigned developer
                assignDeveloper = fileInput.nextLine().split(":")[1];

                //Split severity level
                split = fileInput.nextLine().split(" : ");

                switch (split[1]) {
                    case "0":
                        level = SeverityLevel.LOW;
                        break;
                    case "1":
                        level = SeverityLevel.MEDIUM;
                        break;
                    case "2":
                        level = SeverityLevel.HIGH;
                        break;
                    case "3":
                        level = SeverityLevel.CRITICAL;
                        break;
                    default:
                        level = null;
                        break;
                }

                //Split solved
                split = fileInput.nextLine().split(":");
                solved = split[1];

                split = fileInput.nextLine().split(" : ");
                description = new StringBuilder(split[1]);
                
                while(fileInput.hasNextLine())
                {
                    description.append("\n").append(fileInput.nextLine());
                }

                bugMap.put(bugFileName, new Bug(ID, title, keywords_AL, description.toString(), assignDeveloper, level, solved));

            }else{
                System.out.println(bugFileName + "  is empty.");
            }

        }catch (IOException e){
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
                input.nextLine(); 
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
        String newID = getNewBugID();
        String fileName = "!Bug" + newID + ".txt";
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

        String commentFile = "Comment" + newID + ".txt";
        try
        {
            FileWriter fw = new FileWriter("CommentFileDatabase.txt", true);
            String toWrite = fileName + "," + commentFile + "\n";
            fw.write(toWrite);

            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setFileName(String fileName)
    {
        try
        {
            FileReader fr = new FileReader("BugFileDatabase.txt");
            Scanner input = new Scanner(fr);
            StringBuilder holdAll = new StringBuilder();
            String toChange = "";
            while(input.hasNextLine())
            {
                toChange = "!" + fileName;
                holdAll.append(input.nextLine()).append(System.lineSeparator());
                
            }
            
            String fileContents = holdAll.toString();
            fileContents = fileContents.replace(toChange, fileName);
            //System.out.println(fileContents);
            FileWriter writer = new FileWriter("BugFileDatabase.txt");
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

    //Rewrote this function to combine to initializeMap
    public HashMap<String, String> getTitleMap() 
    {
        return titleMap;
    }

    public HashMap<Integer, String> getFileMap() {
        return fileMap;
    }

    public HashMap<String, String> getDevMap()
    {
        return devMap;
    }

    public HashMap<String, Bug> getBugMap(){
        return bugMap;
    }

    public ArrayList<String> getKeywordsList()
    {
        return keywordsList;
    }

    public String returnAddBugResponse()
    {
        return "";
    }

}
 