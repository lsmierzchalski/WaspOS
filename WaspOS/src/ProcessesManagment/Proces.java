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
	}
	
	//---odczytaj dane procesu----------------------------------------------------------------------------
	
	protected void ReadInformations() {
		
		System.out.println("------------------------------");
		System.out.println("ID Procesu - " + pcb.ProcessID);
		System.out.println("Nazwa Procesu - " + pcb.ProcessName);
		System.out.println("Stan Procesu - " + pcb.ProcessState);
		System.out.println("Pierwotny prirytet - " + pcb.BaseProcessPriority);
		System.out.println("Obecny prirytet - " + pcb.CurrentProcessPriority);
		System.out.println("Stan zamka - " + pcb.blocked);
		System.out.println("Rejestr A - " + pcb.A);
		System.out.println("Rejestr B - " + pcb.B);
		System.out.println("Rejestr C - " + pcb.C);
		System.out.println("Rejestr D - " + pcb.D);
		System.out.println("Rejestr Z - " + pcb.Z);
		System.out.println("Liczba wykonanych rozkazow - " + pcb.commandCounter);
		System.out.println("Ogolny numer procesu - " + pcb.whenCameToList);
	}
	
	//---Id get -------------------------------------------------------------------------------------------
	
	public int GetID() {
		
		return pcb.ProcessID;
	}
	
	//---name get -----------------------------------------------------------------------------------------
	
	public String GetName() {
		
		return pcb.ProcessName;
	}
	
	//---blocked get set-----------------------------------------------------------------------------------
	
	
	public boolean GetBlocked() {
		
		return pcb.blocked;
	}
	
	public void SetBlocked(boolean blockedState) {
		
		pcb.blocked = blockedState;
	}
	
	//---stan procesu get i set----------------------------------------------------------------------------
	
	public int GetState() {
		
		return pcb.ProcessState;
	}
	
	public void SetState(int State) {
		
		pcb.ProcessState = State;
	}
	
	//---pierwotny priorytet procesu get-------------------------------------------------------------------
	
	public int GetBasePriority() {
		
		return pcb.BaseProcessPriority;
	}
	
	//---obecny priorytet procesu get set------------------------------------------------------------------
	
	public int GetCurrentPriority() {
		
		return pcb.CurrentProcessPriority;
	}
	
	public void SetCurrentPriority(int Priority) {
		
		pcb.CurrentProcessPriority = Priority;
	}
	
	//---pcb get-------------------------------------------------------------------------------------------
	
	public PCB GetPCB() {
		
		return pcb;
	}
}
