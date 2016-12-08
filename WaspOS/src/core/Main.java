package core;

import memoryManagament.RAM;
public class Main {
	public static void main(String[] args) {
		System.out.println("Wasp OS");

		//przykladowe uzycie
		
		//stworzenie klasy
		RAM ram=new RAM();
		
		//zaladowanie danych 4 procesow	
		ram.loadDataProcess("1","proces.txt");
		ram.loadDataProcess("2","proces2.txt");
		ram.loadDataProcess("java3","proces2.txt");
		ram.loadDataProcess("java4", "proces.txt");
		//wypisanie procesow bedacych w pamieci
		ram.writeProcessesNamesInRam();

		//wypisanie przykladowych charow
		System.out.println("2 -"+ram.getCommand(0, "2"));
		System.out.println("1 -"+ram.getCommand(0, "1"));
		System.out.println("2 -"+ram.getCommand(16, "java3"));
		System.out.println("2 -"+ram.getCommand(32, "java3"));
		
		//wypisanie aktualnego stanu kolejki fifo
		ram.writeQueue();
		System.out.println("java4 -"+ram.getCommand(80, "java4"));
		//usuniecie procesu "2"
		ram.deleteProcessData("2");
		System.out.println("java4 -"+ram.getCommand(96, "java4"));
		System.out.println("java4 -"+ram.getCommand(112, "java4"));
		System.out.println("java4 -"+ram.getCommand(128, "java4"));
		System.out.println("java4 -"+ram.getCommand(144, "java4"));
		//wwypisanie calej pamieci ram, sredniki zamiast znakow nowej linii
		ram.writeRAM();
		
	}
}