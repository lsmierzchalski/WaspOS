package core;

import ProcessesManagment.ProcessesManagment;

public class Main {
	public static void main(String[] args) {
		
		
		//test dostepnosci
		ProcessesManagment m = new ProcessesManagment();
	
		m.NewProcess_XC("dupa");
		m.NewProcess_XC("pizda");
		m.NewProcess_XC("huj");
		
		m.GetBlockedWithID(2);
		
		

		
		
		m.ReadProcessListInformations();
		
		m.ReadProcessInformations(0);
		m.ReadProcessInformations(1);
		m.ReadProcessInformations(2);
		
		m.DeleteProcessWithID(2);
		
		m.ReadProcessListInformations();
		
		m.NewProcess_XC("nowy");
		
		m.ReadProcessListInformations();
		
		m.DeleteProcessWithName_XD("dupa");
		
		m.ReadProcessListInformations();
		
		m.NewProcess_XC("najnowszy");
		
		m.ReadProcessListInformations();
		
		m.processesList.get(1).pcb.A = 10;
		
		m.ReadProcessInformations(1);
		
	}
}
