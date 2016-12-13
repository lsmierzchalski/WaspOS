package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import commandInterpreter.Interpreter;
import fileSystem.FileSystem;
import memoryManagement.*;
import processesManagement.ProcessesManagment;
import processorManager.ProcessorManager;
import java.util.Map;

public class Shell {
	private RAM RAM;
	private FileSystem fileSystem;
	private ProcessesManagment processesManagment;
	private ProcessorManager processorManager;
	private Interpreter interpreter;
	
	private String string;
	
	private HashMap<String, String> allowedCommands;
	
	public Shell() throws FileNotFoundException, IOException {
		allowedCommands = new HashMap<String, String>();
		allowedCommands.put("end", "exit from system");
		allowedCommands.put("help", "exactly what you are reading");
		allowedCommands.put("credits", "\\m/");
		allowedCommands.put("logo", "draw wasp");
		
		new Processor();
		
		RAM = new RAM();
		fileSystem = new FileSystem();
		processesManagment = new ProcessesManagment();
		interpreter = new Interpreter(RAM, fileSystem, processesManagment);
		processorManager = new ProcessorManager(processesManagment, interpreter);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
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
}
