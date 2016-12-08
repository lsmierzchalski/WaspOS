package fileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import	fileSystem.File;

public class FileSystem {

	//podstawowe sta³e na których opieram dzia³eni dysku czyli jego wielkoœæ, rozmiar bloku i liczba tych ¿e bloków
	private final int DISK_SIZE = 1024;
	private final int BLOCK_SIZE = 32;
	private final int NR_OF_BLOCKS = DISK_SIZE/BLOCK_SIZE;
	
	//dysk (tablica bajtów(zanków))
	private char[] hardDrive = new char[DISK_SIZE];
		
	//wektor bitowy
	private boolean[] bitVector = new boolean[NR_OF_BLOCKS];
		
	//lista wpisów katalogowych gdzie kazdy wpis jest obiektem klasy File
	private List<File> mainCatalog = new ArrayList<File>();
	
	//konstruktor nie robi nic ciekawego poprostu wype³nia tablice zankiem # by ³atwiej by³o j¹ czytaæ
	public FileSystem(){
		for(int i=0; i < DISK_SIZE; i++) hardDrive[i] = '#';
	}
	
	//funkcja tworz¹ca plik wraz z dopisaniem do niego zawartoœci
	public void createFileWithContent(String name, String content){
		createEmptyFile(name);
		appendToFile(name, content);
	}
	
	//funkcja tworz¹ca pusty plik
	public void createEmptyFile(String name){
		
		int indexBlockIndex = findEmptyBlock();
		
		if(!checkWhetherFileWithThatNameDoesNotExist(name)){
			if(!(indexBlockIndex == -1)){
				
				//zajêcie bloku na blok indeksowy
				bitVector[indexBlockIndex] = true;
				//dodanie wpisu katologowego
				mainCatalog.add(new File(name, indexBlockIndex, 0));
				
			}   //nie wiem czy to jest wymagane ale jest potem zmienie na angielskie zdania
			else System.out.println("Nie mo¿esz utworzyæ pliku z powodu braku miejsca w pamiêci");
		}
		else System.out.println("Nie mo¿esze utworzyæ pliku o takiej nazwie");
	}
	
	//funkcja dopisuje do pliku dane
	public void appendToFile(String name, String content){
		
		int nrContentToEnter = content.length();
		int index = 0;
		String charTable = "";
		
		if(youHaveEnough(name, content.length())){
			if(checkWhetherFileWithThatNameDoesNotExist(name)){
				
				if(getNumberFreePlaceInLastBlock(name) != 0){
					System.out.println("o waæ panie ile miejsca->"+getNumberFreePlaceInLastBlock(name));
					charTable = "";
					for(int i = 0;i < getNumberFreePlaceInLastBlock(name) && index < nrContentToEnter; i++, index++){
						charTable+= content.charAt(index);
					}
					System.out.println("to siê na razie nie wykona ->"+charTable);
					appendToExistingBlock(name, charTable);
					addSizeToFile(name, charTable.length());
					//odpalic funkcje opisuj¹ca od zajêtego bloku
				}
				
				while(index != nrContentToEnter){
					charTable = "";
					
					for(int i = 0; i < BLOCK_SIZE && index < nrContentToEnter; i++,index++){
						//System.out.println(content.charAt(index)+" "+index+" "+nrContentToEnter);
						charTable+= content.charAt(index);
					}

					int i = findEmptyBlock();
					//System.out.println(name+" "+i+" "+charTable);
					addNrBlockToBlockIndex(name, i);
					appendToEmptyBlock(i, charTable);
					addSizeToFile(name, charTable.length());
					bitVector[i] = true;
					
				}
				
			}
			else System.out.println("Nie mo¿esze dopisaæ danych bo nie istnieje plik o takiej nazwie");
		}
		else System.out.println("Nie mo¿esz dopisaæ danych poniewa¿ brakuje pamiêci");
		
	}
	
	//funkcja odczytuj¹ca dane z pliku
	public String getFileContent(String name){
		
		return "ala ma kota";
	}
	
	//funkcja usuwaj¹ca plik
	public void deleteFile(String name){
		
	}
	
	//______________________PONI¯EJ_FUNKCJE_POMOCNICZE_____________________________________
	
