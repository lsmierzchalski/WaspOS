package processorManager;
import ProcessesManagment.Proces;
import ProcessesManagment.ProcessesManagment;
import commandInterpreter.Interpreter;
import java.util.Iterator;

public class ProcessorManager {
	// + PETLA WHILE WSPOLPRACY Z JARKIEM TELEGRAMEM WIECZORKIEM!
	// PROGRAMY Z PLIKU !!!
	// SHELL !!!
	// STATIKI CZY INSTANCJE KLASY !!!
	
	/**
	 * Proces bezczynnoœci, bym móg³ porównywaæ procesy z listy z polem RUNNING i NEXTTRY oraz do wykorzystania gdy z pola NEXTTRY proces przechodzi do RUNNING (na chwile do momentu sprawdzenia pola NEXTTRY).
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	
	private static Proces idleProcess = ProcessesManagment.NewProcess_EmptyProcess("idleProcess");
	
	/**
	 * Pole przechowuj¹ce aktualnie uruchomiony proces.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	public static Proces RUNNING = idleProcess; 
	
	/**
	 * Pole przechowuj¹ce kandydata na kolejny proces do uruchomienia.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	public static Proces NEXTTRY = idleProcess;
	
	/**
	 * Funkcja sprawdzaj¹ca, czy w polu RUNNING jest na pewno proces o najwy¿szym piorytecie z dostepnych tych na liscie procesow + zastosowanie algorytmu FCFS.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	private void checkRUNNING(){
		for(Iterator<Proces> processesFromList = ProcessesManagment.processesList.iterator(); processesFromList.hasNext();){
			Proces processFromList = processesFromList.next();
			if((processFromList.GetCurrentPriority() >= RUNNING.GetCurrentPriority()) && processFromList.GetState()==1){
				if(processFromList.GetCurrentPriority() == RUNNING.GetCurrentPriority()){
					if(processFromList.GetWhenCameToList() < RUNNING.GetWhenCameToList()){
						RUNNING.SetCurrentPriority(RUNNING.GetBasePriority());
						RUNNING = processFromList;
						RUNNING.SetState(2);
					}
				}
				else{
					RUNNING.SetCurrentPriority(RUNNING.GetBasePriority());
					RUNNING = processFromList;
					RUNNING.SetState(2);
				}
			}
		}
	}
	
	/**
	 * Funkcja sprawdzaj¹ca, czy w polu NEXTTRY jest na pewno kolejny proces o najwy¿szym piorytecie + zastosowanie algorytmu FCFS.
	 * Je¿eli na liœcie jest proces o wiêkszym piorytecie; jezeli taki proces istnieje, (lub ma taki sam piorytet ale wczesniej wszedl do listy) i nie jest ACTIVE to wstawiamy go do NEXTTRY.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	private void checkNEXTTRY(){
		for(Iterator<Proces> processesFromList = ProcessesManagment.processesList.iterator(); processesFromList.hasNext();){
			Proces processFromList = processesFromList.next();
			if((processFromList.GetCurrentPriority() >= NEXTTRY.GetCurrentPriority()) && (processFromList.GetState()==1  || processFromList.GetState()==3)){
					if(processFromList.GetCurrentPriority() == NEXTTRY.GetCurrentPriority()){
						if(processFromList.GetWhenCameToList() < NEXTTRY.GetWhenCameToList()){
							NEXTTRY.SetCurrentPriority(NEXTTRY.GetBasePriority());
							NEXTTRY = processFromList;
						}
				}
					else{
						NEXTTRY.SetCurrentPriority(NEXTTRY.GetBasePriority());
						NEXTTRY = processFromList;
					}
			}
		}
	}
	
	/**
	 *  Funkcja porównuj¹ca pola NEXTTRY i RUNNING.
	 *  Je¿eli proces w NEXTTRY : nie jest zablokowany, ma wiêkszy piorytet od procesu w RUNNING lub ma rowny piorytet, ale wczeœniej wszed³ na listê, to zamieniane jest pole RUNNING, a pole NEXTTRY odpowiednio uzupe³niane. 
	 * 	je¿eli nie, do podmiany pola RUNNING wyszukiwany jest proces z listy RUNNING.
	 * 
	 *  @author £UKASZ WOLNIAK
	 */
	private void compareNEXTTRYandRUNNING(){
		checkNEXTTRY();
		if(NEXTTRY.GetBlocked()==false){
			if(NEXTTRY.GetCurrentPriority()>= RUNNING.GetCurrentPriority()){
				if(NEXTTRY.GetCurrentPriority() == RUNNING.GetCurrentPriority()){
					if(NEXTTRY.GetWhenCameToList() < RUNNING.GetWhenCameToList()){
						RUNNING.SetCurrentPriority(RUNNING.GetBasePriority());
						RUNNING = NEXTTRY;
						RUNNING.SetState(2);
						NEXTTRY = idleProcess;
						checkNEXTTRY();
					}
					else{
						checkRUNNING();
					}
				}
				else{
					RUNNING.SetCurrentPriority(RUNNING.GetBasePriority());
					RUNNING = NEXTTRY;
					RUNNING.SetState(2);
					NEXTTRY = idleProcess;
					checkNEXTTRY();
				}
			}
			else{
				checkRUNNING();
			}
		}
		else{
			checkRUNNING();
		}
	}
	
