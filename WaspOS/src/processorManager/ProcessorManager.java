package processorManager;
import ProcessesManagment.Proces; 
import ProcessesManagment.ProcessesManagment;

// Rejestr D jako licznik wykonanych rozkazow - DO ZROBIENIA PRZEZ JARKA!
// EMPTY - Pusty proces do wrzucenia na poczatku do RUNNING i NEXTTRY!
// + wywolanie INTERPRETERA!
// + POSTARZANIE PROCESOW gdy getCommantCounter() % 3!
// + MOJE GETTERY : Co jest akurat w RUNNING i NEXTTRY + MOZE TEKSTY GDY WYOWLUJE FUNKCJE POSZCZEGOLNE(?)!
// ? WSZYSTKO W RUN ?
// ? JAREK ZWROCI MI ZERO TO ZNOW DZIALAM ?
// !!! PROTEZY OD JARKA I GRACJANA !!!
/**
 * Pole przechowuj¹ce aktualnie uruchomiony proces.
 * 
 * @author £UKASZ WOLNIAK
 */
public static Proces RUNNING; 

/**
 * Pole przechowuj¹ce kandydata na kolejny proces do uruchomienia.
 * Przed pobraniem procesu z NEXTTRY i wrzuceniu go do RUNNING, sprawdzane jest czy w liœcie procesów nie ma procesu o wy¿szym piorytecie. 
 * Je¿eli takowy jest, to on zostaje wrzucony do pola RUNNING, a pole NEXTTRY siê nie zmienia.
 * 
 * @author £UKASZ WOLNIAK
 */
public static Proces NEXTTRY;

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
				if(processFormList.GetNumberOfAdd() < RUNNING.processFormList.GetNumberOfAdd()){
					RUNNING = processFromList;
					RUNNING = RUNNING.SetState(2);
				}
			}
			else{
			RUNNING = processFromList;
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
			if(RUNNING!=processFromList){
				if(processFromList.GetCurrentPriority() == NEXTTRY.GetCurrentPriority()){
					if(processFormList.GetNumberOfAdd() < NEXTTRY.processFormList.GetNumberOfAdd()){
						NEXTTRY = processFromList;
					}
				}
				else{
					NEXTTRY = processFromList;
				}
			}
		}
	}
}

/**
 * Funkcja rozwi¹zuj¹ca problem g³odzenia piorytetów.
 * Je¿eli zmienna sendProcessToRunningCounter jest podzielna bez reszty przez 3 (czyl i co 3 wyslane procesy), to obecny piorytet procesu o najmniejszym piorytecie jest zwiêkszany o 1.
 * Wywolane zostaje rownie¿ sprawdzenie pola najpierw RUNNING (czy czasem zwiekszony proces nie ma wiekszego piorytetu niz ten w RUNNING) oraz NEXTTRY (jezeli nie ma wiekszy od tego w RUNNING, to moze ma wiekszy chociaz od tego w NEXTTRY).
 * @author £UKASZ WOLNIAK
 */
private void checkStarving(){
		private Proces weakestProcess = processesList.get(1);
		for(Proces processFromList : processesList){
			if(processFromList.GetCurrentPriority() <= weakestProcess.GetCurrentPriority()){
				if(processFormList.GetNumberOfAdd() == weakestProcess.processFormList.GetNumberOfAdd()){
					if(processFormList.GetNumberOfAdd() < NEXTTRY.processFormList.GetNumberOfAdd()){
					weakestProcess = processFromList;
					}
			}
				else{
					weakestProcess = processFromList;
				}
		for(Proces processFromList : processesList){
			if(processFromList.GetID() == weakestProcess.GetID()){
				processFromList.SetCurrentPriority(processFromList.GetCurrentPiority()+1);
			}
		}
		checkRUNNING();
		checkNEXTTRY();
	} 
	}	
}

/**
 * 
 */
private void checkAging(){
	
}

/**
 * Instancja klasy wywo³ywana zawsze gdy proces zostanie zakoñczony lub zablokowany.
 * 
 * @author £UKASZ WOLNIAK
 */
public class ProcessorManager {
	//SPRAWDZAMY CZY PROCES W RUNNING JEST ZABLOKOWANY LUB ZAKONCZONY
	IF(RUNNING.GetState()==4 || RUNNING.GetState()==3){
		//SPRAWDZENIE CZY NA PEWNO W NEXTTRY JEST PROCES O NAJWYZCZYM PIORYTECIE
		checkNEXTTRY();
		//JEZELI PROCES NIE JEST ZABLOKOWANY TO WRZUCAMY GO DO RUNNING I WRZUCAMY NOWY DO NEXTTRY, A JEZELI NIE TO Z LISTY SZUKAMY NOWY DO RUNNINGU (BO W RUNNING SPRAWDZAMY CZY NIE JEST BLOKED)
		if(NEXTTRY.GetBlocked()==false){
			RUNNING = NEXTTRY;
			checkNEXTTRY();
		}
		else{
			checkRUNNING();
		}
		checkStarving();
	}
}