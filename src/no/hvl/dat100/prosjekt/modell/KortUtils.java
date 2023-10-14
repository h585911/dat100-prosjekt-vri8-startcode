package no.hvl.dat100.prosjekt.modell;

import java.util.Arrays;
import java.util.Random;

import no.hvl.dat100.prosjekt.TODO;

public class KortUtils {

	/**
	 * Sorterer en samling. Rekkefølgen er bestemt av compareTo() i Kort-klassen.
	 * 
	 * @see Kort
	 * 
	 * @param samling
	 * 			samling av kort som skal sorteres. 
	 */
	
	public static void sorter(KortSamling samling) {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		// TODO - END
		
		/*Kort[] kort = samling.getAllekort();
		int n = kort.length;
		boolean sortert = false;
		
		while (!sortert) {
			sortert = true;
			for (int i = 0; i < n - 1; i++) {
				if (kort[i].compareTo(kort[i + 1]) > 0) {
					Kort temp = kort[i];
					kort[i] = kort[i + 1];
					kort[i + 1] = temp;
					sortert = false;
				}
			}
			
			n--;
		}*/
		Kort[] kort = samling.getAllekort();
		Arrays.sort(kort);
		for (int i = 0; i < kort.length; i++) {
			samling.fjernAlle();
			for (Kort k : kort) {
				samling.leggTil(k);
			}
		}
	}
	
	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
	public static void stokk(KortSamling samling) {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		// TODO - END
		Kort[] kort = samling.getAllekort();
		Random rand = new Random();
		
		for (int i = kort.length - 1; i > 0; i--) {
			int j = rand.nextInt(i + 1);
			Kort temp = kort[i];
			kort[i] = kort[j];
			kort[j] = temp;
		}
	}
	
}