	/**
	 * Funkcja rozwi¹zuj¹ca problem g³odzenia piorytetów.
	 * Co rozkaz zwiêkszany jest piorytet procesu o najmniejszym piorytecie z listy a jezeli sa dwa o takim samy, wybierany jest ten co wczesniej wszedl na liste.
	 * Wywolane zostaje rownie¿ funkcja compareNEXTTRYandWAITING.
	 *
	 * @author £UKASZ WOLNIAK
	 */
	private void checkStarving(){
		Proces weakProcess = ProcessesManagment.processesList.get(1);
		for(Iterator<Proces> processesFromList = ProcessesManagment.processesList.iterator(); processesFromList.hasNext();){
			Proces processFromList = processesFromList.next();
				if(processFromList.GetCurrentPriority() <= weakProcess.GetCurrentPriority()){
					if(processFromList.GetCurrentPriority() == weakProcess.GetCurrentPriority()){
					if(processFromList.GetWhenCameToList() < weakProcess.GetWhenCameToList()){
						weakProcess = processFromList;
					}	
				}
					else{
						weakProcess = processFromList;
					}
	
			}
		}
		for(Proces processFromListCheck : ProcessesManagment.processesList){
			if(processFromListCheck.GetID() == weakProcess.GetID()){
				processFromListCheck.SetCurrentPriority(processFromListCheck.GetCurrentPriority()+1);
			}
		}
		compareNEXTTRYandRUNNING();
	}
	/**
	 * Funkcja zwiêksza pole howLongWaiting co rozkaz - je¿eli proces nie jest aktywny, zwiêkszamy jego pole howLongWaiting o 1.
	 * ZALOZENIE: Nie zwiekszamy pola howLongWaiting procesu aktywnemu, zakonczonemu, ale tez (!) zablokowanemu.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	private void changeWaiting(){
		for(Proces processFromList : ProcessesManagment.processesList){
			if(processFromList.GetState()!=2 && processFromList.GetState()!=4 && processFromList.GetState()!=4){
				processFromList.SetHowLongWaiting(processFromList.GetHowLongWaiting()+1);
			}
		}
	}
	
	/**
	 * Funkcja zwiekszajaca piorytet procesu czekajacego juz co najmniej 3 rozkazy - postarzanie procesow.
	 * Je¿eli jakiœ proces czeka co najmniej 3 rozkazy (howLongWaiting) (lub jezeli czekajacych jakas liczbe jest kilka, to ten ktory wszedl na liste wczesniej) to jego obecny piorytet jest zwiêkszany o 1 i howLongWaiting jest zerowane.
	 * Na koncu sprawdzamy pola NEXTTRY i RUNNING;
	 * ZALOZENIE : 2 procesy nie moga w tej samej chwili wejsc na liste (wiec wybieramy tylko jeden glodujacy a nie grupe glodujacych).
	 *  
	 * @author £UKASZ WOLNIAK
	 */
	private void checkAging(){
		Proces weakestProcess = ProcessesManagment.processesList.get(1);
		for(Proces processFromList : ProcessesManagment.processesList){
			if(processFromList.GetHowLongWaiting() >= 3){
				if(processFromList.GetHowLongWaiting() >= weakestProcess.GetHowLongWaiting()){
					if(processFromList.GetHowLongWaiting() == weakestProcess.GetHowLongWaiting()){
					if(processFromList.GetWhenCameToList() > weakestProcess.GetWhenCameToList()){
						weakestProcess = processFromList;
					}	
				}
					else{
						weakestProcess = processFromList;
					}
	
			}
		}
		}
		for(Proces processFromListCheck : ProcessesManagment.processesList){
			if(processFromListCheck.GetID() == weakestProcess.GetID()){
				processFromListCheck.SetCurrentPriority(processFromListCheck.GetCurrentPriority()+1);
				processFromListCheck.SetHowLongWaiting(0);
			}
		}
		compareNEXTTRYandRUNNING();
	}
	
