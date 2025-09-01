package it.polito.tdp.GestoreGuardaroba.model;

public class Abbinamento {
	private int id_colore;
	private int id_abbinato;
	
	public Abbinamento(int id_colore, int id_abbinato) {
		super();
		this.id_colore = id_colore;
		this.id_abbinato = id_abbinato;
	}
	
	public int getId_colore() {
		return id_colore;
	}
	
	public void setId_colore(int id_colore) {
		this.id_colore = id_colore;
	}
	
	public int getId_abbinato() {
		return id_abbinato;
	}
	
	public void setId_abbinato(int id_abbinato) {
		this.id_abbinato = id_abbinato;
	}
	
	
}
