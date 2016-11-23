package ProcessesManagment;
import java.lang.Object;
import java.util.LinkedList;
import java.util.List;

public class ProcessesManagment extends Proces {

	//===ZMIENNE=========================================================================================
	
	public List<Proces> ProcessesList;
	
	private ID_Overseer overseer;
	
	//===METODY==========================================================================================
	
	//---Konstruktor-------------------------------------------------------------------------------------
	
	public ProcessesManagment(){
		
		ProcessesList = new LinkedList<Proces>();
		overseer = new ID_Overseer();
	}
	
	//---Dodaj nowy proces--------------------------------------------------------------------------------

	public void NewProcess(){
		
		Proces process = new Proces();
		process.CreateProcess(overseer.PickID());
	
		ProcessesList.add(process);
	}

	public void NewProcess(String ProgramPath_Original){
		
		Proces process = new Proces();
		process.CreateProcess(overseer.PickID(),ProgramPath_Original);
	
		ProcessesList.add(process);
	}
	
	public void NewProcess(String ProgramPath_Original, String Name) {
		
		Proces process = new Proces();
		process.CreateProcess(overseer.PickID(),ProgramPath_Original, Name);;
	
		ProcessesList.add(process);
	}
	
	//---odczytaj dane procesu----------------------------------------------------------------------------
	
	public void ReadProcessInformations(int ID) {
		
		// do poprawienia zeby szukalo po id nie indeksie w liscie
		Proces p1 = ProcessesList.get(ID);
		p1.ReadInformations();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