	/**
	 * Funkcja przestawiajaca aktualna zawartosc pola RUNNING.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	public void showRUNNING(){
		System.out.println("\n\nObecnie w polu RUNNING znajduje sie proces o ponizszej zawartosci: \n\n");
		System.out.println("Nazwa: ");
		System.out.println(RUNNING.GetName());
		System.out.println("ID: ");
		System.out.println(RUNNING.GetID());
		System.out.println("Numer porzadkowy: ");
		System.out.println(RUNNING.GetWhenCameToList());
		System.out.println("Piorytet bazowy: ");
		System.out.println(RUNNING.GetBasePriority());
		System.out.println("Piorytet dynamiczny: ");
		System.out.println(RUNNING.GetCurrentPriority());
		System.out.println("Stan: ");
		System.out.println(RUNNING.GetState());
		System.out.println("\n\n");
	}
	
	/**
	 * Funkcja przestawiajaca aktualna zawartosc pola NEXTTRY.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	public void showNEXTTRY(){
		System.out.println("\n\nObecnie w polu NEXTTRY znajduje sie proces o ponizszej zawartosci: \n\n");
		System.out.println("Nazwa: ");
		System.out.println(NEXTTRY.GetName());
		System.out.println("ID: ");
		System.out.println(NEXTTRY.GetID());
		System.out.println("Numer porzadkowy: ");
		System.out.println(NEXTTRY.GetWhenCameToList());
		System.out.println("Piorytet bazowy: ");
		System.out.println(NEXTTRY.GetBasePriority());
		System.out.println("Piorytet dynamiczny: ");
		System.out.println(NEXTTRY.GetCurrentPriority());
		System.out.println("Stan: ");
		System.out.println(NEXTTRY.GetState());
		System.out.println("\n\n");
	}
	
	/**
	 * ZALOZENIE : funkcja Scheuler jest wywo³ywana po kazdym rozkazie.
	 * 
	 * @author £UKASZ WOLNIAK
	 */
	public void Scheduler(){
			//Jezeli proces zosta³ zablokowany lub zakonczony, pod RUNNING podstawiamy proces bezczynnoœci.
			if(RUNNING.GetState()==3 || RUNNING.GetState()==4){
				RUNNING.SetCurrentPriority(RUNNING.GetBasePriority()); 
				RUNNING = idleProcess;
			}
			//JEZELI PROCES NIE JEST ZABLOKOWANY, PIORYTET MA WIEKSZY, MOMENT WEJSCIA NA LISTE MNIEJSZY - WRZUCAMY GO DO RUNNING, NEXTTRY ODPOWIEDNIO ZMIENIAMY - funkcja compareRUNNINGandNEXTTRY
			compareNEXTTRYandRUNNING();
			// ROZWIAZUJEMY GLODOWANIE
			checkStarving();
			// ROZWIAZUJEMY CZEKANIE W NIESKONCZONOSC
			checkAging();
			// MINAL ROZKAZ, WIEC ZWIEKSZAMY CZEKANKO - wywolujemy tu a nie na poczatku bo przy pierwszym wyborze wysdzloby ze czekaly rozkaz procesy
			changeWaiting();
			//Uruchomienie INTERPRETERA
			Interpreter.RUN(RUNNING);
	}
}