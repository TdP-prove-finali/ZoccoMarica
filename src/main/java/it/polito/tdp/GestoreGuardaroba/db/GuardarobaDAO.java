package it.polito.tdp.GestoreGuardaroba.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.GestoreGuardaroba.model.Abbinamento;
import it.polito.tdp.GestoreGuardaroba.model.Capo;
import it.polito.tdp.GestoreGuardaroba.model.Colore;

public class GuardarobaDAO {
	
	public List<Capo> getCapi() {
		String sql = "SELECT * "
				+ "FROM capo ORDER BY sottotipo";
		
		List <Capo> resultCapo= new ArrayList<>();
		
		Connection conn = ConnectDB.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Capo c = new Capo(rs.getInt("id"), rs.getString("tipo"), rs.getString("sottotipo"),
						 rs.getString("colore"), rs.getString("stagione"), rs.getString("occasione"),
						  rs.getString("marca"));
				resultCapo.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			return resultCapo;
			
			} catch (SQLException e) {
				System.out.println("Errore in Guardaroba DAO");
				e.printStackTrace();
				return null;
			}
	}
	
	public boolean esisteCapo(Capo c) {
		
			String sql = "SELECT COUNT(*) AS cnt FROM capo "
						+ "WHERE tipo = ? AND sottotipo = ? AND colore = ? "
						+ "AND stagione = ? AND occasione = ? AND marca = ?";
			
			Connection conn = ConnectDB.getConnection();
			
			try {
				PreparedStatement st = conn.prepareStatement(sql);

				st.setString(1, c.getTipo());
		        st.setString(2, c.getSottotipo());
		        st.setString(3, c.getColore());
		        st.setString(4, c.getStagione());
		        st.setString(5, c.getOccasione());
		        st.setString(6, c.getMarca());

				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					return rs.getInt("cnt") > 0; //Se il count è 0, il capo non è ancora presente
				}

			} catch (SQLException e) {
				System.out.println("Errore in Guardaroba DAO");
				e.printStackTrace();
			}
			return false;  
	}

		
	public List<String> getSottotipiByTipo(String tipo) {
		
	    String sql = "SELECT DISTINCT sottotipo FROM capo WHERE tipo = ? ORDER BY sottotipo";
	    
	    List<String> resultSottotipi = new ArrayList<>();
	    
	    Connection conn = ConnectDB.getConnection();

	    try {
	        
	        PreparedStatement st = conn.prepareStatement(sql);
	        st.setString(1, tipo);

	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            resultSottotipi.add(rs.getString("sottotipo"));
	        }

	        rs.close();
	        st.close();
	        conn.close();
	        return resultSottotipi;

	    } catch (SQLException e) {
	    	 System.out.println("Errore in Guardaroba DAO");
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public List<Colore> getColori() {
	    String sql = "SELECT id, nome FROM colore";  // prendi anche l'id

	    List<Colore> resultColore = new ArrayList<>();

	    Connection conn = ConnectDB.getConnection();

	    try {
	        PreparedStatement st = conn.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            Colore c = new Colore(rs.getInt("id"), rs.getString("nome"));
	            resultColore.add(c);
	        }

	        st.close();
	        rs.close();
	        conn.close();
	        return resultColore;

	    } catch (SQLException e) {
	        System.out.println("Errore in Guardaroba DAO");
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public boolean aggiungiCapo(Capo c) {
		
		String sql = "INSERT INTO capo (tipo, sottotipo, colore, stagione, occasione, marca) "
	               + "VALUES (?, ?, ?, ?, ?, ?)";
		
		Connection conn = ConnectDB.getConnection();

	    try {
	        
	        PreparedStatement st = conn.prepareStatement(sql);

	        st.setString(1, c.getTipo());
	        st.setString(2, c.getSottotipo());
	        st.setString(3, c.getColore());
	        st.setString(4, c.getStagione());
	        st.setString(5, c.getOccasione());
	        st.setString(6, c.getMarca());

	        st.executeUpdate();

	        st.close();
	        conn.close();
	        return true;

	    } catch (SQLException e) {
	    	System.out.println("Errore in Guardaroba DAO");
	    	e.printStackTrace();
	    	return false;
	    }
		
	}
	
	public boolean eliminaCapo(Capo c) {
		
		String sql = "DELETE FROM capo "
	               + "WHERE tipo = ? AND sottotipo = ? "
	               + "AND colore = ? AND stagione = ? "
	               + "AND occasione = ? AND marca = ?";
		
		Connection conn = ConnectDB.getConnection();

	    try {
	    	PreparedStatement st = conn.prepareStatement(sql);

	        st.setString(1, c.getTipo());
	        st.setString(2, c.getSottotipo());
	        st.setString(3, c.getColore());
	        st.setString(4, c.getStagione());
	        st.setString(5, c.getOccasione());
	        st.setString(6, c.getMarca());

	        int rows = st.executeUpdate();
	        return rows > 0;  //ritorna true se è stato eliminato un capo

	    } catch (SQLException e) {
	    	System.out.println("Errore in Guardaroba DAO");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public void getVertici(String stagione, String occasione, Map<Integer, Capo> idMap) {
		String sql = "SELECT * "
				+ "FROM capo "
				+ "WHERE stagione = ? AND occasione = ? ";
		
		Connection conn = ConnectDB.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, stagione);
	        st.setString(2, occasione);
	        
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Capo c = new Capo(rs.getInt("id"), rs.getString("tipo"), rs.getString("sottotipo"),
						 rs.getString("colore"), rs.getString("stagione"), rs.getString("occasione"),
						  rs.getString("marca"));
				idMap.put(c.getId(), c);
			}
			
			st.close();
			rs.close();
			conn.close();
			
			} catch (SQLException e) {
				System.out.println("Errore in Guardaroba DAO");
				e.printStackTrace();
			}
	}
	
	public List<Abbinamento> getAbbinamenti() {
	    String sql = "SELECT * FROM abbinamento";

	    List<Abbinamento> result = new ArrayList<>();
	    
	    Connection conn = ConnectDB.getConnection();
	    
	    try {
	         PreparedStatement st = conn.prepareStatement(sql);
	         ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	        	Abbinamento a = new Abbinamento(rs.getInt("id_colore"), rs.getInt("id_abbinato"));
	            result.add(a);
	        }
	        return result;

	    } catch (SQLException e) {
	    	System.out.println("Errore in Guardaroba DAO");
	        e.printStackTrace();
	        return null;
	    }
	}

}
