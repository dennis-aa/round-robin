public class Process
{
    private int id;
    private int serviceTime;
    private int arrivalTime;


    private int startTime;
    private int endTime;
    private int initalServieTime;
    private int initialWaitTime;
    private int totaWaitTime;
    private int turnAroundTime;

    public Process(int id, int sTime, int aTime)
    {
        this.id = id;
        this.serviceTime = sTime;
        this.arrivalTime = aTime;
        this.initalServieTime = sTime;
    }

    public int getID()
    {
        return this.id;
    }

    public int getServiceTime()
    {
        return this.serviceTime;
    }

    public int getArrivalTime()
    {
        return this.arrivalTime;
    }
    public void service()
    {
        this.serviceTime -= 1;
    }


    public int getInitialServiceTime()
    {
    	return this.initalServieTime;
    }
    public int getStartTime()
    {
        return this.startTime;
    }
    public void setStartTime(int sTime)
    {
        this.startTime = sTime;
    }
    public int getEndTime()
    {
    	return this.endTime;
    }
    public void setEndTime(int eTime)
    {
    	this.endTime = eTime;
    }
    public int getInitialWaitTime()
    {
        return this.initialWaitTime;
    }
    public void setInitialWaitTime(int iWTime)
    {
        this. initialWaitTime = iWTime;
    }
    public int getTotalWaitTime()
    {
        return this.totaWaitTime;
    }
    public void setTotalWaitTime(int tWTime)
    {
        this.totaWaitTime = tWTime;
    }
    public int getTurnAroundTime()
    {
        return this.turnAroundTime;
    }
    public void setTurnAroundTime(int tATime)
    {
        this.turnAroundTime = tATime;
    }

}


/*
if(processQueue.peek() != null){
		if(quantaLeft > 0){
			processQueue.peek().service();
			quantaLeft--;
			System.out.println("Time remaining for Procces "+
			processQueue.peek().getID() + ": " + processQueue.peek().getServiceTime());

			if(processQueue.peek().getServiceTime() == 0){
				processQueue.remove();
			}

			if(p0.getServiceTime() > 0){
				p0.service();
				quantaLeft--;
				System.out.println(p0.getServiceTime());
			}

		}
		else{
		//TODO: Rotate servicing process
			Process replacementProcess = processQueue.peek();
			processQueue.remove();
			processQueue.add(replacementProcess);
			quantaLeft = quanta;
		}
*/


























/*
Random rand = new Random();
try{
PrintWriter FileWriter = new PrintWriter("Numbers.txt","UTF-8");
int next_int = 0;

for (int i = 0; i<10; i++) {
	next_int = rand.nextInt(5) + 4; //"+ x"where x is the minimum
	FileWriter.println("Random Number: " + next_int);
}
FileWriter.close();
}
catch (IOException e) {

}



Scanner input = new Scanner(System.in);
Random rand = new Random();

System.out.println("Enter Number of Processes: ");
int numberOfProcesses = input.nextInt();
ArrayList<Process> Processes = new ArrayList<Process>();
Queue<Process> processQueue = new LinkedList<Process>();

for (int i = 0; i < numberOfProcesses; i++) {
	Processes.add(new Process(i+1,rand.nextInt(5) + 4,rand.nextInt(5) + 4));
}

int time = 0;
while(time < 20){
	time += 1;
	System.out.println(time + " Seconds");
	}
*/