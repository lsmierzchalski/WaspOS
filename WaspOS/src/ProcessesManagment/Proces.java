package ProcessesManagment;

public class Proces extends PCB{

	//===ZMIENNE=========================================================================================
	
		private String ProgramPath;
		
		public PCB pcb = new PCB();
		
		
	//---zmienne pomocnicze------------------------------------------------------------------------------
		
		private PriorityOverseer priorityOverseer = new PriorityOverseer();
		
	//---Stany-------------------------------------------------------------------------------------------
		
		private int nowy = 0;
		private int gotowy = 1;
		private int aktywny = 2;
		private int czekajacy = 3;
		private int zakonczony = 4;
	
	//===METODY==========================================================================================
	
	//---Stworz nowy proces------------------------------------------------------------------------------
		
	protected void CreateProcess(int ID){
			
		pcb.ProcessState = nowy;
		
		pcb.ProcessID = ID;
	
		pcb.BaseProcessPriority = priorityOverseer.RollPriority();
		
		pcb.blocked = false;
		
		pcb.ProcessState = gotowy;
	}
		
	protected void CreateProcess(int ID,String ProgramPath_Original){
		
		pcb.ProcessState = nowy;
		
		pcb.ProcessID = ID;
		
		ProgramPath = ProgramPath_Original;
		
		pcb.BaseProcessPriority = priorityOverseer.RollPriority();
		
		pcb.blocked = false;
		
		pcb.ProcessState = gotowy;
	}
	
	protected void CreateProcess(int ID,String ProgramPath_Original, String Name) {
		
		pcb.ProcessState = nowy;
		
		pcb.ProcessID = ID;
		
		ProgramPath = ProgramPath_Original;
		
		pcb.ProcessName = Name;
		
		pcb.BaseProcessPriority = priorityOverseer.RollPriority();
		
		pcb.blocked = false;
		
		pcb.ProcessState = gotowy;
	}
	
	//---odczytaj dane procesu----------------------------------------------------------------------------
	
	protected void ReadInformations() {
		
		System.out.println("------------------------------");
		System.out.println("ID Procesu - " + pcb.ProcessID);
		System.out.println("Stan Procesu - " + pcb.ProcessState);
		System.out.println("Pierwotny prirytet - " + pcb.BaseProcessPriority);
	}
	
	//---Id get -------------------------------------------------------------------------------------------
	
	protected int GetID() {
		
		return pcb.ProcessID;
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
}
