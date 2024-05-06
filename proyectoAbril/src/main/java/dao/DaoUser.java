package dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.User;
/**CLASE DAO: clase reservada para todas las acciones que tiene 
 * un objeto con iteración en una base de datos. Tienen persistencia. DAO -> InsertarUsuario
 * 
 * clase TAD: clases creadas para gestionar colecciones de objeto.Ej un ArrayList. TAD -> ListarUsuarios
 * 
 * */


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
		 * @param Objeto ue del tipo User
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
			
		}//fin metodo insertarUser()
		
		/**metodo listarUsuarios() Crea una coleccion de datos tipo User a null
		 * 
		 * */
		
		public ArrayList<User> listarUsuarios() {
			ArrayList<User> listaUe=null;
			try {
				
				String consulta = "SELECT * FROM usuario";
				PreparedStatement pr =con.prepareStatement(consulta);
				ResultSet rs = pr.executeQuery();	
				
				while(rs.next()) {
					//si es null lo creamos y si no le añadimos información para no tener que resetearlo
					if(listaUe==null) {
						listaUe = new ArrayList<User>();
					}//fin if
					
					listaUe.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
					
				}//fin while
				
			}catch(SQLException ex) {
				ex.getMessage();
				
			}//fin try/catch
			
			return listaUe;
			
		}// fin listarUsuarios()
		
		
		/**Método listarUeJson() nos comunicaremos con el cliente con json, diferenciando el front del back
		 * 	hacemos que ntro servlet cree un JSON para devolverle al cliente.
		 * 	Para Java el contenido de un json es tipo String
		 * 
		 * @build gson.google
		 * 
		 * **/
		public String listarUeJson() {
			//generamos cadena vacia
			String json ="";
			//creamos libreria/objeto json q nos ayudara a convertir el arrayList en json
			Gson gson = new Gson();
			
			json = gson.toJson(this.listarUsuarios());
			
			return json;
			
			
			
		}// fin listarUeJson()
		
		
		
}//fin clase DaoUser
