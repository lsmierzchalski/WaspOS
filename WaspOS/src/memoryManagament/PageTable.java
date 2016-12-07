package memoryManagament;

public class PageTable {
	// w jakich ramkach jest dana strona programu
	public int framesNumber[];
	// czy jest w ramie
	public boolean inRAM[];
	// nr procesu ktory jest w ramie
	String processName;

	public PageTable(int processDataSize, String procName) {
		int count;
		// obliczanie ile stron zajmuje dany program
		if (processDataSize % 16 == 0)
			count = processDataSize / 16;
		else
			count = processDataSize / 16 + 1;
		// ustawianie odpowiednich wartosci
		this.framesNumber = new int[count];
		this.inRAM = new boolean[count];
		this.processName = procName;
		// wypelnienie
		for (int i = 0; i < count; i++) {
			this.framesNumber[i] = ' ';
			this.inRAM[i] = false;
			// System.out.println(framesNumber[i] +" "+ inRAM[i]+"A");
		}

	}

	public int getPositionInRam(int pageNumber) {
		if (this.inRAM[pageNumber] == false)
			return -1;
		else
			return this.framesNumber[pageNumber];
	}
	
	public int getIndex(int pageInRam) {
		for(int i=0;i<this.framesNumber.length;i++) {
			if(this.framesNumber[i]==pageInRam)
				return i;
		}
		return -1;
	}
}
