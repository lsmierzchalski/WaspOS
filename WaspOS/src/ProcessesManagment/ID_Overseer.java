package ProcessesManagment;

public class ID_Overseer {
	
	//===ZMIENNE=========================================================================================
	
	private int currentID;
	
	private int copyCurrentID;

	
	//===METODY==========================================================================================
	
	public int PickID() {
		
		copyCurrentID = currentID;
		
		currentID++;
			
		return copyCurrentID;
	}
}