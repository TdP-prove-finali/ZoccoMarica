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
	
	public boolean esisteCapo(String tipo, String sottotipo, String colore, String stagione, String occasione, String marca) {
		return this.guardarobaDAO.esisteCapo(tipo, sottotipo, colore, stagione, occasione, marca);
	}
	
	public List<String> getSottotipiByTipo(String tipo) {
		return this.guardarobaDAO.getSottotipiByTipo(tipo);
	}
	
	public List<String> getColori() {
		return this.guardarobaDAO.getColori();
	}
	
	public boolean AggiungiCapo(String tipo, String sottotipo, String colore, String stagione, String occasione, String marca) {
		return this.guardarobaDAO.AggiungiCapo(tipo, sottotipo, colore, stagione, occasione, marca);
	}

}
