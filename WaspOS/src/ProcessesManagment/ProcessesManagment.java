package ProcessesManagment;
import java.lang.Object;
import java.util.LinkedList;
import java.util.List;

public class ProcessesManagment extends Process {

	//===ZMIENNE=========================================================================================
	
	public List<Process> ProcessesList;
	
	private ID_Overseer overseer;
	
	//===METODY==========================================================================================
	
	//---Konstruktor-------------------------------------------------------------------------------------
	
	public ProcessesManagment(){
		
		ProcessesList = new LinkedList<Process>();
		overseer = new ID_Overseer();
	}
	
	//---Dodaj nowy proces--------------------------------------------------------------------------------
	
	public void NewProcess(){
		
		Process process = new Process();
		process.CreateProcess(overseer.PickID());
	
		ProcessesList.add(process);
	}

	public void NewProcess(String ProgramPath_Original){
		
		Process process = new Process();
		process.CreateProcess(overseer.PickID(),ProgramPath_Original);
	
		ProcessesList.add(process);
	}
	
	public void NewProcess(String ProgramPath_Original, String Name) {
		
		Process process = new Process();
		process.CreateProcess(overseer.PickID(),ProgramPath_Original, Name);;
	
		ProcessesList.add(process);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
