package it.polito.tdp.GestoreGuardaroba.model;

import java.util.Objects;

public class Capo {
	private int id;
	private String tipo;
	private String sottotipo;
	private String colore;
	private String stagione;
	private String occasione;
	private String marca;
	
	public Capo(int id, String tipo, String sottotipo, String colore, String stagione, String occasione, String marca) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.sottotipo = sottotipo;
		this.colore = colore;
		this.stagione = stagione;
		this.occasione = occasione;
		this.marca = marca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSottotipo() {
		return sottotipo;
	}

	public void setSottotipo(String sottotipo) {
		this.sottotipo = sottotipo;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getStagione() {
		return stagione;
	}

	public void setStagione(String stagione) {
		this.stagione = stagione;
	}

	public String getOccasione() {
		return occasione;
	}

	public void setOccasione(String occasione) {
		this.occasione = occasione;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Capo other = (Capo) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Capo [tipo=" + tipo + ", sottotipo=" + sottotipo + ", colore=" + colore + ", marca=" + marca + "]";
	}
	
	
}
