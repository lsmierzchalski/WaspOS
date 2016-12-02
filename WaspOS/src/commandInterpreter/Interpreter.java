package commandInterpreter;

import java.util.ArrayList;

import core.Processor;
public class Interpreter {
	
	static int A = 5;	// Rejestr Arytmetyczny A
	static int B = -2;	// Rejestr Arytmetyczny B
	static int C;	 	// Resjestr Licznika rozkazów C
	static int D;    	// Rejestr Dowolonego użytku D

	private static int getRejestr(String rejestr) {
	   switch(rejestr) {
	      case "A": return Processor.A;
	      case "B": return Processor.B;
	      case "C": return Processor.C;
	      case "D": return Processor.D;
	   }
	   return 0;
	}

	private static int setRejestr(String rejestr, int wartosc) {
	   switch(rejestr) {
	      case "A":  Processor.A = wartosc; break;
	      case "B":  Processor.B = wartosc; break;
	      case "C":  Processor.C = wartosc; break;
	      case "D":  Processor.D = wartosc; break;
	   }
	   return 0;
	}

	private static void doCommand(String rozkaz, String rejestr, String parametr, boolean parametrJestRejestrem) {
	   switch(rozkaz) {
	   case "AD":
	      if(parametrJestRejestrem) {
	    	  // Jeśli II arg. jest rej. wykonaj dodanie zawartości rej. zawartego w II arg. do zawartości rej. w arg I.
	         setRejestr(rejestr, getRejestr(rejestr) + getRejestr(parametr));
	      } else {
	    	  // Jeśli II arg. jest rej. wykonaj dodanie wartości zawartej w II arg. do rejestru w I arg.
	         setRejestr(rejestr, getRejestr(rejestr) + Integer.parseInt(parametr));
	      }
	      break;
	   case "OD": 
		  if(parametrJestRejestrem) {
		     // Jeśli II arg. jest rej. wykonaj odejmowanie zawartości rej. zawartego w II arg. do zawartości rej. w arg I.
		     setRejestr(rejestr, getRejestr(rejestr) - getRejestr(parametr));
		  } else {
		     // Jeśli II arg. jest rej. wykonaj odejmowanie wartości zawartej w II arg. do rejestru w I arg.
		     setRejestr(rejestr, getRejestr(rejestr) - Integer.parseInt(parametr));
		  }
	      break;
	   case "MU":
		  if(parametrJestRejestrem) {
			 // Jeśli II arg. jest rej. wykonaj mnożenie zawartości rej. zawartego w II arg. do zawartości rej. w arg I.
			 setRejestr(rejestr, getRejestr(rejestr) * getRejestr(parametr));
		  } else {
			  // Jeśli II arg. jest rej. wykonaj mnożenie wartości zawartej w II arg. do rejestru w I arg.
			 setRejestr(rejestr, getRejestr(rejestr) * Integer.parseInt(parametr));
		  }
	      break;
	   
	  case "SB":
		  if(parametrJestRejestrem) {
			 // Jeśli II arg. jest rej. wykonaj mnożenie zawartości rej. zawartego w II arg. do zawartości rej. w arg I.
			 setRejestr(rejestr, getRejestr(rejestr) / getRejestr(parametr));
		  } else {
			  // Jeśli II arg. jest rej. wykonaj mnożenie wartości zawartej w II arg. do rejestru w I arg.
			 setRejestr(rejestr, getRejestr(rejestr) / Integer.parseInt(parametr));
		  }
	      break;
	   }
	}

	public static void main(String args[]) {
	   
	   ArrayList<String> rozkazy = new ArrayList<String>();
	   rozkazy.add("HLT"); // 
	   rozkazy.add("MM"); // String Register, INT address
	   rozkazy.add("MN"); // String Register, INT Number
	   rozkazy.add("XC"); // String processName, String fileName
	   rozkazy.add("XD"); // String processName
	   rozkazy.add("XR"); //  ---
	   rozkazy.add("XS"); // String processName, String communicate
	   rozkazy.add("XN"); // String processName	
	   rozkazy.add("XY"); // String processName
	   rozkazy.add("XZ"); // String processName
	   rozkazy.add("AD"); // String Register, String Register
	   rozkazy.add("SB"); // String Register, String Register
	   rozkazy.add("MU"); // String Register, String Register
	   rozkazy.add("JN"); // String Address
	   rozkazy.add("MV"); // String Register, String Register
	   rozkazy.add("JM"); // INT Address
	   
	   ArrayList<String> rejestry = new ArrayList<String>();
	   rejestry.add("A");
	   rejestry.add("B");
	   rejestry.add("C");
	   rejestry.add("D");
	   
	   String program = "AD B,A\0";

	   int stan = 0; // rozkaz = 0, rejestr = 1; param = 2;
	   
	   StringBuilder command = new StringBuilder();
	   StringBuilder rejestr = new StringBuilder();
	   StringBuilder param = new StringBuilder();
	   
	   for(Character c : program.toCharArray()) {   
	      if(c == '\0') {
	         stan = 0;
	         
	         boolean poprawnoscRejestru = rejestry.contains(rejestr.toString());
	         boolean poprawnoscRozkazu = rozkazy.contains(command.toString());
	         boolean parametrJestRejestrem = rejestry.contains(param.toString());
	         
	         System.out.println("rozkaz: " + command.toString() + " rejestr: " + rejestr.toString() + " parametr: " + param.toString());
	         System.out.println("Czy rozkaz " + command.toString() + " jest poprawny: " + poprawnoscRozkazu);
	         System.out.println("Czy rejestr " + rejestr.toString() + " jest poprawny: " + poprawnoscRejestru);
	         System.out.println("Czy parametr jest rejestrem: " + parametrJestRejestrem + "\n");
	         
	         
	         if(poprawnoscRejestru && poprawnoscRozkazu) {
	            doCommand(command.toString(), rejestr.toString(), param.toString(), parametrJestRejestrem);
	            System.out.println(A);
	            System.out.println(B);
	         }
	         
	         command.delete(0, command.length());
	         rejestr.delete(0, rejestr.length());
	         param.delete(0, param.length());
	         continue;
	      }
	      
	      if(c == ',') {
	         stan++;
	         continue;
	      }
	      
	      if(c != ' ') {
	         switch(stan) {
	         case 0: command.append(c); break;
	         case 1: rejestr.append(c); break;
	         case 2: param.append(c); break;
	         }
	      } else {
	         stan++;
	      }
	   }
	
	}
}
