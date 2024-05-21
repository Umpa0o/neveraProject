package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	/***insertarEspacio en DB*/
	public void insertarEspacio(Space sp) {
		PreparedStatement pr;
		ResultSet rs=null;
		
		String query = "INSERT INTO espacio(nombreEspacio, regimenTemperatura, icono, id_usuario) VALUES (?,?,?,?) ";
		try {
			pr= con.prepareStatement(query);
			pr.setString(1, sp.getNombreEspacio());
			pr.setString(2, sp.getRegimenTemperatura());
			
			/*	//que cambie el icono x defecto segun regimen tde temperatura del espacio
			if(sp.getRegimenTemperatura().equalsIgnoreCase("ambiente")) {
				pr.setString(3, ("/img/icon/cestaVerdura.png"));
			}else if(sp.getRegimenTemperatura().equalsIgnoreCase("frio")) {
				pr.setString(3, ("/img/icon/fridge32.png"));
			}else if(sp.getRegimenTemperatura().equalsIgnoreCase("congelado")) {
				pr.setString(3, ("/img/icon/congelador32.png"));
			}
			*/
			pr.setString(3, sp.getIcono());
			pr.setInt(4, sp.getId_usuario());	
			
			
			System.out.println(pr.toString());
			
			int filas = pr.executeUpdate();
			
			pr.close();
			
			System.out.println("Espacio insertado");
			
		} catch (SQLException e) {
			System.out.println("Error tryCatch: error al insertar espacio");
			e.getMessage();
			e.printStackTrace();
		}//fin try/catch
		
		
		/**listar espacios para comprobar insercionxconsola***/
		
		try {
			Statement st = con.createStatement();
			String consul = "SELECT * FROM espacio";
			
			rs = st.executeQuery(consul);
			System.out.println("TABLA ESPACIOS");
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t" +rs.getString(2)+"\t" +rs.getString(3)+"\t"+rs.getInt(4));
			}//fin while rs.next()
			

		} catch (SQLException e) {
			
			System.out.println("Error excepcion listarUsuarios"+e.getMessage());
		}
		
	}//fin isertarEspacio()
	

}//fin daoSpace
