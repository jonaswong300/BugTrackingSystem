package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class TriagerSetBugSeverityController {

    private final ArrayList<String> allBugFileList = new ArrayList<>();
    //private final ArrayList<String> bugFileWithNoLevelList = new ArrayList<>();

    public TriagerSetBugSeverityController(){
        initializeMap();
    }

    public void initializeMap(){
        BugFileAccess bd = new BugFileAccess();
        HashMap<String, SeverityLevel> severeMap = bd.getSevereMap();

        allBugFileList.addAll(severeMap.keySet());
        //System.out.println( allBugFileList.size() == bd.getSevereMap().size() ? "True" : "False");
    }

    public ArrayList<String> getAllBugFileList(){
        return allBugFileList;
    }

    public void setSeverityLevel(String fileName, String severityLevel){

        BugFileAccess bd = new BugFileAccess();
        HashMap<String, SeverityLevel> severeMap = bd.getSevereMap();

        for(String s : allBugFileList){
            if(s.equals(fileName)){
                try{
                    FileReader fr = new FileReader("Bugs/" + fileName);
                    Scanner input = new Scanner(fr);

                    StringBuilder builder = new StringBuilder();

                    while(input.hasNextLine()){
                        builder.append(input.nextLine()).append(System.lineSeparator());
                    }

                    String fileContents = builder.toString();
                    String currentLevel = "Severity Level : ";
                    String newLevel = currentLevel + severityLevel;

                    if(severeMap.containsKey(fileName)){
                        switch(severeMap.get(fileName)){
                            case LOW:
                                currentLevel += "0";
                                break;
                            case MEDIUM:
                                currentLevel += "1";
                                break;
                            case HIGH:
                                currentLevel += "2";
                                break;
                            case CRITICAL:
                                currentLevel += "3";
                                break;
                        }
                    }

                    //System.out.println(currentLevel);
                    //System.out.println(newLevel);
                    //System.out.println(fileContents);

                    fileContents = fileContents.replaceAll(currentLevel, newLevel);

                    FileWriter fw = new FileWriter("Bugs/" + fileName);
                    fw.write(fileContents);

                    fr.close();
                    fw.close();


                }catch (IOException e){
                    e.printStackTrace();
                }

                break;
            }
        }
    }
}
