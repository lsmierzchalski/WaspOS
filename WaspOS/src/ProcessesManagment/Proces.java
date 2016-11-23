package ProcessesManagment;

public class Proces {

	//===ZMIENNE=========================================================================================
	
		private String ProgramPath;
		
		public PCB pcb = new PCB();
		
		
	//---zmienne pomocnicze------------------------------------------------------------------------------
		
		private PriorityOverseer priorityOverseer = new PriorityOverseer();
		
	//---Stany-------------------------------------------------------------------------------------------
		
		private int nowy = 0;
		private int gotowy = 1;
		
		
		
		
		
		
		
	//---PCB---------------------------------------------------------------------------------------------
		
		public class PCB {
			
			private int ProcessID;
			private String ProcessName;
			private int ProcessState;
			private int BaseProcessPriority;
			private int CurrentProcessPriority;
			private boolean blocked;
		// do dodania DANE PROCESORA
		}
	
	
	
	
	
	
	//===METODY==========================================================================================
	
	//---Stworz nowy proces------------------------------------------------------------------------------
		
	protected void CreateProcess(int ID){
			
		pcb.ProcessState = nowy;
		
		pcb.ProcessID = ID;
	
		pcb.BaseProcessPriority = priorityOverseer.RollPriority();
		
		pcb.ProcessState = gotowy;
	}
		
	protected void CreateProcess(int ID,String ProgramPath_Original){
		
		pcb.ProcessState = nowy;
		
		pcb.ProcessID = ID;
		
		ProgramPath = ProgramPath_Original;
		
		pcb.BaseProcessPriority = priorityOverseer.RollPriority();
		
		pcb.ProcessState = gotowy;
	}
	
	protected void CreateProcess(int ID,String ProgramPath_Original, String Name) {
		
		pcb.ProcessState = nowy;
		
		pcb.ProcessID = ID;
		
		ProgramPath = ProgramPath_Original;
		
		pcb.ProcessName = Name;
		
		pcb.BaseProcessPriority = priorityOverseer.RollPriority();
		
		pcb.ProcessState = gotowy;
	}
	
	//---odczytaj dane procesu----------------------------------------------------------------------------
	
	protected void ReadInformations() {
		
		System.out.println("------------------------------");
		System.out.println("ID Procesu - " + pcb.ProcessID);
		System.out.println("Stan Procesu - " + pcb.ProcessState);
		System.out.println("Pierwotny prirytet - " + pcb.BaseProcessPriority);
	}
	
	//---Id get set---------------------------------------------------------------------------------------
	
	protected int GetID() {
		
		return pcb.ProcessID;
	}
	
	
	
	
}
