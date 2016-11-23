package syncMethod;

import java.util.*;

public class Lock {
	public String name;
	private boolean state;
	private TestPCB lockingProcess;
	private Queue<TestPCB> queueWaitingProcesses = new LinkedList<TestPCB>();
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
	public void lock(TestPCB processWhichCloses){
		if(!isState()){
			//zamek jest otwarty, proces zamyka zamek i rusza dalej
			setState(true);
			this.lockingProcess = processWhichCloses;}
		else{
			//zamek jest zamkniêty wiêc proces wêdruje do kolejki i ustawiany jest jego bit blocked
			queueWaitingProcesses.offer(processWhichCloses);
			processWhichCloses.blocked= true;
		}
	}
	public void unlock(TestPCB processWhichOpen){
		//odblokowuje zamek i jeœli kolejka nie jest pusta to zeruje bit blocked pierwszego oczekujacego procesu.
		if(processWhichOpen == this.lockingProcess){
			this.setState(false);
			this.lockingProcess=null;
			if(!queueWaitingProcesses.isEmpty()){
				queueWaitingProcesses.poll().blocked=false;
			}
		}
	}
}
