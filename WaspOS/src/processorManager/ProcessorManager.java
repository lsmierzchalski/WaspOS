package processorManager;
import ProcessesManagment.Proces; 
import ProcessesManagment.ProcessesManagment
// CO USTAWIÆ JAKO POCZ¥TKOWA WARTOSC POLA RUNNING (!), BO W NEXTTRY MOZE BYC OBOJETNIE JAKI PROCES BO I TAK SPRAWDZAM NA POCZATKU CZY W NEXTTRY JEST NAJWIEKSZY NA BANK
/**
 * Pole przechowuj¹ce aktualnie uruchomiony proces.
 * 
 * @author £UKASZ WOLNIAK
 */
public Proces RUNNING; 

/**
 * Pole przechowuj¹ce kandydata na kolejny proces do uruchomienia.
 * Przed pobraniem procesu z NEXTTRY i wrzuceniu go do RUNNING, sprawdzane jest czy w liœcie procesów nie ma procesu o wy¿szym piorytecie. 
 * Je¿eli takowy jest, to on zostaje wrzucony do pola RUNNING, a pole NEXTTRY siê nie zmienia.
 * 
 * @author £UKASZ WOLNIAK
 */
public Proces NEXTTRY;

/**
 * Pole przechowuj¹ce liczbe procesów wys³anych na RUNNING. 
 * Co trzy procesy (gdy pole jest modulo 3 bez reszty) zwiekszany jest o 1 obecny piorytet (currentPiority) procesu o najmniejszym piorytecie (currentPiority).
 * 
 * @author £UKASZ WOLNIAK
 */
private int sendProcessToRunningCounter = 0;

/**
 * Funkcja sprawdzaj¹ca, czy w polu RUNNING jest na pewno proces o najwy¿szym piorytecie z dostepnych tych na liscie procesow.
 * Je¿eli na liœcie jest proces o wiêkszym piorytecie - zmieniane jest pole NEXTTRY.
 * 
 * @author £UKASZ WOLNIAK
 */
private void checkRUNNING(){
	for(Proces processFromList : processesList){
		if((processFromList.GetCurrentPriority() > RUNNING.GetCurrentPriority()) && processFromList.GetBlocked()==false && rocessFromList.GetState()!=4){
			RUNNING = processFromList;
		}
	}
}

/**
 * Funkcja sprawdzaj¹ca, czy w polu NEXTTRY jest na pewno kolejny proces o najwy¿szym piorytecie.
 * Je¿eli na liœcie jest proces o wiêkszym piorytecie - sprawdzamy czy czasem te piorytet nie jest aby w polu RUNNING, zmieniane jest pole NEXTTRY.
 * 
 * @author £UKASZ WOLNIAK
 */
private void checkNEXTTRY(){
	for(Proces processFromList : processesList){
		if((processFromList.GetCurrentPriority() > NEXTTRY.GetCurrentPriority()) && processFromList.GetState()!=4){
			if(RUNNING!=processFromList){
				
				NEXTTRY = processFromList;
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
	if(sendProcessToRunningCounter%3){
		private Proces weakestProcess = processesList.get(1);
		for(Proces processFromList : processesList){
			if(processFromList.GetCurrentPriority() < weakestProcess.GetCurrentPriority()){
				weakestProcess = processFromList;
			}
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

/**
 * Instancja klasy wywo³ywana zawsze gdy proces zostanie zakoñczony lub zablokowany.
 * 
 * @author £UKASZ WOLNIAK
 */
public class ProcessorManager {
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