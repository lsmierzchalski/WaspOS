package commandInterpreter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Processor;

import processorManager.*;
import fileSystem.*;
import ProcessesManagment.*;

public class Interpreter {
	
	// PCB pbccos = new PCB();
	// pcbcos = ProcesMangment.RUNNING.GetPCB();
	// pcbcos.
	//ProcesMangment.RUNNING.SetPCB(pcbcos);
/*

+ * XR -- Czytanie komunikatu 	
+ * XS -- Wysłanie komunikatu 	String processName, String communicate XS <String>,<String>
+ * XN -- Znalezienie PCB 		String processName XN <String>
+ * XY -- Uruchomienie procesu 	String processName XY <String>
+ * XC -- Utworzenie procesu 	String processName, String fileName XC <String><String>
+ * XD -- Usunięcie procesu 		String processName XD <String>
+ * XZ -- Zatrzymanie procesu 	String processName XZ <String>
  
   <> commandInterpreter <>
+ * AD -- Dodawanie  		String Register, String Register || String Register, float number AD <String>,<String> || AD <String>,<int>
+ * SB -- Odejmowanie  		String Register, String Register || String Register, float number SB <String>,<String> || SB <String>,<int>
+ * MU -- Mnożenie  		String Register, String Register || String Register, float number MU <String>,<String> || MU <String>,<int>
+ * JM -- Jump to adress 	String labelName JM <String>
+ * MV -- Move 				String Register, String Register MV <String>,<String>
+ * $: -- Etykieta JM		String labelName+ ":" ex. MyLabel:
+ * HLT -- Koniec programu	  
 
   <> Interpreter <>
+ * MN -- Move number 		String Register, INT Number   MV <String>,<INT>
 
   <> fileManger <>
+ * CF -- Create file 		String Name, String content CR <String>,<String>
+ * OF -- Open file 		String Name CR <String>,<String>
+ * DF -- Delete file 		String Name DF <String> 
+ * PO -- Print Output 		String Register

*/		
	
	static PCB PCBbox= new PCB();
	static int counter = PCBbox.commandCounter;
	static Map<String, Integer> labels = new HashMap<String, Integer>();  
	
	public static int RUN(Proces RUNNING)
	{
		counter = PCBbox.commandCounter;
		PCBbox = RUNNING.GetPCB();
		Map<String, Integer> labels;  
		
		setValue("A", PCBbox.A);
		setValue("B", PCBbox.B);
		setValue("C", PCBbox.C);
		setValue("D", PCBbox.D);
		
		RUNNING.SetPCB(PCBbox);
		return 0;
	}
	
	private static int getValue(String param1)
	{
	   switch(param1) {
	      case "A": return Processor.A;
	      case "B": return Processor.B;
	      case "C": return Processor.C;
	      case "D": return Processor.D;
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
	      case "A":  Processor.A = value; break;
	      case "B":  Processor.B = value; break;
	      case "C":  Processor.C = value; break;
	      case "D":  Processor.D = value; break;
	      //case "RUNNING.PCB.A":  RUNNING.PCB.A = value;
	      //case "RUNNING.PCB.B":  RUNNING.PCB.B = value;
	      //case "RUNNING.PCB.C":  RUNNING.PCB.C = value;
	      //case "RUNNING.PCB.D":  RUNNING.PCB.D = value;
	      //case "RUNNING.PCB.commandCounter": RUNNING.PCB.commandCounter = value;
	   }
	   return 0;
	}
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

  /*private static String getCommand(RUNNING.PCB.commandCounter, RUNNING.PCB.ProcesName)
	{
		String program ="";
		licznik = RUNNING.PCB.commandCounter;
		 	while(true)
		 	{
		 		znak = getCommand(licznik RUNNING.PCB.ProcesName);
		 		program += znak;
		 		licznik++;
		 		if(znak== '\n'){
		 			break;
		 		}		 		
		 	}
		return program;
	}*/

 	
	private static void doCommand(String rozkaz, String param1, String parametr, boolean argDrugiJestRejestrem, Map<String, Integer> labels) {
	  switch(rozkaz) {

	   case "AD":  // Dodawanie wartości
	      if(argDrugiJestRejestrem) {
	         setValue(param1, getValue(param1) + getValue(parametr));
	      } else {
	    	  setValue(param1, getValue(param1) + Integer.parseInt(parametr));
	      }
	      break;

	   case "MU": // Mnożenie wartości
		  if(argDrugiJestRejestrem) {
			 setValue(param1, getValue(param1) * getValue(parametr));
		  } else {
			 setValue(param1, getValue(param1) * Integer.parseInt(parametr));
		  }
	      break;
	   
	  case "SB": // Odejmowanie wartości
		  if(argDrugiJestRejestrem) {
			  setValue(param1, getValue(param1) - getValue(parametr));
		  } else {
			 setValue(param1, getValue(param1) - Integer.parseInt(parametr));
		  }
	      break;
	      
	  case "MV": // Umieszczenie wartości 
		  if(argDrugiJestRejestrem) {
			 setValue(param1, getValue(parametr));
		  } else {
			 setValue(param1, Integer.parseInt(parametr));
		  }
		  break;
		
	  case "MN":  // Umieszczenie wartości MN
		  if(argDrugiJestRejestrem) {
				 setValue(param1, getValue(parametr));
		  } else {
				 setValue(param1, Integer.parseInt(parametr));
		  }
		  break;
	  
	  case "JM":  // Skok do etykiety JM
		  if(labels.containsKey(param1)&& getValue("C")!=0){
			  
			  setValue("C", labels.get(param1));
			  System.out.println(getValue("C"));
		  } 
		  else 
			  {System.out.println("Error");}
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
		  // findPCB(param1)
	  break;   
	  
	  
	  case "XC":  //-- tworzenie procesu (param1);
		  //stworzProces(param1,parametr);
	  break;
	  case "XY":  //-- Uruchomienie procesu 
		  //runProces(param1);
	  break;
	  case "XD":  //-- usuwanie procesu (param1);
		  //usunProces(param1);
	  break;
	  case "XZ":  //-- Zatrzymanie procesu 
		  //stopProces(param1);
	  break;
	  
	  
	  case "CF":  //-- Create file 
		 // createFile(paramI,paramII)
	  break;
	  case "OF":  //-- Open file 
		// openFile(paramI,paramII)
	  break;
	  case "DF":  //-- Delete file 
		// deleteFile(paramI)
      break;
	  case "PO":  //-- Print Output 	
		 //printInfo(paramI)
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
		   rozkazy.add("JM"); // INT Address
		   rozkazy.add("MF");
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
		            
		            System.out.println("A: "+Processor.A);
		            System.out.println("B: "+Processor.B);
		            System.out.println("C: "+Processor.C);
		            System.out.println("D: "+Processor.D); 
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
	public static void main(String args[]) {
		String program = "AD A,B\n";
		prog(program , labels);
	}
}
