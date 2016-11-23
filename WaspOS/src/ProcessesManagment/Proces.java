package ProcessesManagment;

public class Proces {

	//===ZMIENNE=========================================================================================
	
		private String ProgramPath;
		public PCB pcb;;
	
	//---PCB---------------------------------------------------------------------------------------------
	
		public class PCB {
		public int ProcessID;
		private String ProcessName;
		public int ProcessState;
		private int BaseProcessPriority;
		private int CurrentProcessPriority;
		private boolean blocked;
		// do dodania DANE PROCESORA
		}
	
	
	
	
	
	
	//===METODY==========================================================================================
	
	//---Stworz nowy proces------------------------------------------------------------------------------
		
	protected void CreateProcess(int ID){
			
		pcb.ProcessID = ID;
	}
		
	protected void CreateProcess(int ID,String ProgramPath_Original){
		
		pcb.ProcessID = ID;
		ProgramPath = ProgramPath_Original;
	}
	
	protected void CreateProcess(int ID,String ProgramPath_Original, String Name) {
		
		pcb.ProcessID = ID;
		ProgramPath = ProgramPath_Original;
		pcb.ProcessName = Name;
	}
	
}
