package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.modell.KortUtils;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;

/**
 * Abstrakt klasse som implementerer alle metodene i kontrakten (interfacet) ISpiller,
 * bortsett fra nesteHandling(). Dette er grunnen til at klassen er abstrakt.
 * For å lage "virkelige" spillere, må vi arve fra denne klassen og implementere
 * nesteHandling (fra ISpiller).
 * 
 * Klassen har objektvariablene hand (Hand), antalltrekk (heltall) og spiller
 * (Spillere). Den har to konstruktører. Se beskrivelse av disse.
 * 
 */
public abstract class Spiller implements ISpiller {

	// hand for spilleren (samling)
	private KortSamling hand; 
	
	// antall trekk spilleren har gjort fra fra-bunken
	private int antalltrekk; 
	
	// hvem spilleren er (Nord,Syd,Ingen) - se oppramsklassen Spillere
	private Spillere spiller;

	/**
	 * Standard konstruktør som oppretter en Spiller med en hånd uten kort,
	 * antalltrekk som 0 og setter spiller til Spillere.INGEN.
	 */
	public Spiller() {
		
		/*
		 * Oppretter en ny spiller med en tom hånd.
		 * Vi setter antalltrekk til 0.
		 * Spilleren blir satt til `Spillere.INGEN`.
		 */
		
		hand = new KortSamling();
		antalltrekk = 0;
		spiller = Spillere.INGEN;
	}

	/**
	 * Konstruktør der vi kan sette hvilken spiller det er (NORD, SYD eller
	 * INGEN).
	 * 
	 * @param spiller
	 *            hvilken spiller det er.
	 */
	public Spiller(Spillere spiller) {
		
		/*
		 * Oppretter en ny spiller med en tom hånd.
		 * Vi setter antalltrekk til 0.
		 * Spilleren blir satt til `spiller`, som er parameteren som blir gitt i konstruktøren.
		 */
		
		hand = new KortSamling();
		antalltrekk = 0;
		this.spiller = spiller;
	}

	public int getAntallKort() {
		
		/*
		 * Bruker `getAntallKort`-metoden fra `KortSamling` for å hente antall kort i hånden til spilleren.
		 */
		
		return hand.getAntalKort();
	}

	public KortSamling getHand() {
		
		/*
		 * Returnerer referanse til `hand`, som er hånden til spilleren.
		 */
		
		return hand;
	}

	public int getAntallTrekk() {
		
		/*
		 * Returnerer antall trekk som spilleren har gjort.
		 */
		
		return antalltrekk;
	}

	public Spillere hvem() {
		
		/*
		 * Returnerer hvem spilleren er, representert av `spiller`-variabelen.
		 */
		
		return spiller;
		
	}

	public void setAntallTrekk(int t) {
		
		/*
		 * Setter antalltrekk til verdien som er gitt som parameter `t`.
		 */
		
		antalltrekk = t;
	}

	public boolean erFerdig() {
		
		/*
		 * Sjekker om hånden til spilleren er tom vha. `erTom`-metoden fra `KortSamling`.
		 * Hvis hånden er tom, returnerer metoden `true`.
		 * Hvis hånden ikke er tom, returnerer metoden `false`.
		 */
		
		return hand.erTom();
		
	}

	public void leggTilKort(Kort kort) {
		
		/*
		 * Bruker `leggTil`-metoden fra `KortSamling` for å legge til kortet `kort` i hånden.
		 */
		
		hand.leggTil(kort);
		
	}

	public void fjernKort(Kort kort) {
		
		/*
		 * Bruker `fjern`-metoden fra `KortSamling` til å fjerne kortet `kort` fra hånden.
		 */
		
		hand.fjern(kort);
		
	}

	public void fjernAlleKort() {
		
		/*
		 * Bruker `fjernAlle`-metoden fra `KortSamling` for å fjerne alle kortene fra hånden.
		 */
		
		hand.fjernAlle();
	}

	public void trekker(Kort kort) {
		
		/*
		 * Legger til kortet `kort` i hånden vha. `leggTil`-metoden fra `KortSamling`.
		 * Vi øker antalltrekk med 1.
		 */
		
		hand.leggTil(kort);
		antalltrekk++;
		
	}
}
