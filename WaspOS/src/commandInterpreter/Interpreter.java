package commandInterpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ProcessesManagment.*;
import ProcessesManagment.Proces.*;
import ProcessesManagment.PCB.*;
import memoryManagament.RAM;
import memoryManagament.RAM.*;
import core.Processor.*;
import fileSystem.FileSystem;



public class Interpreter {
	
	
	
	//ProcesMangment.RUNNING.SetPCB(pcbcos);
/*

 * XR -- Czytanie komunikatu 	
 * XS -- Wysłanie komunikatu 	String processName, String communicate XS <String>,<String>
 * XN -- Znalezienie PCB 		String processName XN <String>
 * XY -- Uruchomienie procesu 	String processName XY <String>
 * XC -- Utworzenie procesu 	String processName, String fileName XC <String><String>
 * XD -- Usunięcie procesu 		String processName XD <String>
 * XZ -- Zatrzymanie procesu 	String processName XZ <String>
  
   <> commandInterpreter <>
+ * AD -- Dodawanie  		String Register, String Register || String Register, float number AD <String>,<String> || AD <String>,<int>
+ * SB -- Odejmowanie  		String Register, String Register || String Register, float number SB <String>,<String> || SB <String>,<int>
+ * MU -- Mnożenie  		String Register, String Register || String Register, float number MU <String>,<String> || MU <String>,<int>
+ * JM -- Jump to adress 	String labelName JM <String>
+ * MV -- Move 				String Register, String Register MV <String>,<String>
+ * $: -- Etykieta JM		String labelName+ ":" ex. MyLabel:
+ * HLT -- Koniec programu	  
+ * MN -- Move number 		String Register, INT Number   MV <String>,<INT>
 
   <> fileManger <>
 * MF -- Create file 		String Name, String content CR <String>,<String>
 * WF -- Zapisanie do pliku	String Name WR <String>,<String>
 * DF -- Delete file 		String Name DF <String> 
 * PO -- Print Output 		String Register

*/	
	
	

	// Labels for JM commands is saved in:
	static Map<String, Integer> labels = new HashMap<String, Integer>();  
	
	// counter to read from memory
	static int commandCounter=0; 
	
	// other counter not matter - too many explain
	static int otherCounter=0;
	
	// Create Box for PCB
	static ProcessesManagment.PCB PCBbox= new ProcessesManagment.PCB();
	public static int RUN(ProcessesManagment.Proces RUNNING)
	{
		ProcessesManagment.PCB PCBbox= new ProcessesManagment.PCB();
		
		// Put to Box a PCB from current Process
		PCBbox = RUNNING.GetPCB();
		// Set porgram variable
		String program ="";
		
		// Copy PCB to rejetrs of Procesor
		setValue("A", PCBbox.A);
		setValue("B", PCBbox.B);
		setValue("C", PCBbox.C);
		setValue("D", PCBbox.D);
	
		labels = PCBbox.labels;
		commandCounter= PCBbox.commandCounter;
		otherCounter = PCBbox.commandCounter;
		program = getProgram(commandCounter, RUNNING.GetName());
		work(program, labels);	
				
		RUNNING.SetPCB(PCBbox);
		return 0;
	}
	
	private static int getValue(String param1)
	{
	   switch(param1) {
	      case "A": return core.Processor.A;
	      case "B": return core.Processor.B;
	      case "C": return core.Processor.C;
	      case "D": return core.Processor.D;
	      //case "RUNNING.PCB.A": return RUNNING.PCB.A;
	      //case "RUNNING.PCB.B": return RUNNING.PCB.B;
	      //case "RUNNING.PCB.C": return RUNNING.PCB.C;
	      //case "RUNNING.PCB.D": return RUNNING.PCB.D;
	      //case "RUNNING.PCB.commandCounter": return RUNNING.PCB.commandCounter;
	   }
	   return 0;
	}
	private static int setValue(String param1, int value) {
	   switch(param1) {
	      case "A":  core.Processor.A = value; break;
	      case "B":  core.Processor.B = value; break;
	      case "C":  core.Processor.C = value; break;
	      case "D":  core.Processor.D = value; break;
	      case "PCBbox.A":  Interpreter.PCBbox.A = value;
	      //case "RUNNING.PCB.B":  RUNNING.PCB.B = value;
	      //case "RUNNING.PCB.C":  RUNNING.PCB.C = value;
	      //case "RUNNING.PCB.D":  RUNNING.PCB.D = value;
	      //case "RUNNING.PCB.commandCounter": RUNNING.PCB.commandCounter = value;
	   }
	   return 0;
	}
	
