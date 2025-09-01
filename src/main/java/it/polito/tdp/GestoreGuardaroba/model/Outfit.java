package it.polito.tdp.GestoreGuardaroba.model;

public class Outfit {
	private Capo superiore;
	private Capo inferiore;
	private Capo intero;
	private Capo scarpe;
	private Capo capospalla;
	
	//Outfit superiore + inferiore
	public Outfit(Capo superiore, Capo inferiore, Capo scarpe, Capo capospalla) {
		super();
		this.superiore = superiore;
		this.inferiore = inferiore;
		this.scarpe = scarpe;
		this.capospalla = capospalla;
	}
	
	//Outfit intero
	public Outfit(Capo intero, Capo scarpe, Capo capospalla) {
		super();
		this.intero = intero;
		this.scarpe = scarpe;
		this.capospalla = capospalla;
	}

	public Capo getSuperiore() {
		return superiore;
	}

	public Capo getInferiore() {
		return inferiore;
	}

	public Capo getIntero() {
		return intero;
	}

	public Capo getScarpe() {
		return scarpe;
	}

	public Capo getCapospalla() {
		return capospalla;
	}

	@Override
	public String toString() {
		String testo="";
		
		if(capospalla!=null)
			testo+=capospalla + "\n";
		
		if(intero!=null)
			testo+=intero + "\n" + scarpe;
		else
			testo+=superiore + "\n" + inferiore + "\n" + scarpe;
		
		return testo;
	}
	
	
}
