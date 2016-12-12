package processesCommunication;

/**
 * G³ówna klasa wymiany danych pomiêdzy procesami.
 * 
 * @author Gosak.
 */
public class Pipe {
	/**
	 * Podstawiæ tu w³aœciwy uchwyt do pliku na dysku. znakami \0
	 * bêd¹ oddzielane kolejne wiadomoœci, je¿eli plik mo¿e zawieraæ 
	 * wiele wiadomoœci.
	 */
	String falseFile;
	
	/**
	 * TODO Podstawiæ tu w³aœciwy semafor.
	 * TODO Ogarn¹æ jego wykorzystanie.
	 */
	int falseSemaphore = 0;

	public Pipe(Process a, Process b) {
		// TODO utworzenie pliku do komunikacji na dysku
		falseFile = "";
		// TODO ustawienie nazwy pliku w PCB procesów,
		a.pcb.fileName = "nazwaPliku";
		b.pcb.fileName = "nazwaPliku";
	}
	
	/**
	 * Tymczasowo plik jako string.
	 * 
	 * @return ostatnia wiadomosc w stringu.
	 */
	public String getOldestMessage() {
		//TODO blokada pliku, rozpoznawanie czy proces mo¿e czytaæ tak aby nie 
		//     odczyta³ w³asnej wiadomoœci,
		StringBuilder returned = new StringBuilder();
		char[] tab = falseFile.toCharArray();
		
		int i = 0;
		while(tab[i] != '\0') {
			returned.append(tab[i]);
			i++;
		}
		
		char[] newFileTab = falseFile.toCharArray();
		StringBuilder newFile = new StringBuilder();
		i = 0;
		while(i < falseFile.length() - returned.length()) {
			newFile.append(newFileTab[i]);
			i++;
		}
		falseFile = newFile.toString();
		//TODO odblokowanie pliku
		return returned.toString();
	}
	
		
	public void sendMessage(String msg) {
		//TODO je¿eli dostêp do pliku jest mo¿liwy to dodaj kolejn¹ wiadomoœæ
		// 	   w przeciwnym razie uœpij proces korzystaj¹cy z pliku i nadpisz
		//     oraz poinformuj o mo¿liwoœci odczytania wiadomoœci <PCB>
		falseFile += msg + '\0';
	}
}
