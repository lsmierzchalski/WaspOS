package memoryManagament;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RAM {
	private char ram[] = new char[256];
	private boolean freeFrame[] = new boolean[16];
	private String processNameInFrame[] = new String[16];
	private Queue<Integer> FIFO = new LinkedList<Integer>();
	private List<PageTable> pageTables = new ArrayList<PageTable>();
	ExchangeFile exchangeFile = new ExchangeFile();

	public RAM() {
		for (int i = 0; i < 16; i++) {
			this.freeFrame[i] = true;
			this.processNameInFrame[i] = "";
			for (int j = 0; j < 16; j++)
				this.ram[i * j] = ' ';
		}
	}

	public char getCommand(int licznikRozkazow, String processName) {
		char search;
		int pageTableIndex = -1;
		// szukanie tablicy stron z odpowiednim procesem
		for (int i = 0; i < this.pageTables.size(); i++) {
			if (this.pageTables.get(i).processName == processName)
				pageTableIndex = i;
		}

		// wyznaczenie nr strony ktora musi byc w ramie przed zwroceniem
		// odpowiedniego znaku
		int pageNumber = licznikRozkazow / 16;

		// sprawdzenie w ktorej ramce sa dane, zwraca -1 gdy nie ma w ramie
		// odpowiedniej strony
		int framePosition = pageTables.get(pageTableIndex).getPositionInRam(pageNumber);

		// zaladowanie strony do ramu jesli jej tam nie ma
		if (framePosition == -1) {
			String dane = this.exchangeFile.getFrame(processName, pageNumber);

			// szukanie wolnej ramki
			// jezeli wszystkie ramki sa zajete
			if (FIFO.size() == 16) {
				framePosition = FIFO.poll();

				for (int j = 0; j < 16; j++)
					this.ram[framePosition * 16 + j] = dane.charAt(j);

				// zaznaczenie w tablicy stron procesu którego pamiêæ zwalniamy
				// ze nie jest juz w ramie
				for (int i = 0; i < this.pageTables.size(); i++) {
					if (this.pageTables.get(i).processName == this.processNameInFrame[framePosition]) {
						int help = this.pageTables.get(i).getIndex(framePosition);
						this.pageTables.get(i).inRAM[help] = false;
					}
				}

				FIFO.add(framePosition);

			} else {
				for (int i = 0; i < 16; i++) {
					if (this.freeFrame[i] == true) {
						framePosition = i;

						for (int j = 0; j < 16; j++)
							ram[framePosition * 16 + j] = dane.charAt(j);

						this.freeFrame[framePosition] = false;
						this.processNameInFrame[framePosition] = processName;
						FIFO.add(framePosition);

						i = 16;
					}
				}
				pageTables.get(pageTableIndex).inRAM[pageNumber] = true;
				pageTables.get(pageTableIndex).framesNumber[pageNumber] = framePosition;
			}
		}

		// zwrocenie odpowiedniego znaku
		int positionInFrame = licznikRozkazow % 16;
		search = ram[framePosition * 16 + positionInFrame];
		if (search == ';')
			return '\n';
		else
			return search;
	}

	// zaladowanie nowego procesu do pamieci
	public void loadDataProcess(String processName, String fileName) {
		PageTable help = exchangeFile.loadProcessData(processName, fileName);
		this.pageTables.add(help);
	}

	// usuwanie danego procesu z pamieci
	public void deleteProcessData(String processName) {

		// usuniecie danych z pamieci ram
		for (int i = 0; i < 16; i++) {
			if (this.processNameInFrame[i].equals(processName)) {
				this.freeFrame[i] = true;
				this.FIFO.remove(i);
				for (int j = 0; j < pageTables.size(); j++) {
					if (this.pageTables.get(j).processName.equals(processName))
						this.pageTables.remove(j);
				}

				for (int j = 0; j < 16; j++)
					ram[i * 16 + j] = ' ';
			}
		}

		// usuniecie danych z pliku wymiany
		this.exchangeFile.deleteProcessData(processName);
	}

	// wypisanie procesow bedacych w pamieci
	public void writePageTables() {
		for (int i = 0; i < this.pageTables.size(); i++)
			System.out.println(pageTables.get(i).processName);
	}

	// wypisanie zawartosci ramu
	public void writeRAM() {
		for (int i = 0; i < 16; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 16; j++)
				System.out.print(ram[16 * i + j]);
			System.out.println();
		}
	}

}
