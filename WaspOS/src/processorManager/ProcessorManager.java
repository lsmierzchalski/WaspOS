package processorManager;
import ProcessesManagment.Proces; 
import ProcessesManagment.ProcessesManagment;

// Pole howLongWaiting + getter/setter - GRACJAN + getter whenCameToList (co proces dodany on ma sobie to inkrementowaæ co 1)!
// + wywolanie INTERPRETERA!
// + MOJE GETTERY : Co jest akurat w RUNNING i NEXTTRY + MOZE TEKSTY GDY WYOWLUJE FUNKCJE POSZCZEGOLNE(?)!
// ? WSZYSTKO W RUN ?
// ? JAREK - ZWROCI MI ZERO TO ZNOW DZIALAM ?
// !!! PROTEZY OD JARKA I GRACJANA !!!
// !!! PROGRAMY Z PLIKU!!!
// !!! SHELL!!

/**
 * Proces bezczynnoœci, bym móg³ porównywaæ procesy z listy z polem RUNNING i NEXTTRY.
 * 
 * @author £UKASZ WOLNIAK
 */
Proces idleProcess = new Proces();
idleProcess.CreateProcess(0,idleProcess, 0);
idleProcess.SetBasePririty(0);
idleProcess.SetCurrentPririty(idleProcess.GetBasePriority());

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
 * Funkcja sprawdzaj¹ca, czy w polu RUNNING jest na pewno proces o najwy¿szym piorytecie z dostepnych tych na liscie procesow + FCFS.
 * Je¿eli na liœcie jest proces o wiêkszym piorytecie - zmieniane jest pole NEXTTRY.
 * 
 * @author £UKASZ WOLNIAK
 */
private void checkRUNNING(){
	for(Proces processFromList : processesList){
		if((processFromList.GetCurrentPriority() >= RUNNING.GetCurrentPriority()) && processFromList.GetState()==1){
			if(processFromList.GetCurrentPriority() == RUNNING.GetCurrentPriority()){
				if(processFormList.WhenCameToList() < RUNNING.processFormList.WhenCameToList()){
					RUNNING = SetCurrentPriority(GetBasePriority());
					RUNNING = processFromList;
					RUNNING = RUNNING.SetState(2);
				}
			}
			else{
				RUNNING = SetCurrentPriority(RUNNING.GetBasePriority());
				RUNNING = processFromList;
				RUNNING = RUNNING.SetState(2);
			}
		}
	}
}

/**
 * Funkcja sprawdzaj¹ca, czy w polu NEXTTRY jest na pewno kolejny proces o najwy¿szym piorytecie + FCFS.
 * Je¿eli na liœcie jest proces o wiêkszym piorytecie - sprawdzamy czy czasem te piorytet nie jest aby w polu RUNNING, zmieniane jest pole NEXTTRY.
 * 
 * @author £UKASZ WOLNIAK
 */
