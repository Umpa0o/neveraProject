package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
 * CLASE TAD: clases creadas para gestionar colecciones de objeto.Ej un ArrayList. TAD -> ListarUsuarios
 * 
 * @author Patricia Fedriani
 * @version  28/04/2024 v1
 * */


public class DaoUser {
	//Patron Singelton¿?
		//atributo estatico tipo connection
		public static Connection con = null;
		
		/**Constructor de DaoUser
		 * cuando esta clase se instancia se conecta, usando el propio constructor DaoUser()
		 * @throws SQLException Se lanza si se generan errores al conectar/acceder a la base de daatos
		 * */
		public DaoUser() throws SQLException {
			
			this.con = conectaDB.getConexion();
	 		
		}//fin constructor 
		
		
		
		/**Metodo insertarUsuarios() hace consulta para insertar en la DB
		 * los datos recogidos del formulario de registro altaUser.html
		 * @param Objeto ue del tipo User
		 * 
		 * */
		public void insertarUser(User ue) {
			
			try {
				/*String sql = "INSERT INTO usuario (nombreUsuario, emailUsuario, passwordUsuario, permiso) "
					+ "VALUES ('EjemploInsertando','email@insertado.com','contraseña',1) ";*/
				
				PreparedStatement pst;
				ResultSet rs;
				/**FUTURA IMPLEMENTACIÓN*/
				//if
				//si coincide el nombre, id o el email no se podrá dar de alta el usuario
				/** SELECT correo, nombre, id FROM usuario WHERE correo=? OR nombre=? OR id=?   **/
				//else
				//Precompilada sin id para insertar
				/*String sql = "INSERT INTO usuario (nombreUsuario, emailUsuario, passwordUsuario, permiso) "
							+ "VALUES (?,?,?,?) ";*/
				String sql = "INSERT INTO usuario (nombreUsuario, emailUsuario, passwordUsuario, permiso) "
						+ "VALUES (?,?,?, ?) ";
				
				pst= con.prepareStatement(sql);
				pst.setString(1, ue.getNombreUser());
				pst.setString(2, ue.getEmail());
				/*//pasamos la contraseña a MD5
				String hasPass =(miMD5(ue.getPasswordUser()));
				pst.setString(3, (hasPass));*/
				
				pst.setString(3,ue.getPasswordUser());
				pst.setInt(4, ue.getEsAdmin());
				System.out.println(pst.toString());
	
				
				int filas = pst.executeUpdate();
				
				System.out.println("Usuario insertado correctamente");
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
					miMD5(rsLis.getString(4))+"\t" +rsLis.getString(5)+"\t" +rsLis.getString(6)+"\t" +rsLis.getString(7)+"\t" 
				+rsLis.getString(8)+"\t" +rsLis.getString(9));
				}
				
	
			} catch (SQLException e) {
				
				System.out.println("Error excepcion listarUsuarios"+e.getMessage());
			}
			
		}//fin metodo insertarUser()
		
		/**metodo listarUsuarios() Crea una coleccion de datos(ArrayList)de tipo User 
		 * la inicializamoa a null, si es null creamos el ArrayList y si no le
		 * añadimos la información nombreArray.add(nuevo objeto Usuario)
		 * @throws SQLException lanza expeción si hay error con la database
		 * 
		 * */
		
		public ArrayList<User> listarUsuarios() throws SQLException {
			//Iniciamos la coleccióna  null
			ArrayList<User> listaUe=null;
			ResultSet rs;
			
			String consulta = "SELECT * FROM usuario";
			PreparedStatement sta =con.prepareStatement(consulta);
			rs = sta.executeQuery();	
			System.out.println(consulta);

			while(rs.next()) {
				//si es null lo creamos y si no le añadimos información para no tener que resetearlo
				if(listaUe == null) {
					listaUe = new ArrayList<User>();
					//System.out.println("pasa por if");
					//System.out.println("Imprime array craeadovacio"+listaUe);
					
					
				}//fin if
				
				//protegemos las contraseñas que no se crearon cifradas al listarUsuarios
				listaUe.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), miMD5(rs.getString(4)), 
						rs.getString(5), rs.getInt(6), rs.getString(7)));
				
				//System.out.println("IMprime addd"+listaUe);
				
				//System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+ 
					//	rs.getInt(5)+ rs.getString(6)+ rs.getString(7)+ rs.getString(8)+ rs.getString(9));
				
			}//fin while
			System.out.println("ArrayList: "+listaUe);
			
			
			return listaUe;
			
		}// fin listarUsuarios()
		
		/**metodo listarTipo() Crea una coleccion de datos(ArrayList) tipo User 
		 * seleccionando por tipo segun buscador Admin. 
		 * Lleva el metodo miMD5 para que el admin no vea contraseñas
		 * @param int tipo es el numero de permiso del usuario para el buscador/filtro del admin 
		 * 
		 * */
		
		public ArrayList<User> listarTipo(int tipo) throws SQLException {
			//Iniciamos la colección a  null
			ArrayList<User> listaUa=null;
			ResultSet rs;
			
				String filtra = "SELECT * FROM usuario WHERE permiso=?";
				PreparedStatement pr =con.prepareStatement(filtra);
				pr.setInt(1, tipo);
				rs = pr.executeQuery();	
				System.out.println(filtra);

				while(rs.next()) {
					//si es null lo creamos y si no le añadimos información para no tener que resetearlo
					if(listaUa == null) {
						listaUa = new ArrayList<User>();
		
					}//fin if
					/** miMD5 en listar usuarios para proteger ontraseñas antiguas*/
					listaUa.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), miMD5(rs.getString(4)), 
							rs.getString(5), rs.getInt(6), rs.getString(7)));
					
					
				}//fin while
				System.out.println("ArrayList: "+listaUa);
			
			return listaUa;
			
			
		}// fin listarTipo()
		
		
		/**Método listarUeJson() nos comunicaremos con el cliente con json, diferenciando el front del back
		 * 	hacemos que ntro servlet cree un JSON para devolverle al cliente.
		 * 	Para Java el contenido de un json es tipo String
		 * @throws SQLException 
		 * 
		 * @build gson.google
		 * 
		 * **/
		public String listarUeJson() throws SQLException {
			//generamos cadena vacia
			String json ="";
			//creamos libreria/objeto json q nos ayudara a convertir el arrayList en json
			Gson gson = new Gson();
			
			//dentro del JSON queremos que guarde lo que capte el objeto GSON con el método .toJson() con lo q nos devuelva apuntanado a si mismo, listasUsuarios
			json = gson.toJson(this.listarUsuarios());
			System.out.println("por json");
			//System.out.println(json);
			
			return json;
			
					
		}// fin listarUeJson()
		
		/**Método listarTipoJson() listar por tipo sjon devuelve un objeto tipo String
		 * @param tipo int recoge el tipo de usuario a listar(tipo de rol/permiso/esAdmin)
		 * @build gson.google
		 * 
		 * **/
		public String listarTipoJson(int tipo) throws SQLException {

			String json ="";

			Gson gson = new Gson();
			
			
			json = gson.toJson(this.listarTipo(tipo));
			
			
			return json;
			
					
		}// fin listarUeJson()
		
		/** metodo actualizaUser devuelve un objeto tipo Usuario por la búsqueda en sql de su id
		 * 			este método se usa para obtener la información del usuario a actualizar y no la update en si
		 * @param int id como parametro el id del usuario
		 * */
		public User actualizaUser(int id) throws SQLException {
			String consulta = "SELECT * FROM usuario WHERE id_usuario=?";
			
			PreparedStatement pst = con.prepareStatement(consulta);
			//solo mandamos el id para seleccionar el usuario  /**3h23m 6abril*/
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			System.out.println(consulta);
			rs.next();
			User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
					rs.getString(5), rs.getInt(6), rs.getString(7));
			
			System.out.println(us.toString());
			System.out.println("obtenemos");
			
			return us;
					
			
		}// fin actualizaUser()
		
		/**MEtodo que actualiza el usuario con los datosd el form del front 
		 * @param ue recibe como parámetro un objeto de tipo usuario
		 * 
		 * */
		public void actualizador(User ue) {
			String queryUpdate = "UPDATE usuario SET nombreUsuario=?, emailUsuario=?, imagenUsuario=?, permiso=?, descripcionUsuario=? WHERE id_usuario=?";
			
			PreparedStatement pre;
			try {
				pre = con.prepareStatement(queryUpdate);
				//System.out.println(queryUpdate);
				
				
				pre.setString(1, ue.getNombreUser());
				pre.setString(2, ue.getEmail());
				pre.setString(3, ue.getImgUser());	
				pre.setInt(4, ue.getEsAdmin());
				pre.setString(5, ue.getDescripcionPerfil());
				pre.setInt(6, ue.getId());
				System.out.println("actualizadooor");
				System.out.println(pre);
								
				
				int filas = pre.executeUpdate();	
				pre.close();
				
			} catch (SQLException e) {

				e.getMessage();
				e.printStackTrace();
			}//fin tryCatch
			
		}//fin actualizador
		
		/**metodo para borrar usuario de la database a traves de seleccionar el id en el panel de administrador
		 * 	! 
		 * ?	y desde perfil usuario?!-> futura implementación, 
		 * el usuario puede darse de baja en la zona de su perfil
		
		 * @param int id recoge el numero entero que identifica al usuario a eliminar de la DB 
		 * 
		 * */
		public void borrar(int id) {
			String delsql = "DELETE FROM usuario WHERE id_usuario=?";
			
			try {
				PreparedStatement ps = con.prepareStatement(delsql);
				ps.setInt(1, id );
				
				int filas = ps.executeUpdate();	
				System.out.println(ps.toString());
				System.out.println("Usuario borrado");
				
				
			} catch (SQLException he) {
				
				System.out.println("Error tryCatch borrar usuario");
				he.getMessage();
			}//fin tryCatch
			
		}//fin borrar usuario
		
		/** metodo logeando() comprueba email y contraseña del usuario
		 * devolviendo un objeto de tipo usuario para poder iniciar sesión
		 * @param 	User u	parametro que especifica objeto usuario a buscar
		 * @param 	String password recoge la contraseña del usuario
		 * @throws SQLException 
		 * 
		 * */
		
		public User logeando(User u, String password) throws SQLException {
			String query = "SELECT * FROM usuario WHERE emailUsuario = ? AND passwordUsuario = ?";
			User aux =  null;

			PreparedStatement ps = con.prepareStatement(query);
			//queremos que como primer interrogante coja el email
			ps.setString(1, u.getEmail());
			//como segundo la contraseña del usuario
			ps.setString(2, (password));
			
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
				aux = new User(res.getInt(1), res.getString(2), res.getString(3), (res.getString(4)), 
					res.getString(5), res.getInt(6), res.getString(7));
					System.out.println(aux.toString());
			}//fin if
			
			//devuelve al usuario			
			return aux;	
			
		}//fin logeando()
		
		/**metodo para que el administrador actualize los datos del usuario 
		 * @param ue recibe como parámetro un objeto de tipo User
		 * el cual será el objeto Usuario modificado
		 * 
		 * **/
		public void updatePerfil(User ue) {
			String queryPerfil = "UPDATE usuario SET nombreUsuario=?, imagenUsuario=?, descripcionUsuario=? WHERE emailUsuario=?";
			
			PreparedStatement pre;
			try {
				pre = con.prepareStatement(queryPerfil);
				//System.out.println(queryPerfil);
				
				
				pre.setString(1, ue.getNombreUser());	
				pre.setString(2, ue.getImgUser());	
				pre.setString(3, ue.getDescripcionPerfil());
				pre.setString(4, ue.getEmail());
				
				System.out.println("perfil user DAOuser:¿? actualiza");
				System.out.println(pre);
								
				
				int filas = pre.executeUpdate();	
				pre.close();
				
			} catch (SQLException e) {

				e.getMessage();
				e.printStackTrace();
			}//fin try catch
			
		}//fin updatePerfil
		
		
		/**modificar string password a MD5			código de Otero
		 * Emplea la clase MessageDigest que aporta la funcionalidad de un algoritmo 
		 * que resume mensajes y generan un valor hash de longitud fija,
		 *  en este caso para las contraseña del usuario.
		 *  Soporta algoritmos MD5,sah-1 y SHA-256
		 *  @see JavaDoc MessageDigest-> https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html
		 *  @see Nombres de algoritmos cuando instanciamos la clase Message Direct-> https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#MessageDigest
		 * 
		 * */
		private static String miMD5(String inputPassword) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(inputPassword.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);

	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }//fin while
	            return hashtext;
	            
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }//fin tryCatch
	        
	    }//fin miMD5
		
	/*****/
		
}//fin clase DaoUser