	//funkcja sprawdza czy nie istnieje plik o takiej samej nazwie
	private boolean checkWhetherFileWithThatNameDoesNotExist(String name){
		
		boolean fileWithSameName = false;
		
		for(int i = 0; i < mainCatalog.size(); i++){
			
			if(mainCatalog.get(i).name.equals(name)){
				fileWithSameName = true;
			}
			
		}
		
		return fileWithSameName;
	}
	
	//funkcja znajduje pierszy pusty blok i zwraca jego indeks
	private int findEmptyBlock(){
		
		int indexEmptyBlock = -1;
		
		for(int i = 0; i < NR_OF_BLOCKS; i++){
			
			if(bitVector[i] == false){
				indexEmptyBlock = i;
				break;
			}
		}
		
		return indexEmptyBlock;
	}
	
	//funkcja dopisuj¹ca indeks od bloku indeksowego plik o danej nazwie
	private void addNrBlockToBlockIndex(String name, int n){
		int nrBlockIndex = getIndexBlockIndexFile(name);
		int size = getSizeFile(name);
		int nrBloksFile = size / BLOCK_SIZE;
		if(size % BLOCK_SIZE != 0) nrBloksFile++;
		//System.out.println(nrBlockIndex+" "+size+" "+nrBloksFile+" "+nrBlockIndex * BLOCK_SIZE + nrBloksFile + 1+"||||");
		hardDrive[nrBlockIndex * BLOCK_SIZE + nrBloksFile] = (char) n;
	}
	
	//funkcja dodaje rozmiar do pliku
	private void addSizeToFile(String name, int n){
		
		for(int i = 0; i < mainCatalog.size(); i++){
			if(mainCatalog.get(i).name.equals(name)){
				mainCatalog.get(i).size += n;
				break;
			}
		}
		
	}
	
	//funkcja dopisuje dane do pustego bloku
	private void appendToEmptyBlock(int index_block, String charTab){
		int j = index_block * BLOCK_SIZE;
		for(int i = 0; i < charTab.length(); i++,j++){
			hardDrive[j] = charTab.charAt(i);
		}
	}
	
	//funkcja dopisuje dane do juz istniejacego blku
	private void appendToExistingBlock(String name, String charTab){
		int lastBlock = getIndexLastDataBlock(name);
		int index = lastBlock * BLOCK_SIZE + BLOCK_SIZE - getNumberFreePlaceInLastBlock(name);
		for(int i = 0; i < charTab.length(); i++){
			hardDrive[index] = charTab.charAt(i);
			index++;
		}
	}
	
	//funkcja sprawdza czy jest wystarczaj¹co du¿o miejsca by dopisaæ dane
	private boolean youHaveEnough(String name, int spaceNeeded){//:)
		
		boolean enoughSpace = false;
		
		int freeSpace = getNumberFreePlaceInLastBlock(name) + numberOfFreeBlocks() * BLOCK_SIZE;
		
		if(freeSpace > spaceNeeded) enoughSpace = true;
		
		return enoughSpace;
	}
	
	//funkcja zwraca liczbê wolnych bloków w pamiêci
	private int numberOfFreeBlocks(){
		int numberOfFreeBlocks = 0;
		
		for(int i = 0; i < NR_OF_BLOCKS; i++){
			if(bitVector[i] == false) numberOfFreeBlocks++;
		}
		
		return numberOfFreeBlocks;
	}
	
	//fukcja zwracaj¹ca wolne miejsce w ostatnim bloku danych
	private int getNumberFreePlaceInLastBlock(String name){
		int number = 0; 
		
		int indexLastBlock = getIndexLastDataBlock(name);
		
		if(indexLastBlock != -1){
			number = 32 - (getSizeFile(name) % BLOCK_SIZE);
		}
		//System.out.println(number+" "+indexLastBlock);
		if(number == 32) number = 0;
		return number;
	}
	
	//fukcja zwracja¹ca indeks ostatniego bloku
	private int getIndexLastDataBlock(String name){
		
		int indexLastBlock = -1;
		
		int size = getSizeFile(name);
		
		if(size != 0){
			int nrOccupiedBlock = size / BLOCK_SIZE;
			if((size % BLOCK_SIZE) != 0) nrOccupiedBlock++;
			
			indexLastBlock = getIndexBlockIndexFile(name) * BLOCK_SIZE + nrOccupiedBlock;
			
			//System.out.println(nrOccupiedBlock+" "+indexLastBlock+" "+size);//mo¿e siê przydaæ
			
		}
		return indexLastBlock;
	}
	
