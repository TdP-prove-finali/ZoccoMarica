package it.polito.tdp.GestoreGuardaroba.model;

import java.util.Objects;

public class Colore {
	
	private int id;
	private String nome;
	
	public Colore(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Colore other = (Colore) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return nome + "\n";
	}
	

}
