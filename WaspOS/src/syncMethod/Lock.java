package syncMethod;

import java.util.*;
import ProcessesManagment.*;

public class Lock 
{
	
	public String name;
	private boolean state;
	private Proces lockingProces;
	private Queue<Proces> queueWaitingProcesses = new LinkedList<Proces>();
	public Lock(String name)
	{
		this.name=name;
		this.setState(false);
	}
	public boolean isState() 
	{
		return state;
	}
	public void setState(boolean state) 
	{
		this.state = state;
	}
	public void displayLockingProces()
	{
		System.out.println(lockingProces.GetName());
	}
	public void lock(Proces procesWhichCloses)
	{
		if(!isState())
		{
			//zamek jest otwarty, proces zamyka zamek i rusza dalej
			setState(true);
			this.lockingProces = procesWhichCloses;
		}
		else
		{
			//zamek jest zamkniêty wiêc proces wêdruje do kolejki i ustawiany jest jego bit blocked
			queueWaitingProcesses.offer(procesWhichCloses);
			procesWhichCloses.SetBlocked(true);
			procesWhichCloses.SetState(3);
			
		}
	}
	public void unlock(Proces procesWhichOpen)
	{
		//odblokowuje zamek i jeœli kolejka nie jest pusta to zeruje bit blocked pierwszego oczekujacego procesu.
		if(procesWhichOpen == this.lockingProces)
		{
			this.setState(false);
			this.lockingProces=null;
			if(!queueWaitingProcesses.isEmpty())
			{
				queueWaitingProcesses.peek().SetState(1);
				queueWaitingProcesses.poll().SetBlocked(false);
			}
		}
	}
}
