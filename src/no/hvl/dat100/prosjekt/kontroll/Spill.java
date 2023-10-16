package no.hvl.dat100.prosjekt.kontroll;

import java.util.ArrayList;

import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortUtils;
import no.hvl.dat100.prosjekt.modell.Kortfarge;

/**
 * Klassen har objektvariaber som er referanser til de spillerne, nord og syd
 * (type ISpiller). Den har ogsÂ en bunke man deler/trekker fra og en bunke man
 * spiller til.
 * 
 */
public class Spill {

	private ISpiller nord;
	private ISpiller syd;
	
	private Bord bord;
	
	// antall kort som skal deles ut til hver spiller ved start
	public final static int ANTALL_KORT_START = Regler.ANTALL_KORT_START;
	
	public Spill() {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.constructor("Spill"));
		// TODO - END
		
		nord = new NordSpiller(Spillere.NORD);
		syd = new SydSpiller(Spillere.SYD);
		bord = new Bord();
	}
	
	/**
	 * Gir referanse/peker til bord.
	 * 
	 * @return referanse/peker bord objekt.
	 */
	public Bord getBord() {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - END
		
		return bord;
		
	}
	
	/**
	 * Gir referanse/peker til syd spilleren.
	 * 
	 * @return referanse/peker til syd spiller.
	 */
	public ISpiller getSyd() {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - END
		
		return syd;
		
	}

	/**
	 * Gir referanse/peker til nord.
	 * 
	 * @return referanse/peker til nord.
	 */
	public ISpiller getNord() {
		
		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - END
		
		return nord;
	}

	/**
	 * Metoden oppretter to spillere, nord og syd. Det opprettes to bunker, fra
	 * og til. Alle kortene legges til fra. Bunken fra stokkes. Deretter deles
	 * det ut kort fra fra-bunken til nord og syd i henhold til regler. Til
	 * slutt tas øverste kortet fra fra-bunken og legges til til-bunken.
	 * 
	 * Nord har type RandomSpiller (som er forhåndefinert). Syd vil være spiller
	 * av en klasse laget av gruppen (implementeres i oppgave 3).
	 */
	public void start() {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		// TODO - END
		
		/*nord = this.nord;
		syd = this.syd;
		
		bord = new Bord();
		
		bord.getBunkeFra().leggTilAlle();
		KortUtils.stokk(bord.getBunkeFra());
		
		for (int i = 0; i < ANTALL_KORT_START; i++) {
			nord.leggTilKort(bord.getBunkeFra().taSiste());
			syd.leggTilKort(bord.getBunkeFra().taSiste());
		}
		
		bord.getBunkeTil().leggTil(bord.getBunkeFra().taSiste());*/
		
		KortUtils.stokk(bord.getBunkeFra());
		
		for (int i = 0; i < Regler.ANTALL_KORT_START; i++) {
			nord.leggTilKort(bord.taOversteFraBunke());
			syd.leggTilKort(bord.taOversteFraBunke());
		}
		
		bord.vendOversteFraBunke();
	}

	/**
	 * Deler ut kort til nord og syd.
	 * 
	 */
	private void delutKort() {

		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		// TODO - END
		
		KortSamling bunkefra = bord.getBunkeFra();
		
		for (int i = 0; i < ANTALL_KORT_START; i++) {
			nord.leggTilKort(bunkefra.taSiste());
			syd.leggTilKort(bunkefra.taSiste());
		}
		
		Kort oversteKort = bunkefra.taSiste();
		bord.getBunkeTil().leggTil(oversteKort);
		
	}

	/**
	 * Trekker et kort fra fra-bunken til spilleren gitt som parameter. Om
	 * fra-bunken er tom, må man "snu" til-bunken. Se info om metoden
	 * snuTilBunken().
	 * 
	 * @param spiller
	 *            spilleren som trekker.
	 * 
	 * @return kortet som trekkes.
	 */
	public Kort trekkFraBunke(ISpiller spiller) {

		// TODO - START
			
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - END
		
		/*KortSamling bunkefra = bord.getBunkeFra();
		KortSamling bunketil = bord.getBunkeTil();
		
		if (bunkefra.erTom()) {
			while (!bunketil.erTom()) {
				bunkefra.leggTil(bunketil.taSiste());
			}
			
			KortUtils.stokk(bunketil);
		}
		
		Kort trukketKort = bunkefra.taSiste();
		spiller.leggTilKort(trukketKort);
		
		return trukketKort;*/
		
		KortSamling bunkefra = bord.getBunkeFra();
		KortSamling bunketil = bord.getBunkeTil();
		
		if (bunkefra.erTom()) {
			while (!bunketil.erTom()) {
				Kort kort = bunketil.taSiste();
				bunkefra.leggTil(kort);
			}
		}
		
		Kort trukketKort = bunkefra.taSiste();
		spiller.trekker(trukketKort);
		return trukketKort;
	}

	/**
	 * Gir neste handling for en spiller (spilt et kort, trekker et kort, forbi)
	 * 
	 * @param spiller
	 *            spiller som skal handle.
	 * 
	 * @return handlingen som skal utføres av kontroll delen.
	 */
	public Handling nesteHandling(ISpiller spiller) {
		
		// TODO - START
		// Hint: se på hvilke metoder som er tilgjengelig på en spiller
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - END
		
		Kort oversteBunkeTil = bord.seOversteBunkeTil();
		Kort[] alleKort = spiller.getHand().getAllekort();
		
		for (Kort kort : alleKort) {
			if (Regler.kanLeggeNed(kort, oversteBunkeTil)) {
				return new Handling(HandlingsType.LEGGNED, kort);
			}
		}
		
		if (spiller.getAntallTrekk() < Regler.maksTrekk() && !bord.bunkefraTom()) {
			return new Handling(HandlingsType.TREKK, null);
		}
		
		return new Handling(HandlingsType.FORBI, null);
		
	}

	/**
	 * Metoden spiller et kort. Den sjekker at spiller har kortet. Dersom det er
	 * tilfelle, fjernes kortet fra spilleren og legges til til-bunken. Metoden
	 * nulltiller også antall ganger spilleren har trukket kort.
	 * 
	 * @param spiller
	 *            den som spiller.
	 * @param kort
	 *            kort som spilles.
	 * 
	 * @return true dersom spilleren har kortet, false ellers.
	 */
	public boolean leggnedKort(ISpiller spiller, Kort kort) {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - END
		
		KortSamling bunkeTil = bord.getBunkeTil();
		KortSamling spillerHand = spiller.getHand();
		
		if (spillerHand.har(kort)) {
			spillerHand.fjern(kort);
			bunkeTil.leggTil(kort);
			spiller.setAntallTrekk(0);
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * Metode for å si forbi. Må nullstille antall ganger spilleren har trukket
	 * kort.
	 * 
	 * @param spiller
	 *            spilleren som er i tur.
	 */
	public void forbiSpiller(ISpiller spiller) {
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
	
		// TODO - END
		
		spiller.setAntallTrekk(0);
	}

	/**
	 * Metode for å utføre en handling (trekke, spille, forbi). Dersom handling
	 * er kort, blir kortet også spilt.
	 * 
	 * @param spiller
	 *            spiller som utfører handlingen.
	 * @param handling
	 *            handling som utføres.
	 * 
	 * @return kort som trekkes, kort som spilles eller null ved forbi.
	 */
	public Kort utforHandling(ISpiller spiller, Handling handling) {

		// TODO - START
		Kort kort = null;
		
		if (handling.getType() == HandlingsType.TREKK) {
			kort = trekkFraBunke(spiller);
		} else if (handling.getType() == HandlingsType.LEGGNED) {
			kort = handling.getKort();
			leggnedKort(spiller, kort);
		} else if (handling.getType() == HandlingsType.FORBI) {
			forbiSpiller(spiller);
		}
		
		return kort;

		// Hint: del opp i de tre mulige handlinger og vurder 
		// om noen andre private metoder i klassen kan brukes
		// til å implementere denne metoden
				
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - END
	}

}