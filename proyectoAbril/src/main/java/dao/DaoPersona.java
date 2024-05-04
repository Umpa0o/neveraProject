package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.User;

public class DaoPersona {
	public static Connection con = null;
	
	public DaoPersona() throws SQLException {
		this.con=conectaDB.getConexion();
		
	}//fin constructor
	
	
	public void insertarPersona(User ue) throws SQLException {
		
		String query = "INSERT INTO usuario (nombreUsuario, emailUsuario, passwordUsuario) VALUES (?,?,?) ";
		PreparedStatement ps= con.prepareStatement(query);
		System.out.println(query);
		ps.setString(1, ue.getNombreUser());
		ps.setString(2, ue.getEmail());
		ps.setString(3, ue.getPasswordUser());
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}// fin insertarPersona()

}//fin clase DaoPersona
