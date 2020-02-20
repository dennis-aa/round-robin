//Author - Dennis Arias
//Process Service Time will be between 2 - 5 Seconds
//InterArrival Time will be between 4 - 8 Seconds;

import java.util.*;
import java.io.*;
public class test
{
    public static final int QUANTA = 1;
    public static final int UPPER_BOUND_SERVICE_TIME = 5; //Bounds used to simulate arrival and servicing
    public static final int LOWER_BOUND_SERVICE_TIME = 2;
    public static final int UPPERBOUND_ARRIVAL_TIME = 8;
    public static final int LOWER_BOUND_ARRIVAL_TIME = 4;

    public static void main(String[] args)
    {
        //Utilities declared here
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        //Program Vraibles and data structures declared here
        int numberOfJobs = 0;
        System.out.println("Enter Number of Jobs: ");
        numberOfJobs = input.nextInt();
        ArrayList<Process> processList = new ArrayList<Process>(); // Used to hold all processes ** Might not need
        ArrayList<Process> completedList = new ArrayList<Process>(); // Used to hold completed processes
        Queue<Process> processQueue = new LinkedList<Process>(); // Maintain which processes is up next
        System.out.println("Generating Proceeses.......");
        try
        {
            Thread.sleep(1500);
        }
        catch(InterruptedException e)
        {
            System.out.println("Threading Error");
        }
        generateProcesses(processList , numberOfJobs);

        //Implementing
        int time = 0; // Initialize time to start at zero;
        int nextArrivalTime = 0;
        int quantaLeft = QUANTA;
        int jobsCompleted = 0;
        boolean middleOfQuanta = false; //Used as a utility to determine if Servicing finished while quanta was still > 0

        System.out.println("----------------------------------------------------------------------------------------------------------");

        while(jobsCompleted < numberOfJobs)
        {
            //Check for arriving processes
            if(nextArrivalTime == time)
            {
                if(processList.size() > 1)
                {
                    nextArrivalTime = processList.get(1).getArrivalTime();
                }
                processQueue.add(processList.get(0));
                System.out.println("***      Process : " + processList.get(0).getID() + " Arrived at: " + time + " Seconds     ***");
                processList.remove(0);
            }

            //Service the process using quanta as limit for one cyle
            if(processQueue.peek() != null)
            {

                if (quantaLeft == 0)
                {
                    //When quanta runs out, the current process
                    //is rotated to the back of the queue
                    Process replacementProcess = processQueue.peek();
                    if(!middleOfQuanta)
                    {
                        processQueue.remove();
                        processQueue.add(replacementProcess);
                    }
                    else
                        middleOfQuanta = false;

                    quantaLeft = QUANTA; //quanta
                    System.out.println("----------------------------------------------------------------------------------------------------------");

                }
                //Set Start Time for Process
                if(processQueue.peek().getStartTime() == 0)
                {
                    if(processQueue.peek().getID() != 0)
                    {
                        processQueue.peek().setStartTime(time);
                    }
                }

                //Service is the function that actually works on the process
                //In this case removes one second from service time

                processQueue.peek().service();
                quantaLeft--;

                System.out.println("Time remaining for Process " +
                                   processQueue.peek().getID() +
                                   " : " +
                                   processQueue.peek().getServiceTime() + " -- Current Time: " + time);
                try
                {
                    Thread.sleep(5);
                }
                catch(InterruptedException e)
                {
                    System.out.println("Threading Error");
                }
                //Remove the process once it has been completely serviced
                if(processQueue.peek().getServiceTime() == 0)
                {
                    processQueue.peek().setEndTime(time);
                    completedList.add(processQueue.peek());
                    processQueue.remove();
                    if(quantaLeft != 0)
                    {
                        middleOfQuanta = true;
                    }
                    jobsCompleted++;
                }
            }
            time += 1;

        }

        System.out.println("Running Calculations.......");
        try
        {
            Thread.sleep(1500);
        }
        catch(InterruptedException e)
        {
            System.out.println("Threading Error");
        }
        calculateStatistics(completedList);
    }
    public static void calculateStatistics(ArrayList<Process> completedList)
    {
        int totalTurnAroundTime = 0;
        double averageTurnAroundTime = 0;
        System.out.println("--------------------------------------------------------------------------------------------------------------------------\n" +
                           "| ProcessID | Service Time | Arrival Time | Start Time | End Time | Initial Wait Time | Total Wait Time | Trunaround Time |");
        for (int i = 0; i < completedList.size(); i++)
        {
            //System.out.println("----------------------------------------------------------------------------------------------------------");
            Process currentProcess = completedList.get(i);
            currentProcess.setTurnAroundTime(currentProcess.getEndTime() -
                                             currentProcess.getStartTime() + 1);
            currentProcess.setInitialWaitTime(currentProcess.getStartTime() -
                                              currentProcess.getArrivalTime());
            currentProcess.setTotalWaitTime((currentProcess.getTurnAroundTime() -
                                             currentProcess.getInitialServiceTime()) +
                                            currentProcess.getInitialWaitTime());
            if(currentProcess.getTotalWaitTime() < 0)
            {
            	currentProcess.setTotalWaitTime(0);
            }
            totalTurnAroundTime += currentProcess.getTurnAroundTime();
            System.out.print("|     " + currentProcess.getID());
            if(String.valueOf(currentProcess.getID()).length() == 1)
            {
                System.out.print("     |");
            }
            else System.out.print("    |");
            System.out.print("       " + currentProcess.getInitialServiceTime() + "     |");
            System.out.print("       " + currentProcess.getArrivalTime());
            if(String.valueOf(currentProcess.getArrivalTime()).length() == 1)
            {
                System.out.print("      |");
            }
            else if(String.valueOf(currentProcess.getArrivalTime()).length() == 3)
            {
            	System.out.print("    |");
            }
            else System.out.print("     |");

            System.out.print("      " + currentProcess.getStartTime());
            if(String.valueOf(currentProcess.getStartTime()).length() == 1)
            {
                System.out.print("     |");
            }
            else if(String.valueOf(currentProcess.getStartTime()).length() == 3)
            {
            	System.out.print("   |");
            }
            else System.out.print("    |");
            System.out.print("     " + currentProcess.getEndTime());
            if(String.valueOf(currentProcess.getEndTime()).length() == 1)
            {
                System.out.print("    |");
            }
            else if(String.valueOf(currentProcess.getEndTime()).length() == 3)
            {
            	System.out.print("  |");
            }
            else System.out.print("   |");
            System.out.print("         " + currentProcess.getInitialWaitTime() + "         |" +
            "       " + currentProcess.getTotalWaitTime() + "         |" +
            "        " + currentProcess.getTurnAroundTime() + "        |\n");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        averageTurnAroundTime = totalTurnAroundTime / completedList.size();
        System.out.println("Average Turn Around Time: " + averageTurnAroundTime);
    }
    public static void generateProcesses(ArrayList <Process> processList, int numberOfJobs)
    {
        Random rand = new Random();
        for (int i = 0; i < numberOfJobs; i++)
        {
            int nextServiceTime = rand.nextInt(UPPER_BOUND_SERVICE_TIME - LOWER_BOUND_SERVICE_TIME + 1) + LOWER_BOUND_SERVICE_TIME;
            if(i == 0)
            {
                //First Process
                processList.add(new Process(i, nextServiceTime, 0));
            }
            else
            {
                int previousArrivalTime = processList.get(i - 1).getArrivalTime();
                int nextArrivalTime = previousArrivalTime + (rand.nextInt(UPPERBOUND_ARRIVAL_TIME - LOWER_BOUND_ARRIVAL_TIME + 1) + LOWER_BOUND_ARRIVAL_TIME);
                processList.add(new Process(i, nextServiceTime, nextArrivalTime));
            }
        }
    }
    public static Process getItemByID(ArrayList <Process> processList, int iD)
    {
        for (int i = 0; i < processList.size(); i++)
        {
            if(processList.get(i).getID() == iD)
            {
                return processList.get(i);
            }
        }
        return null;
    }
}

