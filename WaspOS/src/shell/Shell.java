package shell;

import java.io.IOException;
import java.util.Scanner;

import fileSystem.FileSystem;

public class Shell {
	
	FileSystem fs;
	
	public Shell(FileSystem fs){
		this.fs = fs;
	}
	
	public void go() throws IOException{
		
		//GO GO POWER RANGER !!!
		EpicLogo wasp = new EpicLogo();
		wasp.displayEpicLogo();
		
		Scanner scanner = new Scanner(System.in);
		String command = "";
		
		do{
			System.out.print("C:\\> ");
			command = scanner.nextLine();
			command = command.toUpperCase();
			
			if(command.equals("LOAD")){
				//tutaj troche zabaawy
			}
			
			//moje komendy od systemu plików
			if(command.equals("DISK")){
				/*System.out.println("1 - show hardDrive");
				System.out.println("2 - show bitVecotr");
				System.out.println("3 - show maniCatalog");
				System.out.println("4 - show all");
				System.out.println("5 - exit");
				
				int n = scanner.nextInt();
				
				switch(n){
				case	1:	fs.showHardDrive(); 	break;
				case	2:	fs.showBitVector();		break;
				case	3:	fs.showMainCatalog();	break;
				case	4:	fs.showDiskAndVector();	
							fs.showMainCatalog();	break;
				}*/
				fs.showDiskAndVector();
				fs.showMainCatalog();
			}
			
			else if(command.equals("HELP")){
				System.out.println("HELP - dispaly help");
				System.out.println("LOAF - load program ");
				//...
				System.out.println("EXIT - exit system ");
			}
			
			else if(command.equals("EXIT")){
				System.out.println("See you!");
			}
			else {
				System.out.println("This command is not supported.");
			}
				
		}while(!command.equals("EXIT"));
		
	}
	
	
	
}