	//fukcja zwraca obiekt typu File o danej nazwie
	private File getFile(String name){
		
		int index = -1;
		
		for(int i = 0; i < mainCatalog.size(); i++){
			if(mainCatalog.get(i).equals(name)){
				index = i;
			}
		}
		return mainCatalog.get(index);
		
	}
	
	//fukcja zwraca indeks bloku indeksowego pliku o danej nazwie
	private int getIndexBlockIndexFile(String name){
		
		int indexBlock = -1;
		
		for(int i = 0; i < mainCatalog.size(); i++){
			if(mainCatalog.get(i).name.equals(name)){
				indexBlock = mainCatalog.get(i).block_index;
			}
		}
		return indexBlock;
	}
	
	//fukcja zwraca rozmiar pliku o danej nazwie
	private int getSizeFile(String name){
			
		int size = -1;
			
		for(int i = 0; i < mainCatalog.size(); i++){
			if(mainCatalog.get(i).name.equals(name)){
				size = mainCatalog.get(i).size;
			}
		}
		return size;
	}
	
	//______________________PONI¯EJ_FUNKCJE_DO_"DIAGNOSTYKI"_MODU£U________________________
	
	//fukcja s³u¿y do wyœwietlania zawartoœci tablicy dysku twardego
	public void showHardDrive(){
		System.out.println("hardDrive");
		int x = 1;
		for(int i = 0; i < DISK_SIZE; i++){
			if(i % 32 == 0){
				System.out.print(x+" ");
				if(x<10) System.out.print(" ");
				x++;
			}
			System.out.print(hardDrive[i]);
			if((i+1) % 8 == 0) System.out.print("\t");
			if((i+1) % 32 == 0) System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	//fukcja s³u¿y do wyœwietlania zawartoœci vektora bitów który przechowuje informacje o tym czy blok jest zajêty
	public void showBitVector(){
		System.out.println("bitVector");
		int y;
		for(int i = 0; i < NR_OF_BLOCKS; i++){
			if(bitVector[i] == false) y = 0;
			else y = 1;
			System.out.print(y+" ");
			if((i+1) % 4 == 0) System.out.print("\n");
		}
		System.out.print("\n");
	}

	//fukcja s³u¿y do wyœwietlania zawartoœci tabilcy dysku twardego oraz vektora bitów
	public void showDiskAndVector(){
		System.out.println("hardDrive");
		int x = 0;
		int y;
		for(int i = 0; i < DISK_SIZE; i++){
			if(i % 32 == 0){
				System.out.print(x+"\t");
				x++;
			}
			
			if(hardDrive[i]<(char)31){
				System.out.print('$');
			}
			else{
				System.out.print(hardDrive[i]);
			}
			
			if(((i+1) % 8 == 0)&&(!((i+1) % 32 == 0))) System.out.print("  ");
			
			if((i+1)%BLOCK_SIZE == 0){
				if(bitVector[i/BLOCK_SIZE] == false) y = 0;
				else y = 1;
				System.out.print("     "+y);
			}
			
			if((i+1) % 32 == 0) System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	//fukcja s³u¿y do wyœwietlania zawartoœci g³ównego katalogu czyli trzy podstawowe informacje o plikach nawa, blok indeksowy, zormiar
	public void showMainCatalog(){
		System.out.println("mainCatalog");
		int x;
		if(mainCatalog.isEmpty()){
			System.out.println("Main Catalog is Empty");
		}
		else{
			System.out.println("NR\tNAME\tB.I\tSIZE");
			for(int i = 0; i < mainCatalog.size(); i++){
				System.out.print(i+"\t");
				System.out.print(mainCatalog.get(i).name+"\t");
				System.out.print(mainCatalog.get(i).block_index+"\t");
				System.out.print(mainCatalog.get(i).size);
				System.out.print("\n");
			}
		}
		System.out.print("\n");
	}
	
}