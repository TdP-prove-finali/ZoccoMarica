package it.polito.tdp.GestoreGuardaroba.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.GestoreGuardaroba.db.GuardarobaDAO;

public class Model {
	
	private GuardarobaDAO guardarobaDAO;
	private Graph <Capo, DefaultEdge> grafo;
	private Map <Integer, Capo> idMap;
	private Map<Integer, Colore> idColoriMap;
	
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
	
	public List<Colore> getColori() {
		return this.guardarobaDAO.getColori();
	}
	
	public List<Abbinamento> getAbbinamenti() {
		return this.guardarobaDAO.getAbbinamenti();
	}
	
	public boolean aggiungiCapo(Capo c) {
		return this.guardarobaDAO.aggiungiCapo(c);
	}
	
	public boolean eliminaCapo(Capo c) {
		return this.guardarobaDAO.eliminaCapo(c);
	}
	
	private void creaGrafo(String stagione, String occasione) {
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		this.idMap = new HashMap<>();
		
		this.guardarobaDAO.getVertici(stagione, occasione, idMap);
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		List <Adiacenza> archi = new ArrayList<>();		
		this.idColoriMap = new HashMap<>();
		
		for(Colore c : this.getColori()) 
			idColoriMap.put(c.getId(), c);
		
		for (Abbinamento abb : this.getAbbinamenti()) {
	        Colore col1 = idColoriMap.get(abb.getId_colore());
	        Colore col2 = idColoriMap.get(abb.getId_abbinato());

	        for (Capo c1 : idMap.values()) {
	            for (Capo c2 : idMap.values()) {	            	
	                if (c1.getId() < c2.getId()) { 
	                	
	                	//controllo se i colori dei due capi rispettano l'abbinamento
	                    if ((c1.getColore().equalsIgnoreCase(col1.getNome()) &&
	                         c2.getColore().equalsIgnoreCase(col2.getNome())) ||
	                        (c1.getColore().equalsIgnoreCase(col2.getNome()) &&
	                         c2.getColore().equalsIgnoreCase(col1.getNome()))) {

	                            archi.add(new Adiacenza(c1, c2));     
	                    }
	                }
	            }
	        }
	    }
		
		for(Adiacenza c: archi)
			grafo.addEdge(c.getC1(), c.getC2());		
	}
	
	public List<Outfit> creaOutfit(String stagione, String occasione, Colore colore) {
	    this.creaGrafo(stagione, occasione);

	    List<Outfit> result = new ArrayList<>();
	    List<Outfit> result_colore = new ArrayList<>();

	    List<Capo> interi = new ArrayList<>();
	    List<Capo> superiori = new ArrayList<>();
	    List<Capo> inferiori = new ArrayList<>();
	    List<Capo> scarpe = new ArrayList<>();
	    List<Capo> capispalla = new ArrayList<>();

	    for (Capo c : idMap.values()) {
	        switch (c.getTipo().toLowerCase()) {
	            case "intero": interi.add(c); break;
	            case "superiore": superiori.add(c); break;
	            case "inferiore": inferiori.add(c); break;
	            case "scarpe": scarpe.add(c); break;
	            case "capospalla": capispalla.add(c); break;
	        }
	    }

	    boolean estate = stagione.equalsIgnoreCase("estate");

	    // Outfit con intero
	    for (Capo intero : interi) {
	        for (Capo shoe : scarpe) {
	            if (grafo.containsEdge(intero, shoe)) {
	                if (!estate) {
	                    for (Capo spalla : capispalla) {
	                        if (grafo.containsEdge(intero, spalla) &&
	                            grafo.containsEdge(shoe, spalla)) {
	                            result.add(new Outfit(intero, shoe, spalla));
	                        }
	                    }
	                } else {
	                    result.add(new Outfit(intero, shoe, null));
	                }
	            }
	        }
	    }

	    // Outfit con superiore + inferiore
	    for (Capo sup : superiori) {
	        for (Capo inf : inferiori) {
	            if (grafo.containsEdge(sup, inf)) {
	                for (Capo shoe : scarpe) {
	                    if (grafo.containsEdge(sup, shoe) && grafo.containsEdge(inf, shoe)) {
	                        if (!estate) {
	                            for (Capo spalla : capispalla) {
	                                if (grafo.containsEdge(sup, spalla) &&
	                                    grafo.containsEdge(inf, spalla) &&
	                                    grafo.containsEdge(shoe, spalla)) {
	                                    result.add(new Outfit(sup, inf, shoe, spalla));
	                                }
	                            }
	                        } else {
	                            result.add(new Outfit(sup, inf, shoe, null));
	                        }
	                    }
	                }
	            }
	        }
	    }
	    
	    if(colore!= null) { //almeno un capo deve avere il colore selezionato
	    	for(Outfit o : result) {
    		if(this.capoHaColore(o.getCapospalla(), colore) || this.capoHaColore(o.getInferiore(), colore)
    				|| this.capoHaColore(o.getIntero(), colore) || this.capoHaColore(o.getScarpe(), colore)
    				|| this.capoHaColore(o.getSuperiore(), colore))
    			result_colore.add(o);
	    	}
	    	
	    return result_colore; 
	    }
	    
	    return result;
	}
	
	private boolean capoHaColore(Capo capo, Colore colore) {
		if(capo == null)
			return false;
		else 
			return capo.getColore().equalsIgnoreCase(colore.getNome());	
	}
	
	public String capitalizeMarca(String m) {
		String[] parole = m.trim().toLowerCase().split("\\s+"); 
		
		/* 
		   trim() --> rimuovo eventuali spazi all'inizio e alla fine
		   toLowerCase() --> converto tutto in minuscolo
		   split("\\s+") --> divido la stringa in parole usando lo spazio come separatore 
		*/
		
		String result = "";	//costruisco la nuova stringa concatenando le parole con un ciclo
		
		for(String parola : parole) {
			if(!parola.isEmpty())
				result += parola.substring(0,1).toUpperCase() + parola.substring(1) + " ";		
		}
		
		/* 
		   !parola.isEmpty() --> controllo che non sia vuota nel caso in cui ci sono spazi multipli tra le parole
		   parola.substring(0,1).toUpperCase() --> trasformo la prima lettera in maiuscolo
		   parola.substring(1) --> prendo il resto della parola così com'è in minuscolo
		   + " " --> aggiungo uno spazio dopo la parola
		*/
		
		return result.trim(); //trim() --> rimuovo lo spazio dato dall'ultimo + " "
	}

}
