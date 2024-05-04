package dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.User;


public class DaoUser {
	//Patron Singelton
		//atributo estatico tipo connection
		public static Connection con = null;
		
		/**Constructor de DaoUser
		 * cuando esta clase se instancia se conecta, usando el propio constructor
		 * @throws SQLException
		 * */
		public DaoUser() throws SQLException {
			
			this.con = conectaDB.getConexion();
	 		
		}//fin constructor 
		
		
		
		/**Metodo para insertarUsuarios en la database
		 * @param ue del objeto User
		 * @throws SQLException
		 * */
		public void insertarUser(User ue) {
			String userDefault = "1";

			try {
				/*String sql = "INSERT INTO usuario (nombreUsuario, emailUsuario, passwordUsuario, permiso) "
					+ "VALUES ('EjemploInsertando','email@insertado.com','contraseña',1) ";*/
				
				PreparedStatement pst;
				ResultSet rs;
				/**FUTURA IMPLEMENTACIÓN*/
				//if
				//si coincide el nombre, id o el email no se podrá dar de alta el usuario
				
				//else
				//Precompilada sin id para insertar
				/*String sql = "INSERT INTO usuario (nombreUsuario, emailUsuario, passwordUsuario, permiso) "
							+ "VALUES (?,?,?,?) ";*/
				String sql = "INSERT INTO usuario (nombreUsuario, emailUsuario, passwordUsuario, permiso) "
						+ "VALUES (?,?,?, ?) ";
				
				pst= con.prepareStatement(sql);
				pst.setString(1, ue.getNombreUser());
				pst.setString(2, ue.getEmail());
				pst.setString(3, ue.getPasswordUser());
				
				
				pst.setInt(4, ue.getEsAdmin());


				System.out.println("Usuario insertado correctamente");
				
				int filas = pst.executeUpdate();
				
				//Cerramos el PreparedStatement
				pst.close();
				
			} catch (SQLException e) {
				System.out.println("catch:Error al insertar usuario");
				e.getMessage();
			} //fin try catch
			
			ResultSet rsLis=null;
			try {
				Statement st = con.createStatement();
				String query = "SELECT * FROM usuario";
				
				rsLis = st.executeQuery(query);
				System.out.println("TABLA USUARIOS");
				
				while(rsLis.next()) {
					System.out.println(rsLis.getString(1)+"\t" +rsLis.getString(2)+"\t" +rsLis.getString(3)+"\t" +
				rsLis.getString(4)+"\t" +rsLis.getString(5)+"\t" +rsLis.getString(6)+"\t" +rsLis.getString(7)+"\t" 
				+rsLis.getString(8)+"\t" +rsLis.getString(9));
				}
				
	
			} catch (SQLException e) {
				
				System.out.println("Error excepcion listarUsuarios"+e.getMessage());
			}
			
		}//fin metodo insertarUser
		
		/**metodo listar
		 * 
		 * */
		
		public void listarUsuarios() {
			
			ResultSet rsLis=null;
			try {
				Statement st = con.createStatement();
				String query = "SELECT * FROM usuario";
				
				rsLis = st.executeQuery(query);
				System.out.println("TABLA USUARIOS");
				
				while(rsLis.next()) {
					System.out.println(rsLis.getString(1)+"\t" +rsLis.getString(2)+"\t" +rsLis.getString(3)+"\t" +
				rsLis.getString(4)+"\t" +rsLis.getString(5)+"\t" +rsLis.getString(6)+"\t" +rsLis.getString(7)+"\t" 
				+rsLis.getString(8)+"\t" +rsLis.getString(9));
				}
				
	
			} catch (SQLException e) {
				
				System.out.println("Error excepcion listarUsuarios"+e.getMessage());
			}
			
		}
		
}//fin clase DaoUser
