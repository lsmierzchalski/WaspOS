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
		
	protected void CreateProcess(int ID,String name){
		
		pcb.ProcessState = stateOverseer.newbie;
		
		pcb.ProcessID = ID;
		
		pcb.ProcessName = name;
		
		basePriority = priorityOverseer.RollPriority();
		
		pcb.BaseProcessPriority = basePriority;
		
		pcb.CurrentProcessPriority = basePriority;
		
		pcb.blocked = false;
		
		pcb.ProcessState = stateOverseer.ready;
	}
	
	protected void CreateProcess(int ID,String ProgramPath_Original, String Name) {
		
		pcb.ProcessState = stateOverseer.newbie;
		
		pcb.ProcessID = ID;
		
		ProgramPath = ProgramPath_Original;
		
		pcb.ProcessName = Name;
		
		basePriority = priorityOverseer.RollPriority();
		
		pcb.BaseProcessPriority = basePriority;
		
		pcb.CurrentProcessPriority = basePriority;
		
		pcb.blocked = false;
		
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
	}
	
	//---Id get -------------------------------------------------------------------------------------------
	
	protected int GetID() {
		
		return pcb.ProcessID;
	}
	
	//---name get -----------------------------------------------------------------------------------------
	
	protected String GetName() {
		
		return pcb.ProcessName;
	}
	
	//---blocked get set-----------------------------------------------------------------------------------
	
	
	protected boolean GetBlocked() {
		
		return pcb.blocked;
	}
	
	protected void SetBlocked(boolean blockedState) {
		
		pcb.blocked = blockedState;
	}
	
	//---stan procesu get i set----------------------------------------------------------------------------
	
	protected int GetState() {
		
		return pcb.ProcessState;
	}
	
	protected void SetState(int State) {
		
		pcb.ProcessState = State;
	}
	
	//---pierwotny priorytet procesu get-------------------------------------------------------------------
	
	protected int GetBasePriority() {
		
		return pcb.BaseProcessPriority;
	}
	
	//---obecny priorytet procesu get set------------------------------------------------------------------
	
	protected int GetCurrentPriority() {
		
		return pcb.CurrentProcessPriority;
	}
	
	protected void SetCurrentPriority(int Priority) {
		
		pcb.CurrentProcessPriority = Priority;
	}
	
	//---pcb get-------------------------------------------------------------------------------------------
	
	protected PCB GetPCB() {
		
		return pcb;
	}
}
