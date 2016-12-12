package shell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import fileSystem.File;

public class EpicLogo {

	public EpicLogo(){
		
	}
	
	public void displayEpicLogo() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("wasp.txt"));
		String line = null;
		while ((line = br.readLine()) != null) {
		  System.out.println(line);
		}
			
	}
	
}