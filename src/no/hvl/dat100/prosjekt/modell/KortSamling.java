package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;

/**
 * Struktur for å lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene. Når
 * programmet er ferdig settes denne til 13, men under utvikling / testing kan
 * det være praktisk å ha denne mindre.
 * 
 */
public class KortSamling {

	private final int MAKS_KORT = 4 * Regler.MAKS_KORT_FARGE;

	private Kort[] samling;
	private int antall;

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() {
		
		/*
		 * Oppretter en KONSTRUKTUR som er en tom kortsamling / array med plass til MAKS_KORT (hele kortstokken).
		 * Lengden på arrayen er nå MAKS_KORT lang. Setter antall kort til 0.
		 */
		this.samling = new Kort[MAKS_KORT];
		this.antall = 0;
	}

	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke være
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan få
	 * tilgang til antallet ved å bruke metoden getAntallKort(). Metoden er
	 * først og fremst ment å brukes i testklasser. Om man trenger
	 * kortene utenfor, anbefales metoden getAlleKort().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() {
		
		return samling;
		
	}
	
	/**
	 * Antall kort i samlingen.
	 * 
	 * @return antall kort i samlinga.
	 */
	public int getAntalKort() {
		
		/*
		 * Returnerer referanse til `antall`, som er antall kort i samlingen.
		 */
		
		return antall;
	}
	
	/**
	 * Sjekker om samlinga er tom.
	 * 
	 * @return true om samlinga er tom, false ellers.
	 */
	public boolean erTom() {
		
		/*
		 * Sjekker om samlingen er tom ved å sjekke om antall er 0.
		 */
		
		return antall == 0;
	}

	/**
	 * Legg et kort til samlinga.
	 * 
	 * @param kort
	 *            er kortet som skal leggast til.
	 */
	public void leggTil(Kort kort) {
		
		/*
		 * Sjekker først om antallet kort i samlingen er mindre eller lik MAKS_KORT.
		 * Deretter legger vi til et kort i samlingen.
		 */
		if(antall < MAKS_KORT) {
			samling[antall] = kort;
			antall++;
		}
		
	}
	
	/**
	 * Legger alle korta (hele kortstokken) til samlinga. Korta vil være sortert
	 * slik at de normalt må stokkes før bruk.
	 */
	public void leggTilAlle() {
		
		/*
		 * Legger til alle kortene i kortstokken til samlingen ved å iterere gjennom alle mulige kortfarger og verdier.
		 */
		for (Kortfarge f : Kortfarge.values()) {
			for (int i = 1; i <= Regler.MAKS_KORT_FARGE; i++) {
				Kort kort = new Kort(f, i);
				leggTil(kort);
			}
		}
	}

	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {
		
		antall = 0;
	}
	
	/**
	 * Ser på siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort seSiste() {
		
		/*
		 * Sjekker om samlingen ikke er tom ved å sjekke om antallet er større enn 0.
		 * Hvis samlingen ikke er tom, returneres det siste kortet i samlingen uten å fjerne det.
		 * Hvis samlingen er tom, returneres null.
		 */
		
		if (antall > 0) {
			return samling[antall - 1];
		} else {
			return null;
		}
		
	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort taSiste() {
		
		/*
		 * Sjekker om samlingen ikke er tom ved å sjekke om antallet er større enn 0.
		 * Hvis samlingen ikke er tom, returneres `antall` med 1, 
		 * og det siste kortet fjernes fra samlingen og returneres. 
		 * 
		 * Hvis samlingen er tom, returneres `null`.
		 */
		if (antall > 0) {
			antall--;
			Kort sisteKort = samling[antall];
			samling[antall] = null;
			return sisteKort;
		} else {
			return null;
		}
	}
	
	/**
	 * Undersøker om et kort finst i samlinga.
	 * 
	 * @param kort.
	 * 
	 * @return true om kortet finst i samlinga, false ellers.
	 */
	public boolean har(Kort kort) {
		
		/*
		 * Itererer gjennom samlingen og sammenligher hvert kort med det gitte kortet vha. 
		 * `equals()`-metoden. 
		 * 
		 * Hvis et matchende kort blir funnet, returneres `true`.
		 * Hvis ingen match blir funnet, returneres `false`.
		 */
		
		for (int i = 0; i < antall; i++) {
			if (samling[i].equals(kort)) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 * Fjernar et kort frå samlinga. Dersom kortet ikke finnest i samlinga,
	 * skjer ingenting med samilingen
	 * 
	 * @param kort
	 *            kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *            ingenting.
	 * @return true om kortet blev fjernet fra samlinga, false ellers.
	 */
			 
	public boolean fjern(Kort kort) {
		
		/*
		 * Itererer gjennom samlingen for å finne det gitte kortet.
		 * Hvis kortet blir funnet returneres `true`, og kortet fjernes det fra samlingen.
		 * Resten av samlingen forskyves for å fylle tomrommet. 
		 * Hvis kortet ikke blir funnet, returneres `false`.
		 */
		for (int i = 0; i < antall; i++) {
			if (samling[i].equals(kort)) {
				antall--;
				for (int j = i; j < antall; j++) {
					samling[j] = samling[j + 1];
				}
				samling[antall] = null;
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Gir kortene som en tabell av samme lengde som antall kort i samlingen
	 * 
	 * @return tabell av kort som er i samlingen, der kort skal ha samme rekkefølge
	 *         som i kortsamlinga.
	 */
	public Kort[] getAllekort() {
		
		/*
		 * Oppretter en ny array `alleKortKopi` med størrelse lik antall kort i samlingen.
		 * Alle kortene kopieres fra arrayen `samling` til den nye arrayen `alleKortKopi`.
		 * 
		 * På denne måten vil størrelsen på arrayen som returneres, være nøyaktig lik antall kort i samlingen.
		 */

		Kort[] alleKortKopi = new Kort[antall];
		for (int i = 0; i < antall; i++) {
			alleKortKopi[i] = samling[i];
		}
		
		return alleKortKopi;
	}
	
}
