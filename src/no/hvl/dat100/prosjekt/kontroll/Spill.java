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
		
		/*
		 * Oppretter to nye spillere: nord og syd.
		 * Dette er av typen `NordSpiller` og `SydSpiller`.
		 * Vi oppretter også ett nytt bord som er av typen `Bord`.
		 */
		
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
		
		/*
		 * Returnerer referanse til bordobjektet.
		 */
		
		return bord;
		
	}
	
	/**
	 * Gir referanse/peker til syd spilleren.
	 * 
	 * @return referanse/peker til syd spiller.
	 */
	public ISpiller getSyd() {
		
		/*
		 * Returnerer referanse til syd-spilleren.
		 */
		
		return syd;
		
	}

	/**
	 * Gir referanse/peker til nord.
	 * 
	 * @return referanse/peker til nord.
	 */
	public ISpiller getNord() {
		
		/*
		 * Returnerer referanse til nord-spilleren.
		 */
		
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
		
		/*
		 * Vi stokker først fra-bunken.
		 * 
		 * Etter dette legger vi til kortene for nord- og syd-spillerene i henhold til reglene.
		 * Dette gjør vi ved å bruke `taOversteFraBunke`-metoden i `Bord`-klassen.
		 * 
		 * Til slutt tar vi øverste kortet fra fra-bunken og legger det til til-bunken.
		 */
		
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

		/*
		 * Tydligvis totalt unødvendig metode som ikke blir brukt. 
		 */
		
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

		/*
		 * Vi sjekker først om `bunkefra`-objektet er tomt. 
		 * Dersom `bunketil`-objektet ikke er tomt, tar vi siste kortet fra `bunketil`,
		 * og legger det til `bunkefra`-objektet. 
		 */
		
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
		
		/*
		 * Vi henter det øverste kortet fra bunken, og deretter blir alle kortene til spilleren
		 * sjekket for å se om de kan legges ned i henhold til reglene.
		 * Hvis et kort kan legges ned, opprettes en handling av typen `LEGGNED` med det aktuelle kortet.
		 * 
		 * Hvis vi ikke kan gjøre denne handlingen, sjekker vi om antall tillatte trekk er mindre enn
		 * `Regler.maksTrekk`, og om fra-bunken har kort. Hvis dette er tilfelle, oppretter vi en ny 
		 * handling av typen `TREKK`. 
		 * 
		 * Hvis ingen av de handlingene ovenfor er oppfylt, oppretter vi en ny handling av typen `FORBI`.
		 */
		
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
		
		/*
		 * Først henter vi til-bunken og spillerens hånd. 
		 * Deretter bruker vi `har`-metoden fra `KortSamling` for å se om kortet er i spillerens hånd.
		 * Hvis dette er tilfellet, blir det fjernet fra hånden og lagt til til-bunken.
		 * Antall trekk for spilleren blir nullstilt.
		 * 
		 * Hvis kortet ikke finnes i hånden, returnerer metoden `false`.
		 */
		
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
		
		/*
		 * Antall trekk for spilleren blir nullstilt. 
		 */
		
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

		/*
		 * Vi definerer handlingene i henhold til hva som er spesifisert i oppgaven.
		 * Handlingene er enten: TREKK, LEGGNED eller FORBI.
		 * 
		 * Dersom handlingen er TREKK:
		 * 		Vi trekker ett kort fra fra-bunken.
		 * 
		 * Dersom handlingen er LEGGNED:
		 * 		Vi tar kortet fra spillerens hånd og legger det til til-bunken.
		 * 
		 * Dersom handlingen er FORBI:
		 * 		Vi sier pass, og det er neste spillers tur.
		 */
		
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
		
	}

}