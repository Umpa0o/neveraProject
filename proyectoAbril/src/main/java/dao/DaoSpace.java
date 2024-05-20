package dao;

import java.sql.Connection;
import java.sql.SQLException;

import modelo.Space;
//clase DAO para el tipo objeto Space

public class DaoSpace {
	//atributo estatico tipo connection
	public static Connection con = null;
	
	//constructor con conexion a DB
	public DaoSpace() throws SQLException {		
		this.con = conectaDB.getConexion();
 		
	}//fin constructor 
	
	//MÉTODOS CRUD
	
	//insertarEspacio en DB
	public void insertarEspacio(Space sp) {
		PreparedStatement pst;
		ResultSet rs;
		
		String query = "INSERT INTO espacio(nombreEspacio, regimenTemperatura, icono, id_usuario) VALUES (?,?,?,?) ";
		pst= con.prepareStatement(query);
		pst.setString(1, sp.getNombreEspacio());
	}//fin isertarEspacio()
	

}//fin daoSpace
