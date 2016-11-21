package ProcessesManagment;

public class Process {

	//---PCB---------------------------------------------------------------------------------------------
	
		protected int ProcessID;
		protected String ProcessName;
		protected int BaseProcessPriority;
		protected int CurrentProcessPriority;
		// do dodania DANE PROCESORA
		protected String ProgramPath;
	
	
	
	
	
	
	
	
	
	
	void NewProcess(String ProgramPath_Original){
		
		ProgramPath = ProgramPath_Original;
		
		
		
	}
	
}
