package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import commandInterpreter.Interpreter;
import fileSystem.FileSystem;
import memoryManagement.*;
import processesManagement.ProcessesManagment;
import processorManager.ProcessorManager;

public class Shell {
	private RAM RAM;
	private FileSystem fileSystem;
	private ProcessesManagment processesManagment;
	private ProcessorManager processorManager;
	private Interpreter interpreter;
	private BufferedReader in;
	
	private String string;
	
	private HashMap<String, String> allowedCommands;
	
	public Shell() throws FileNotFoundException, IOException {
		allowedCommands = new HashMap<String, String>();
		allowedCommands.put("end", "exit from system");
		allowedCommands.put("help", "exactly what you are reading");
		allowedCommands.put("credits", "\\m/");
		allowedCommands.put("logo", "draw wasp");
		allowedCommands.put("load", "load program");
		
		new Processor();
		
		RAM = new RAM();
		fileSystem = new FileSystem();
		processesManagment = new ProcessesManagment(RAM);
		interpreter = new Interpreter(RAM, fileSystem, processesManagment);
		processorManager = new ProcessorManager(processesManagment, interpreter);
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome in WaspOS 2016");
		help();
		
		do {
			string = in.readLine().trim();
			string = string.toLowerCase();
			
			if(!allowedCommands.containsKey(string)) {
				System.out.println("Undefined command!");
			}
			
			switch(string) {
			case "help": help(); break;
			case "logo": drawLogo(); break;
			case "load": load(); break;
			}
			
		} while(!string.equals("end"));
		
		in.close();
		System.out.println("Panda3!");
	}
	
	private void help() {
		System.out.println("All allowed commands:");
		Object[] tab = allowedCommands.keySet().toArray();
		for(int i = 0; i < allowedCommands.size(); i++) {
			System.out.println("->" + tab[i] + " - " + allowedCommands.get(tab[i]));
		}
	}
	
	private void drawLogo() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("wasp.logo"));
		String line = null;
		while ((line = br.readLine()) != null) {
		  System.out.println(line);
		}
		br.close();
	}
	
	private void load() throws IOException {
		System.out.println("Enter path of program.");
		switch(in.readLine()) {
		case "program1":
			processesManagment.NewProcess_forUser("program1", "program1");
			break;
		case "program2":
			processesManagment.NewProcess_forUser("program2", "program2");
			break;
		case "program3":
			processesManagment.NewProcess_forUser("program3", "program3");
			break;
		default: System.out.println("This file does not exist."); return;
		}
	}
}