	// Checking: Is command is the label?
	private static boolean isLabel(StringBuilder command)
	{
		if(command.charAt(command.length()-1)==':')
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
 	private static String getProgram(int commandCounter, String procesName)
	{
	  	char znak;
	  	String program ="";
	  	
	    while(true)
		{
	    	// Load char from RAM
		 	znak = RAM.getCommand(commandCounter, procesName);
		 	program += znak;
		 	commandCounter=commandCounter++;
		 	
		 	if(znak== '\n'){
		 		break;
		 	}		 		
		}
		return program;
	}

 	// Follow the recognized command
	private static void doCommand(String rozkaz, String param1, String param2, boolean argDrugiJestRejestrem, Map<String, Integer> labels) {
	  switch(rozkaz) {

	   case "AD":  // Dodawanie wartości
	      if(argDrugiJestRejestrem) {
	         setValue(param1, getValue(param1) + getValue(param2));
	      } else {
	    	  setValue(param1, getValue(param1) + Integer.parseInt(param2));
	      }
	      break;

	   case "MU": // Mnożenie wartości
		  if(argDrugiJestRejestrem) {
			 setValue(param1, getValue(param1) * getValue(param2));
		  } else {
			 setValue(param1, getValue(param1) * Integer.parseInt(param2));
		  }
	      break;
	   
	  case "SB": // Odejmowanie wartości
		  if(argDrugiJestRejestrem) {
			  setValue(param1, getValue(param1) - getValue(param2));
		  } else {
			 setValue(param1, getValue(param1) - Integer.parseInt(param2));
		  }
	      break;
	      
	  case "MV": // Umieszczenie wartości 
		  if(argDrugiJestRejestrem) {
			 setValue(param1, getValue(param2));
		  } else {
			 setValue(param1, Integer.parseInt(param2));
		  }
		  break;
		
	  case "MN":  // Umieszczenie wartości MN
		  if(argDrugiJestRejestrem) {
				 setValue(param1, getValue(param2));
		  } else {
				 setValue(param1, Integer.parseInt(param2));
		  }
		  break;
	  
	  case "JM":  // Skok do etykiety JM
		  if(labels.containsKey(param1)&& getValue("C")!=0){
			  
			  setValue("C", labels.get(param1));
			  System.out.println(getValue("C"));
		  } 
		  break; 
			 
	  
	  case "HLT": // Koniec programu
          System.out.println("HLT, End porgram"); 
		  System.out.println(getValue("A"));
          System.out.println(getValue("B"));
          System.out.println(getValue("C"));
          System.out.println(getValue("D")); 
          System.out.println(getValue("Z")); 
          break;
       
	  case "XR": // czytanie komunikatu;
		  // readMsg();
	  break;
	  case "XS": // -- Wysłanie komunikatu;
		  //sendMsg(paramI, paramII);
	  break;
	  case "XN": //-- znalezienie PCB (param1);
		  setValue("A", ProcessesManagment.FindProcessWithName(param1));
	  break;   
	  	  
	  case "XC":  //-- tworzenie procesu (param1);
		  ProcessesManagment.NewProcess_XC(param1)
	  break;
	  case "XY":  //-- Uruchomienie procesu 
		  
	  break;
	  case "XD":  //-- usuwanie procesu (param1);
		  ProcessesManagment.DeleteProcessWithName_XD(param1);
	  break;
	  case "XZ":  //-- Zatrzymanie procesu 
		  //stopProces(param1);
	  break;
	 	  
	  case "MF":  //-- Create file 
		  FileSystem.createFileWithContent(param1,param2);
	  break;
	  case "WF":  
		  FileSystem.appendToFile(param1,param2);
	  break;
	  case "WR": 
		  FileSystem.appendToFile(param1,getValue(param2));
	  break;
	  case "DF":  //-- Delete file 
		// deleteFile(paramI)
		  FileSystem.deleteFile(param1);
      break;
	  case "PO":  //-- Print Output 	
		  System.out.println(rejestr);
	  break;
	  }
	}
	
	public static void work(String program, Map<String, Integer> labels)
	{
		   ArrayList<String> rozkazy = new ArrayList<String>();				//  [*] USTALONE ROZKAZY [*]  //

		   // ROZKAZY ARYTMETYCZNE DWUARGUMENTOWE
		   rozkazy.add("AD");  // String Register  +  String Register/int Number
		   rozkazy.add("SB");  // String Register  -  String Register/int Number
		   rozkazy.add("MU");  // String Register  *  String Register/int Number
		   rozkazy.add("MV");  // String Register  <- String Register/int Number
		   rozkazy.add("MN"); // String Register <- INT Number
		  
		   // ROZKAZY DWUARGUMENTOWE 
		   rozkazy.add("XC"); // String processName, String fileName
		   rozkazy.add("XS"); // String processName, String communicate
		   rozkazy.add("WF"); // String FileName, String Content
		   rozkazy.add("WR"); // String FileName, String Register

		 
		   // ROZKAZY JEDNOARGUMENTOWE						
		   rozkazy.add("XD"); // String processName 
		   rozkazy.add("XN"); // String processName	
		   rozkazy.add("XY"); // String processName
		   rozkazy.add("XZ"); // String processName
		   rozkazy.add("JN"); // String Address
		   rozkazy.add("JM"); // String label
		   rozkazy.add("MF"); // String Name
		   rozkazy.add("DF"); // String name
		   rozkazy.add("PO"); 

		   // ROZKAZY BEZARGUMENTOWE
		   
		   rozkazy.add("XR"); //  Czytanie komunikatu
		   rozkazy.add("HLT"); // END PROGRAM
		   
		   ArrayList<String> rejestry = new ArrayList<String>(); 	//  [*] USTALONE REJESTRY [*]  //
		   rejestry.add("A");
		   rejestry.add("B");
		   rejestry.add("C");
		   rejestry.add("D");
		   

		   boolean manySpace = false;
		   boolean found  = false;
		   int stan = 0; // command = 0, param1 = 1; param2 = 2;
		   
		   StringBuilder command = new StringBuilder();
		   StringBuilder param1 = new StringBuilder();
		   StringBuilder param2 = new StringBuilder();
		  
		   	   	   
		   for(Character c : program.toCharArray()) {   
			  if(c == '\n') 
		      {
		         stan = 0;
		         found = false;
		         boolean rozkazToEtykieta = isLabel(command);
		         boolean poprawnoscRejestru = rejestry.contains(param1.toString());
		         boolean poprawnoscRozkazu = rozkazy.contains(command.toString());
		         boolean argDrugiJestRejestrem = rejestry.contains(param2.toString());
		        
		         System.out.println("Rozkaz/Etykieta: " + command.toString() + " parametrI: " + param1.toString() + " parametrII: " + param2.toString());
		         System.out.println("Poprawność rozkazu: " + command.toString() + " jest poprawny: " + poprawnoscRozkazu);
		         System.out.println("Czy parametrI " + param1.toString() + " jest poprawny: " + poprawnoscRejestru);
		         System.out.println("Czy parametrII " +param2.toString()+" jest rejestrem: " + argDrugiJestRejestrem + "\n");
		         
		       	 
		         if(rozkazToEtykieta)
		         {
		        	 String temp ="";
		        	 for(Character z : (command.toString()).toCharArray())
		        	 {
		        		 if(z != ':'){ temp +=z;} 
		        		 else{ labels.put(temp, getValue("C"));}	
		        	 }
		        	 
		        	 System.out.println("Etykieta: "+ temp);
	        		 System.out.println("Mapa: "+ labels);
	        		 System.out.println("D: "+getValue("D"));
	        		 
		         }else if(poprawnoscRozkazu || poprawnoscRejestru) {
		            doCommand(command.toString(), param1.toString(), param2.toString(), argDrugiJestRejestrem, labels);
		            
		            System.out.println("A: "+core.Processor.A);
		            System.out.println("B: "+core.Processor.B);
		            System.out.println("C: "+core.Processor.C);
		            System.out.println("D: "+core.Processor.D); 
		         }

		         command.delete(0, command.length());
		         param1.delete(0, param1.length());
		         param2.delete(0, param2.length());	         
		         continue;
		      } 
		      
		      if(c == ',')
		      { 
		    	stan++; 
		    	found = true;
		        manySpace = true;
			    continue;
			  }
		      
		      if(c==' ')
		      {	  if(stan==1 && !found){continue;}
		    	  if(manySpace){continue;}
		    	  else 
		    	  { 
		    		  stan++; 
		    		  manySpace=true;
		    	  }
		      }
		      
		      if(c != ' ')
		      {
		    	manySpace = false;
			    switch(stan) {
			    	case 0: command.append(c); break;
			    	case 1: param1.append(c); break;
			    	case 2: param2.append(c); break;
			    }
		      }
		   }
		}
}
