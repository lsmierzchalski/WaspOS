package core;
import syncMethod.*;
import ProcessesManagment.*;

public class Main {
	public static void main(String[] args) 
	{
		System.out.println("Wasp OS");
		System.out.println("Grzegorz Osak");
		System.out.println("Norbert Wo³owiec");
		
		ProcessesManagment m = new ProcessesManagment();
		m.NewProcess_XC("huj");
		m.NewProcess_XC("kurwa");
		m.NewProcess_XC("lol");
		
		Lock l = new Lock("Taki");
		
		int index=m.FindProcessWithID(1);
		int index2=m.FindProcessWithID(2);
		
		l.lock(m.processesList.get(index));
		l.lock(m.processesList.get(index2));
		
		System.out.println(m.GetNameWithID(2)+" "+ m.GetBlockedWithID(index2));
		
		l.unlock(m.processesList.get(index));
		
		System.out.println(m.GetNameWithID(2)+" "+ m.GetBlockedWithID(index2));
	}
}
