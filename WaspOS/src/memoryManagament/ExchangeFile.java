package memoryManagament;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExchangeFile {
	File exchange = new File("exchangefile.txt");

	public ExchangeFile() {
		try {
			if (this.exchange.exists()) {
				this.exchange.delete();
				this.exchange.createNewFile();
			} else
				this.exchange.createNewFile();
		} catch (IOException e) {
			System.out.println("Blad zapisu do pliku");
		}
	}

	// ladowanie kodu procesu do pliku wymiany
	public PageTable loadProcessData(String process_name, String fileName) {
		try {
			// odczytanie danych z pliku
			File process = new File(fileName);
			Scanner read = new Scanner(process);
			int pageIndex = 0;

			String processData = new String("");
			// wczytanie danych do stringa
			while (read.hasNextLine()) {
				processData += read.nextLine();
				processData += ';';
			}

			read.close();
			// dopelnienie zeby liczba znakow byla podzielna przez 16
			while (processData.length() % 16 != 0) {
				processData += " ";
			}

			File kopia = new File("exchangefile_copy.txt");
			PrintWriter push = new PrintWriter(kopia);

			// zapis danych do pliku wymiany
			for (int i = 0; i < processData.length() / 16; i++) {
				// System.out.println(processData.substring(pageIndex*16,
				// pageIndex*16+16));
				// zapisanie nr procesu i nr strony programu
				push.println(process_name + " " + pageIndex);

				// zapisanie strony
				push.println(processData.substring(pageIndex * 16, pageIndex * 16 + 16));
				pageIndex++;
			}

			// przepisanie tego co by³o
			Scanner scan = new Scanner(this.exchange);
			while (scan.hasNextLine()) {
				push.println(scan.nextLine());
			}
			push.close();
			scan.close();

			this.exchange.delete();
			kopia.renameTo(this.exchange);

			PageTable ret = new PageTable(processData.length(), process_name);
			return ret;

		} catch (FileNotFoundException e) {
			System.out.println("Blad: Plik nie istnieje");
		}
		PageTable ret = null;
		return ret;

	}

	public void deleteProcessData(String process_name) {
		try {
			Scanner read = new Scanner(this.exchange);
			File newfile = new File("exchangefile_copy.txt");
			PrintWriter push = new PrintWriter(newfile);

			while (read.hasNextLine()) {
				String procName = read.next();
				// System.out.print(procName+" ");
				int pageNum = read.nextInt();
				// System.out.println(pageNum);
				read.nextLine();
				String chc = read.nextLine();
				// System.out.println(chc);
				if (!process_name.equals(procName)) {
					push.println(procName + " " + pageNum);
					push.println(chc);
				}

			}
			read.close();
			push.close();
			this.exchange.delete();
			newfile.renameTo(this.exchange);
		} catch (FileNotFoundException e) {
			System.out.println("Wyj¹tek w pamiêci, nie znaleniono pliku");
		}

	}

	// dostan ramke o danym nr procesu i ramki
	public String getFrame(String processName, int pageNumber) {
		try {
			Scanner search = new Scanner(exchange);
			String check = null;
			int pageNr = -1;
			String procName = "";
			boolean odczytanoPoprawnie = false;
			while (search.hasNextLine()) {
				check = search.nextLine();
				if (check.length() != 16) {
					String podziel[] = check.split(" ");
					procName = podziel[0];
					pageNr = Integer.parseInt(podziel[1]);
					if (processName.equals(procName) && pageNumber == pageNr) {
						check = search.nextLine();
						odczytanoPoprawnie = true;
						break;
					}
				}
			}
			search.close();
			if (odczytanoPoprawnie)
				return check;
			else
				return null;

		} catch (FileNotFoundException e) {
			System.out.println("NIe ma pliku");
			return null;
		}
	}

}
