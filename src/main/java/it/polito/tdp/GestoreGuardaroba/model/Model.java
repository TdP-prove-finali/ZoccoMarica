package it.polito.tdp.GestoreGuardaroba.model;

import java.util.List;

import it.polito.tdp.GestoreGuardaroba.db.GuardarobaDAO;

public class Model {
	
	private GuardarobaDAO guardarobaDAO;
	
	public Model() {
		this.guardarobaDAO = new GuardarobaDAO();
	}
	
	public List<Capo> getCapi() {
		return this.guardarobaDAO.getCapi();
	}
	
	public boolean esisteCapo(Capo c) {
		return this.guardarobaDAO.esisteCapo(c);
	}
	
	public List<String> getSottotipiByTipo(String tipo) {
		return this.guardarobaDAO.getSottotipiByTipo(tipo);
	}
	
	public List<String> getColori() {
		return this.guardarobaDAO.getColori();
	}
	
	public boolean aggiungiCapo(Capo c) {
		return this.guardarobaDAO.aggiungiCapo(c);
	}
	
	public boolean eliminaCapo(Capo c) {
		return this.guardarobaDAO.eliminaCapo(c);
	}

}
