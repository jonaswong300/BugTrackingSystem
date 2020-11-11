package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

class TriagerGenerateReportController {
    // get the closed files and compare to see which dev was assigned the most
    // closed bugs.
    // get no. of bugs reported in a month
    // get no. of bugs resolved in a week (last 7 days).

    ArrayList<String> fileList = new ArrayList<>();
    ArrayList<Date> dateList = new ArrayList<>();
    int Rnum, Dnum;

    public TriagerGenerateReportController() {
        readBugFileDatabase();
    }

    public void readBugFileDatabase() {
        BugDatabase bd = new BugDatabase();
        HashMap<String, Bug> bMap = bd.getBugMap();
        try {
            FileReader fr = new FileReader("BugFileDatabase.txt");
            Scanner in = new Scanner(fr);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(", ");
                fileList.add(line[0]);

                String date_S = line[1];
                Date date = formatter.parse(date_S);
                dateList.add(date);
                // System.out.println(formatter.format(d).toString());
            }

            fr.close();
            in.close();

        } catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
    }

    public int getBugsInMonth(int month)
    {
        int counter = 0;

        for(int i = 0; i < dateList.size(); i++)
        {
            //get month
            if(dateList.get(i).getMonth() == month)
            {
                counter++;
            }
        }

        return counter;
    }
    
    public int getResolvedInWeek()
    {
        int counter = 0;

        //get current date first to get the last 7 days
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date lastDay = cal.getTime();
        BugDatabase bd = new BugDatabase();
        
        //check for time range
        for(int i = 0; i < dateList.size(); i++)
        {
            if(!dateList.get(i).before(lastDay))
            {
                //check for resolved
                //get the bugfile
                String fileName = "Bugs/" + fileList.get(i);
                HashMap<String, Bug> bugMap = bd.getBugMap();
                for(Map.Entry me : bugMap.entrySet())
                {
                    if(me.getKey().equals(fileName))
                    {
                        Bug b = (Bug) me.getValue();
                        if(!b.isSolved().equals("open"))
                        {
                            counter++;
                        }
                    }
                }
            }
        }

        //System.out.println(counter);
        return counter;
    }

    public String getBestDeveloper()
    {
        String dev = "";
        //get all bugs
        BugDatabase bd = new BugDatabase();
        HashMap<String, Bug> bugMap = bd.getBugMap();
        HashMap<String, String> devMap = bd.getDevMap();
        ArrayList<String> allDevs = new ArrayList<>();
        ArrayList<Integer> devSolved = new ArrayList<>();

        //initialise to check all the devs later
        for(Map.Entry me : devMap.entrySet())
        {
            if(!allDevs.contains(me.getValue().toString()))
            {
                allDevs.add(me.getValue().toString());
                devSolved.add(0);
            }
        }
        
        //loop through all the bugs to check for solved status
        for(Map.Entry lel : bugMap.entrySet())
        {
            Bug k = (Bug) lel.getValue();
            //check solved status, if solved, add number.
            //im sure there's a better way to do this but my brain not working.
            if(k.isSolved().equals(" closed"))
            {
                //loop through to check which dev
                for(int i = 0; i < allDevs.size(); i++)
                {
                    //add count
                    if(k.getAssignDeveloper().trim().equals(allDevs.get(i)))
                    {
                        devSolved.set(i, (devSolved.get(i)+1));
                    }
                }
            }
        }

        String devs = "";
        int highest = devSolved.get(0);
        int index = 0;
        //check who has highest count
        for(int i = 0; i < devSolved.size(); i++)
        {
            if(highest < devSolved.get(i))
            {
                highest = devSolved.get(i);
                devs = allDevs.get(i);
                index = i;
            }
            else if (highest == devSolved.get(i) && devs != allDevs.get(i))
            {
                devs = devs + " | " + allDevs.get(i) + " | "; 
            }
        }

        
        Dnum = highest;

        return devs;
    }

    public String getBestReporter()
    {
        String rep = "";
        //get all bugs
        BugDatabase bd = new BugDatabase();
        HashMap<String, Bug> bugMap = bd.getBugMap();
        HashMap<String, String> repMap = bd.getRepMap();
        ArrayList<String> allReps = new ArrayList<>();
        ArrayList<Integer> rePorted= new ArrayList<>();

        //initialise to check all the devs later
        for(Map.Entry me : repMap.entrySet())
        {
            if(!allReps.contains(me.getValue().toString()))
            {
                allReps.add(me.getValue().toString());
                rePorted.add(0);
            }
        }
        
        //loop through all the bugs to check for solved status
        for(Map.Entry lel : bugMap.entrySet())
        {
            Bug k = (Bug) lel.getValue();
            //check solved status, if solved, add number.
            //im sure there's a better way to do this but my brain not working.
            //loop through to check which dev
            for(int i = 0; i < allReps.size(); i++)
            {
                //add count
                if(k.getReporter().trim().equals(allReps.get(i)))
                {
                    rePorted.set(i, (rePorted.get(i)+1));
                }
            }
            
        }

        String reps = "";
        int highest = rePorted.get(0);
        
        //check who has highest count
        for(int i = 0; i < rePorted.size(); i++)
        {
            if(highest < rePorted.get(i))
            {
                highest = rePorted.get(i);
                reps = allReps.get(i);
            }
            else if (highest == rePorted.get(i) && reps != allReps.get(i))
            {
                reps = reps + " | " + allReps.get(i) + " | "; 
            }
        }

        Rnum = highest;

        return reps;
    }

    public int getRepNum()
    {
        return Rnum;
    }

    public int getDevNum()
    {
        return Dnum;
    }
}
