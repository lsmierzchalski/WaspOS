package ProcessesManagment;

public class Proces extends PCB{

	//===ZMIENNE=========================================================================================
	
		private String ProgramPath;
		
		public PCB pcb = new PCB();
		
		
	//---zmienne pomocnicze------------------------------------------------------------------------------
		
		private PriorityOverseer priorityOverseer = new PriorityOverseer();
		
		private ProcessStateOverseer stateOverseer = new ProcessStateOverseer();
		
		private int basePriority;
	
	//===METODY==========================================================================================
	
	//---Stworz nowy proces------------------------------------------------------------------------------
		
	protected void CreateProcess(int ID,String name, int number){
		
		pcb.ProcessState = stateOverseer.newbie;
		
		pcb.ProcessID = ID;
		
		pcb.ProcessName = name;
		
		basePriority = priorityOverseer.RollPriority();
		
		pcb.BaseProcessPriority = basePriority;
		
		pcb.CurrentProcessPriority = basePriority;
		
		pcb.blocked = false;
		
		pcb.A = 0;
		
		pcb.B = 0;
		
		pcb.C = 0;
		
		pcb.D = 0;
		
		pcb.Z = 0;
		
		pcb.commandCounter = 0;
		
		pcb.commandCounter = 0;
		
		pcb.whenCameToList = number;
		
		pcb.ProcessState = stateOverseer.ready;
		
		pcb.howLongWaiting = 0;
	}
	
	protected void CreateProcess(int ID,String ProgramPath_Original, String Name, int number) {
		
		pcb.ProcessState = stateOverseer.newbie;
		
		pcb.ProcessID = ID;
		
		ProgramPath = ProgramPath_Original;
		
		pcb.ProcessName = Name;
		
		basePriority = priorityOverseer.RollPriority();
		
		pcb.BaseProcessPriority = basePriority;
		
		pcb.CurrentProcessPriority = basePriority;
		
		pcb.blocked = false;
		
		pcb.A = 0;
		
		pcb.B = 0;
		
		pcb.C = 0;
		
		pcb.D = 0;
		
		pcb.Z = 0;
		
		pcb.commandCounter = 0;
		
		pcb.whenCameToList = number;
		
		pcb.ProcessState = stateOverseer.ready;
		
		pcb.howLongWaiting = 0;
	}
	
	//---odczytaj dane procesu----------------------------------------------------------------------------
	
	protected void ReadInformations() {
		
		System.out.println("------------------------------");
		System.out.println("ID Procesu - " + pcb.ProcessID);
		System.out.println("Nazwa Procesu - " + pcb.ProcessName);
		System.out.println("Ogolny numer procesu - " + pcb.whenCameToList);
		System.out.println("Stan Procesu - " + pcb.ProcessState);
		System.out.println("Pierwotny prirytet - " + pcb.BaseProcessPriority);
		System.out.println("Obecny prirytet - " + pcb.CurrentProcessPriority);
		System.out.println("Czas oczekiwania procesu - " + pcb.howLongWaiting);
		System.out.println("Stan zamka - " + pcb.blocked);
		//procesor
		System.out.println("Rejestr A - " + pcb.A);
		System.out.println("Rejestr B - " + pcb.B);
		System.out.println("Rejestr C - " + pcb.C);
		System.out.println("Rejestr D - " + pcb.D);
		System.out.println("Rejestr Z - " + pcb.Z);
		System.out.println("Liczba wykonanych rozkazow - " + pcb.commandCounter);
		
	}
	
	//-- Getery i Setery----------------------------------------------------------------------------------
	
	//---
	
	public int GetID() {
		
		return pcb.ProcessID;
	}
	
	//---
	
	public String GetName() {
		
		return pcb.ProcessName;
	}
	
	//---
	
	public int GetWhenCameToList() {
			
		return pcb.whenCameToList;
			
	}
		
	public void SetWhenCameToList(int when) {
			
		pcb.whenCameToList = when;
	}
		
	//---
		
	public int GetState() {
			
		return pcb.ProcessState;
	}
		
	public void SetState(int State) {
			
		pcb.ProcessState = State;
	}
	
	//---
	
	public int GetBasePriority() {
		
		return pcb.BaseProcessPriority;
	}
	
	public void SetBasePriority(int priority) {
		
		pcb.BaseProcessPriority = priority;
	}
	
	//---
	
	public int GetCurrentPriority() {
			
		return pcb.CurrentProcessPriority;
	}
		
	public void SetCurrentPriority(int Priority) {
			
		pcb.CurrentProcessPriority = Priority;
	}
	
	//---
		
	public int GetHowLongWaiting() {
			
		return pcb.howLongWaiting;
	}
		
	public void SetHowLongWaiting(int howLong) {
			
		pcb.howLongWaiting = howLong;
	}	
		
	//---
	
	public boolean GetBlocked() {
		
		return pcb.blocked;
	}
	
	public void SetBlocked(boolean blockedState) {
		
		pcb.blocked = blockedState;
	}
	
	//---
	
	public PCB GetPCB() {
		
		return pcb;
	}
}
