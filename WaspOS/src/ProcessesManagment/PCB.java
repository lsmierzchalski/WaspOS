<<<<<<< HEAD
package ProcessesManagment;

public class PCB {
	
	protected int ProcessID;
	protected String ProcessName;
	protected int ProcessState;
	protected int BaseProcessPriority;
	protected int CurrentProcessPriority;
	protected boolean blocked;
	// do dodania DANE PROCESORA
	// do dodania DANE Z PAMIECI
	// do dodania DANE DO KOMUNIKACJI
=======
package ProcessesManagment;

public class PCB{
	
	protected int ProcessID;
	protected String ProcessName;
	protected int whenCameToList;
	protected int ProcessState;
	protected int BaseProcessPriority;
	protected int CurrentProcessPriority;
	protected int howLongWaiting;
	protected boolean blocked;
	
	
	
	
	
	
	protected int commandCounter;
	
	public int A;
	public int B;
	public int C;
	public int D;
	public int Z;
>>>>>>> refs/remotes/origin/grutkowski
}