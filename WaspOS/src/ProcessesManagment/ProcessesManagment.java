package ProcessesManagment;
import java.lang.Object;
import java.util.LinkedList;
import java.util.List;

public class ProcessesManagment extends Process {

	//===ZMIENNE=========================================================================================
	
	private List<Process> ProcessesList;
	
	//===METODY==========================================================================================
	
	void Start(){
		
		ProcessesList = new LinkedList<Process>();
	}
	
	//---Dodaj nowy proces--------------------------------------------------------------------------------
	
	void NewProcess(String ProgramPath_Original){
		
		Process process = new Process();
		process.CreateProcess(ProgramPath_Original);
	
		ProcessesList.add(process);
	}
	
	void NewProcess(String ProgramPath_Original, String Name) {
		
		Process process = new Process();
		process.CreateProcess(ProgramPath_Original, Name);;
	
		ProcessesList.add(process);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
