package core;

import ProcessesManagment.ProcessesManagment;

public class Main {
	public static void main(String[] args) {
		
		
		//test dostepnosci
		ProcessesManagment m = new ProcessesManagment();
	
		m.NewProcess_XC("dupa");
		m.NewProcess_XC("pizda");
		m.NewProcess_XC("huj");
		
		m.ReadProcessListInformations();
		
		m.ReadProcessInformationsWithID(0);
		m.ReadProcessInformationsWithID(1);
		m.ReadProcessInformationsWithID(2);
		
		m.DeleteProcessWithID(2);
		
		m.ReadProcessListInformations();
		
		m.NewProcess_XC("dupa");
		
		m.ReadProcessListInformations();
		
		m.ReadProcessInformationsWithID(3);
		/*
		m.DeleteProcessWithName_XD("dupa");
		
		m.ReadProcessListInformations();
		
		m.NewProcess_XC("najnowszy");
		
		m.ReadProcessListInformations();
		
		m.processesList.get(1).pcb.A = 10;
		
		m.ReadProcessInformations(1);
		*/
	}
}