private void checkNEXTTRY(){
	for(Proces processFromList : processesList){
		if((processFromList.GetCurrentPriority() >= NEXTTRY.GetCurrentPriority()) && (processFromList.GetState()==1  || processFromList.GetState()==3)){
				if(processFromList.GetCurrentPriority() == NEXTTRY.GetCurrentPriority()){
					if(processFromList.WhenCameToList() < NEXTTRY.processFromList.WhenCameToList()){
						NEXTTRY = SetCurrentPriority(NEXTTRY.GetBasePriority());
						NEXTTRY = processFromList;
					}
			}
				else{
					NEXTTRY = SetCurrentPriority(NEXTTRY.GetBasePriority());
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
private void comapreNEXTTRYandRUNNING(){
	checkNEXTTRY();
	if(NEXTTRY.GetBlocked()==false){
		if(NEXTTRY.GetCurrentPriority()>= RUNNING.GetCurrentPriority()){
			if(NEXTTRY.GetCurrentPriority() == RUNNING.GetCurrentPriority()){
				if(NEXTTRY.GetWhenCameToList() < RUNNING.GetWhenCameToList()){
					RUNNING.SetCurrentPriority(RUNNING.GetBasePriority());
					RUNNING = NEXTTRY;
					RUNNING = RUNNING.SetState(2);
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
				RUNNING = RUNNING.SetState(2);
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
 * Co rozkaz zwiêkszany jest piorytet procesu o najmniejszym piorytecie z listy.
 * Wywolane zostaje rownie¿ funkcja compareNEXTTRYandWAITING.
 *
 * @author £UKASZ WOLNIAK
 */
private void checkStarving(){
	private Proces weakProcess = processesList.get(1);
	for(Proces processFromList : processesList){
			if(processFromList.GetCurrentPriority() <= weakProcess.GetCurrentPriority()){
				if(processFromList.GetCurrentPriority() == weakProcess.GetCurrentPriority()){
				if(processFromList.GetWhenCameToList > weakProcess.GetWhenCameToList()){
					weakProcess = processFromList;
				}	
			}
				else{
					weakProcess = processFromList;
				}

		}
	}
	for(Proces processFromListCheck : processesList){
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
	for(Proces processFromList : processesList){
		if(processFromList.GetState()!=2 && processFromList.GetState()!=4 && processFromList.GetState()!=4){
			processFromList.SetHowLongWaiting(processFromList.GetHowLongWaiting()+1);
		}
	}
}

/**
 * Funkcja zwiekszajaca proces glodujacemu max, postarzanie procesow.
 * Je¿eli jakiœ proces czeka co najmniej 3 rozkazy (howLongWaiting) i wszedl najpozniej na liste to jego obecny piorytet jest zwiêkszany o 1 i howLongWaiting jest zerowane.
 * Na koncu sprawdzamy pola NEXTTRY i RUNNING;
 * ZALOZENIE : 2 procesy nie moga w tej samej chwili wejsc na liste (wiec wyiberamy tlyko jeden glodujacy a nie grupe glodujacych).
 *  
 * @author £UKASZ WOLNIAK
 */
private void checkAging(){
	private Proces weakestProcess = processesList.get(1);
	for(Proces processFromList : processesList){
		if(processFromList.GetHowLongWaiting() >= 3){
			if(processFromList.GetHowLongWaiting() >= weakestProcess.GetHowLongWaiting()){
				if(processFromList.GetHowLongWaiting() == weakestProcess.GetHowLongWaiting()){
				if(processFromList.GetWhenCameToList > weakestProcess.GetWhenCameToList()){
					weakestProcess = processFromList;
				}	
			}
				else{
					weakestProcess = processFromList;
				}

		}
	}
	}
	for(Proces processFromListCheck : processesList){
		if(processFromListCheck.GetID() == weakestProcess.GetID()){
			processFromListCheck.SetCurrentPriority(processFormListCheck()+1);
		}
	}
	compareNEXTTRYandRUNNING();
}

/**
 * ZALOZENIE : Instancja klasy jest wywo³ywana po kazdym rozkazie.
 * 
 * @author £UKASZ WOLNIAK
 */
public class ProcessorManager {
		//Jezeli proces zosta³ zablokowany lub zakonczony, pod RUNNING podstawiamy proces bezczynnoœci.
		if(RUNNING.GetState()==3 || RUNNING.GetState==4){
			RUNNING.SetCurrentPriority(RUNNING.GetBasePriority()); 
			RUNNING = idleProcess;
		}
		//JEZELI PROCES NIE JEST ZABLOKOWANY, PIORYTET MA WIEKSZY, MOMENT WEJSCIA NA LISTE MNIEJSZY - WRZUCAMY GO DO RUNNING, NEXTTRY ODPOWIEDNIO ZMIENIAMY - funkcja compareRUNNINGandNEXTTRY
		compareNEXTTRYandRUNNING();
		// ROZWIAZUJEMY GLODOWANIE
		checkStarving();
		// ROZWIAZUJEMY CZEKANIE W NIESKONCZONOSC
		checkAgig();
		// MINAL ROZKAZ, WIEC ZWIEKSZAMY CZEKANKO - wywolujemy tu a nie na poczatku bo przy pierwszym wyborze wysdzloby ze czekaly rozkaz procesy
		changeWaiting();
	}