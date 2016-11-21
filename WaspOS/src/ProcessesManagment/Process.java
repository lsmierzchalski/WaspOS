package ProcessesManagment;

public class Process {

	//===ZMIENNE=========================================================================================
	
		private String ProgramPath;
	
	//---PCB---------------------------------------------------------------------------------------------
	
		public int ProcessID;
		private String ProcessName;
		private int BaseProcessPriority;
		private int CurrentProcessPriority;
		// do dodania DANE PROCESORA
		
	
	
	
	
	
	
	//===METODY==========================================================================================
	
	//---Stworz nowy proces------------------------------------------------------------------------------
		
	protected void CreateProcess(int ID){
			
		ProcessID = ID;
	}
		
	protected void CreateProcess(int ID,String ProgramPath_Original){
		
		ProcessID = ID;
		ProgramPath = ProgramPath_Original;
	}
	
	protected void CreateProcess(int ID,String ProgramPath_Original, String Name) {
		
		ProcessID = ID;
		ProgramPath = ProgramPath_Original;
		ProcessName = Name;
	}
	
}
