package ProcessorManager;
//import ProcessesManagment.Proces; => wrzucanie procesu do RUNNING i NEXTTRY, zwracanie danych procesu
//import ProcessesManagment.ProcessesManagment => dostep do processesList

// USTAWIÆ POCZ¥TKOWE WARTOŒCI PÓL RUNNING I NEXTTRY (!)

/**
 * Pole przechowuj¹ce aktualnie uruchomiony proces.
 * 
 * @author £UKASZ WOLNIAK
 */
//Proces RUNNING; 

/**
 * Pole przechowuj¹ce kandydata na kolejny proces do uruchomienia.
 * Przed pobraniem procesu z NEXTTRY i wrzuceniu go do RUNNING, sprawdzane jest czy w liœcie procesów nie ma procesu o wy¿szym piorytecie. 
 * Je¿eli takowy jest, to on zostaje wrzucony do pola RUNNING, a pole NEXTTRY siê nie zmienia.
 * 
 * @author £UKASZ WOLNIAK
 */
//Proces NEXTTRY;


public class processorManager {

/**
 * Pêtla sprawdzaj¹ca, czy w polu NEXTTRY jest na pewno proces o najwy¿szym piorytecie.
 * Je¿eli na liœcie jest proces o wiêkszym piorytecie - zmieniane jest pole NEXTTRY.
 * 
 * @author £UKASZ WOLNIAK
 */
for(Proces processFromList : processesList){
	if(processFromList.GetCurrentPriority()>NEXTTRY.GetCurrentPriority()){
	NEXTTRY = processFromList;
	}
}

;;

}
