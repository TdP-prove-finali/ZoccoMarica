package it.polito.tdp.GestoreGuardaroba.model;

public class Adiacenza {
	private Capo c1;
	private Capo c2;
	
	public Adiacenza(Capo c1, Capo c2) {
		super();
		this.c1 = c1;
		this.c2 = c2;
	}

	public Capo getC1() {
		return c1;
	}

	public void setC1(Capo c1) {
		this.c1 = c1;
	}

	public Capo getC2() {
		return c2;
	}

	public void setC2(Capo c2) {
		this.c2 = c2;
	}

	@Override
	public String toString() {
		return c1 + " - " + c2;
	}
		
}
