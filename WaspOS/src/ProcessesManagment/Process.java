package ProcessesManagment;

public class Process {

	//===ZMIENNE=========================================================================================
	
		protected String ProgramPath;
	
	//---PCB---------------------------------------------------------------------------------------------
	
		protected int ProcessID;
		protected String ProcessName;
		protected int BaseProcessPriority;
		protected int CurrentProcessPriority;
		// do dodania DANE PROCESORA
		
	
	
	
	
	
	
	//===METODY==========================================================================================
	
	//---Stworz nowy proces------------------------------------------------------------------------------
		
	void CreateProcess(String ProgramPath_Original){
		
		ProgramPath = ProgramPath_Original;
	}
	
	void CreateProcess(String ProgramPath_Original, String Name) {
		
		ProgramPath = ProgramPath_Original;
		ProcessName = Name;
	}
	
}
