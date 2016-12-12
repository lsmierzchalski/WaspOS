package syncMethod;

import java.util.*;
import ProcessesManagment.*;

public class Lock {
	
	public String name;
	private boolean state;
	private Proces lockingProcess;
	private Queue<Proces> queueWaitingProcesses = new LinkedList<Proces>();
	public Lock(String name){
		this.name=name;
		this.setState(false);
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public void lock(Proces processWhichCloses){
		if(!isState()){
			//zamek jest otwarty, proces zamyka zamek i rusza dalej
			setState(true);
			this.lockingProcess = processWhichCloses;}
		else{
			//zamek jest zamkniêty wiêc proces wêdruje do kolejki i ustawiany jest jego bit blocked
			queueWaitingProcesses.offer(processWhichCloses);
			processWhichCloses.SetBlocked(true);
			processWhichCloses.SetState(3);
			
		}
	}
	public void unlock(Proces processWhichOpen)
	{
		//odblokowuje zamek i jeœli kolejka nie jest pusta to zeruje bit blocked pierwszego oczekujacego procesu.
		if(processWhichOpen == this.lockingProcess)
		{
			this.setState(false);
			this.lockingProcess=null;
			if(!queueWaitingProcesses.isEmpty())
			{
				queueWaitingProcesses.peek().SetState(1);
				queueWaitingProcesses.poll().SetBlocked(false);
			}
		}
	}
	
}
