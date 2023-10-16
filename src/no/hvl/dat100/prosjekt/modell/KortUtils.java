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
		
		/*
		 * Bruker boblesortering for å sortere en array av `Kort`-objektet ved å bruke compareTo-metoden.
		 * Når arrayen er sortert, tømmes samlingen og de sorterte kortene legges tilbake i den riktige rekkefølgen.
		 */
		
		Kort[] kort = samling.getAllekort();
		int n = kort.length;
		boolean sortert = true;
		
		while (sortert) {
			sortert = false;
			for (int i = 1; i < n; i++) {
				if (kort[i - 1].compareTo(kort[i]) > 0) {
					Kort temp = kort[i];
					kort[i] = kort[i - 1];
					kort[i - 1] = temp;
					sortert = true;
				}
			}
			n--;
		}
		
		samling.fjernAlle();
		for (Kort k : kort) {
			samling.leggTil(k);
		}
	}
	
	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
	public static void stokk(KortSamling samling) {
		
		/*
		 * Stokker samlingen ved å bruke Random-klassen til å bytte hvert kort med et tilfeldig
		 * valgt kort i samlingen. Dette gjøres gjentatte ganger for hvert kort i samlingen,
		 * noe som til slutt resulterer i en tilfeldig stokket samling.
		 * 
		 * Denne algoritmen er også kjent som "Fisher-Yates shuffle Algorithm".
		 * KILDE: https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
		 */
		
		Random rand = new Random();
		
		for (int i = samling.getAntalKort() - 1; i > 0; i--) {
			int j = rand.nextInt(i + 1);
			Kort temp = samling.getSamling()[i];
			samling.getSamling()[i] = samling.getSamling()[j];
			samling.getSamling()[j] = temp;
		}
		
	}
	
}
