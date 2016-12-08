package fileSystem;

import java.util.Scanner;

public class TestFileSystem {
	
	FileSystem fs;
	
	public TestFileSystem(){
		fs = new FileSystem();
	}
	
	public void testFileSystem(){
		boolean run_test = true;
		
		String command;
		Scanner scanner = new Scanner(System.in);
		
		do{
			
			System.out.print("$~> ");
			command = scanner.nextLine();
			
			System.out.println(command);
			
			if (command.equals("exit")) run_test = false;
			else if (command.equals("show hd")){
				fs.showHardDrive();
			}
			else if (command.equals("show bv")){
				fs.showBitVector();
			}
			else if (command.equals("show mc")){
				fs.showMainCatalog();
			}
			else if (command.equals("show all")){
				fs.showDiskAndVector();
				fs.showMainCatalog();
			}
			else if (command.equals("ce")){
				String name;
				System.out.print("NAME: ");
				name = scanner.nextLine();
				fs.createEmptyFile(name);
			}
			else if (command.equals("cf")){
				String name, content;
				System.out.print("NAME: ");
				name = scanner.nextLine();
				System.out.print("CONTENT: ");
				content = scanner.nextLine();
				fs.createFileWithContent(name, content);
			}
			else if (command.equals("df")){
				String name;
				System.out.print("NAME: ");
				name = scanner.nextLine();
				//fs.deleteFile(name);
			}
			else if (command.equals("ap")){
				String name, content;
				System.out.print("NAME: ");
				name = scanner.nextLine();
				System.out.print("CONTENT: ");
				content = scanner.nextLine();
				fs.appendToFile(name,content);
			}
		}while(run_test);
		
		System.out.println("end test");
	}
	
}
