package core;

import ProcessesManagment.ProcessesManagment;

public class Main {
	public static void main(String[] args) {
		
		
		//test dostepnosci
		ProcessesManagment m = new ProcessesManagment();
	
		m.NewProcess();
		m.NewProcess();
		m.NewProcess();
		
		m.ReadProcessInformations(0);
		m.ReadProcessInformations(1);
		m.ReadProcessInformations(2);
		
		m.CheckStates();
		
		System.out.println(m.processesList.size());
	}
}
