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
			
			try (Connection conn = ConnectDB.getConnection();
					PreparedStatement st = conn.prepareStatement(sql)) {

				st.setString(1, c.getTipo());
		        st.setString(2, c.getSottotipo());
		        st.setString(3, c.getColore());
		        st.setString(4, c.getStagione());
		        st.setString(5, c.getOccasione());
		        st.setString(6, c.getMarca());

				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					return rs.getInt("cnt") > 0; 
				}

			} catch (SQLException e) {
				System.out.println("Errore in Guardaroba DAO");
				e.printStackTrace();
			}
			return false;  //Se il count è 0, il capo non è ancora presente
	}

		
	public List<String> getSottotipiByTipo(String tipo) {
	    String sql = "SELECT DISTINCT sottotipo FROM capo WHERE tipo = ? ORDER BY sottotipo";
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

	
	public boolean aggiungiCapo(Capo c) {
		
		String sql = "INSERT INTO capo (tipo, sottotipo, colore, stagione, occasione, marca) "
	               + "VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        Connection conn = ConnectDB.getConnection();
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

	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(sql)) {

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
	
}
