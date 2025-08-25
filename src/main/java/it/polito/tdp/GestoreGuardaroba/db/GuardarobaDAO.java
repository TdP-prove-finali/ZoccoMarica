package it.polito.tdp.GestoreGuardaroba.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.GestoreGuardaroba.model.Capo;

public class GuardarobaDAO {
	
	public List<Capo> getCapi() {
		String sql = "SELECT * "
				+ "FROM capo ";
		
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
	
	public List<String> getSottotipiByTipo(String tipo) {
	    String sql = "SELECT DISTINCT sottotipo FROM capo WHERE tipo = ? ORDER BY sottotipo ASC";
	    List<String> resultSottotipi = new ArrayList<>();

	    try {
	        Connection conn = ConnectDB.getConnection();
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
	
	public List<String> getColori() {
	    String sql = "SELECT nome FROM colore";

	    List<String> resultColore = new ArrayList<>();

	    Connection conn = ConnectDB.getConnection();

	    try {
	        PreparedStatement st = conn.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            resultColore.add(rs.getString("nome"));
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

	
	public boolean AggiungiCapo(String tipo, String sottotipo, String colore, String stagione, String occasione, String marca) {
		
		String sql = "INSERT INTO capo (tipo, sottotipo, colore, stagione, occasione, marca) "
	               + "VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        Connection conn = ConnectDB.getConnection();
	        PreparedStatement st = conn.prepareStatement(sql);

	        st.setString(1, tipo);
	        st.setString(2, sottotipo);
	        st.setString(3, colore);
	        st.setString(4, stagione);
	        st.setString(5, occasione);
	        st.setString(6, marca);

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

}
