package ProcessorManager;
//import ProcessesManagment.Proces; => wrzucanie procesu do RUNNING i NEXTTRY, zwracanie danych procesu
//import ProcessesManagment.ProcessesManagment => dostep do processesList

// USTAWIÆ POCZ¥TKOWE WARTOŒCI PÓL RUNNING I NEXTTRY (!)

/**
 * Pole przechowuj¹ce aktualnie uruchomiony proces.
 * 
 * @author £UKASZ WOLNIAK
 */
Proces RUNNING; 

/**
 * Pole przechowuj¹ce kandydata na kolejny proces do uruchomienia.
 * Przed pobraniem procesu z NEXTTRY i wrzuceniu go do RUNNING, sprawdzane jest czy w liœcie procesów nie ma procesu o wy¿szym piorytecie. 
 * Je¿eli takowy jest, to on zostaje wrzucony do pola RUNNING, a pole NEXTTRY siê nie zmienia.
 * 
 * @author £UKASZ WOLNIAK
 */
Proces NEXTTRY;

/**
 * Pole przechowuj¹ce liczbe procesów wys³anych na RUNNING. 
 * Co trzy procesy (gdy pole jest modulo 3 bez reszty) zwiekszany jest o 1 obecny piorytet (currentPiority) procesu o najmniejszym piorytecie (currentPiority).
 * 
 * @author £UKASZ WOLNIAK
 */
private int sendProcessToRunningCounter = 0;

public class processorManager {

/**
 * Pêtla sprawdzaj¹ca, czy w polu NEXTTRY jest na pewno proces o najwy¿szym piorytecie.
 * Je¿eli na liœcie jest proces o wiêkszym piorytecie - zmieniane jest pole NEXTTRY.
 * 
 * @author £UKASZ WOLNIAK
 */
for(Proces processFromList : processesList){
	if(processFromList.GetCurrentPriority() > NEXTTRY.GetCurrentPriority()){
		NEXTTRY = processFromList;
	}
}

/*** ... ***/

/**
 * Instrukcja warunkowa + Pêtla rozwi¹zuj¹ca problem g³odzenia piorytetów.
 * Je¿eli zmienna sendProcessToRunningCounter jest podzielna bez reszty przez 3 (czyl i co 3 wyslane procesy), to obecny piorytet procesu o najmniejszym piorytecie jest zwiêkszany o 1.
 * 
 * @author £UKASZ WOLNIAK
 */
if(sendProcessToRunningCounter%3){
	Proces weakestProcess = processesList.get(1);
	for(Proces processFromList : processesList){
		if(processFromList.GetCurrentPriority() < weakestProcess.GetCurrentPriority()){
			weakestProcess = processFromList;
		}
	}
	weakestProcess.SetCurrentPriority(weakestProcess.GetCurrentPiority()+1);
}
}