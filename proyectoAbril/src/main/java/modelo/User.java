package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoUser;


/** Clase Usuario. Recoge los datos de los usuarios en atributos privados
 * @author patfe
 * @version 12/04/2024A v 1
 * 
 * */
public class User {
	/**ATRIBUTOS DE LA CLASE USUARIO*/
	private int id;
	private String nombreUser;
	private String email;
	private String passwordUser;
	//private String imgUser;
	private String imgUser = "img_default.png";		//Por defecto, idem imagen de usuario
	//private int esAdmin;
	private int esAdmin = 1;		//Por defecto el usuario es 1 usuario normal
	
	//private String fechaNacimiento;
	private String descripcionPerfil;
	
	/***************************************/
	
	/**CONSTRUCTORES*/
	
	/**Constructor por defecto*/
	public User() {}
	
	/**Constructor User sin id entre otros, solo tiene los atributos necesarios para el registro en la aplicación*/
	public User(String nombreUser, String email, String passwordUser) {
		super();
		this.nombreUser = nombreUser;
		this.email = email;
		this.passwordUser = passwordUser;
		//this.imgUser = imgUser;

	}//fin contructor User para el Registro
	
	/**Contructos para administrados que modificaUsuarios, sin pisar la contraseña*/
	public User(int id, String nombreUser, String email, String imgUser, int esAdmin, String descripcionPerfil) {
		super();
		this.id = id;
		this.nombreUser = nombreUser;
		this.email = email;
		this.imgUser = imgUser;
		this.esAdmin = esAdmin;
		this.descripcionPerfil = descripcionPerfil;
	}
	
	
	/**  Constructor para usuario que pueda actualizarse asi  mismo su nombre de usuario, la img y la descripción***/
	
	public User(String nombreUser, String imgUser, String email, String descripcionPerfil) {
		super();
		this.nombreUser = nombreUser;
		this.email = email;
		this.imgUser = imgUser;
		this.descripcionPerfil = descripcionPerfil;
	}



	/**Constructor User con todos los atributos de la clase User*/
	public User(int id, String nombreUser, String email, String passwordUser, String imgUser, int esAdmin, 
			 String descripcionPerfil) {
		super();
		this.id = id;
		this.nombreUser = nombreUser;
		this.email = email;
		this.passwordUser = passwordUser;
		this.imgUser = imgUser;
		this.esAdmin = esAdmin;
		
		//this.fechaNacimiento = fechaNacimiento;
		this.descripcionPerfil = descripcionPerfil;
		
	}//fin constructor User completo
	
	
	

	/***************************************/

	/**GETTERS & SETTERS*/
	
	/**Metodo getId que devuelve el id del usuario
	 * @return id Numero entero(int) que identifica al usuario*/
	public int getId() {
		return id;
	}
	
	/**Metodo setId el atributo apunta asi mismo y se actualiza
	 * @param int id  recoge el entero de la id del usuario*/
	public void setId(int id) {
		this.id = id;
	}
	
	/**Metodo getNombreUser que devuelve el nombre del usuario
	 * @return nombreUser Cadena de texto(String) con el nombre del usuario*/
	public String getNombreUser() {
		return nombreUser;
	}
	
	/**Metodo setNombreUser el atributo apunta asi mismo
	 * @param String nombreUser  recoge los caracteres del nombre del usuario*/
	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}
	
	
	/**Metodo getEmail que recoge el email del usuario
	 * @return email Cadena de caracteres(String) con el correo electrónico del usuario*/
	public String getEmail() {
		return email;
	}
	
	/**Metodo setEmail el atributo apunta asi mismo
	 * @param String email  recoge los caracteres del email del usuario*/
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**Metodo getPasswordUser que recoge la contraseña del usuario
	 * @return passwordUser Cadena de caracteres(String) con la contraseña del usuario*/
	public String getPasswordUser() {
		return passwordUser;
	}
	
	/**Metodo setPasswordUser donde el atributo apunta asi mismo
	 * @param String passwordUser  recoge los caracteres de la contraseña del usuario*/
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	
	/**Metodo getEsAdmin que recoge el tipo de permiso del usuario sobre la aplicación
	 * @return esAdmin Numero entero(int) que representa el permiso del usuario*/
	public int getEsAdmin() {
		return esAdmin;
	}
	
	/**Metodo setEsAdmin donde el atributo apunta asi mismo
	 * @param int esAdmin  recoge el valor entero del permiso definido para el usuario*/
	public void setEsAdmin(int esAdmin) {
		this.esAdmin = esAdmin;
	}
	
	/**Metodo getImgUser recoge la imagen que el usuario elige para su perfil(nombre.extension) para meter
	 * la imgUser a traves de una variable dentro de un <img id="imgPerfilUsuario" href="img/'+imgUser+'">
	 * @return imgUser Cadena de caracteres con el nombre y extensión (imgUser=> dukeJava.png)
	 * */
	public String getImgUser() {
		return imgUser;
	}
	
	/**Metodo setImgUser donde el atributo apunta asi mismo
	 * @param String imgUser  recoge en una cadena de caracteres nombre y extension de la img*/
	public void setImgUser(String imgUser) {
		this.imgUser = imgUser;
	}
	

	
	/**Metodo getDescripcionPerfil que recoge la fecha de nacimiento introducida por el usuario
	 * @return descripcionPerfil String Cadena de caracteres que el usuario puede añadir a su perfil informando de su vida*/
	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}
	
	/**Metodo setDescripcionPerfil donde el atributo apunta asi mismo
	 * @param String descripcionPerfil  recoge en una cadena de caracteres la descripcion del user que who cares?*/
	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}
	
	/***************************************/
	
	 
	
	/***************************************/
	
	/**METODOS*/
	
	/**Metodo para INSERTAR usuarios en la database
	 Crea una nueva instancia de la clase DAOUsuario 
	 * */
	public void insertar() throws SQLException {
			//Esta sección va por patrón !*
			/**Redundancia: como norma en POO cada objeto debe ser dueño de sus acciones, aunque sea redundante
			 * 	Si el usuario/User(dueño) es el que se inserta, es él mismo quien debe ejecutar esa acción
			 * */
			DaoUser dao = new DaoUser(); 
			dao.insertarUser(this);
			
			// !* 
			
		}//fin insertar()
	
	/**Metodo para actualizar los datos de USuarios en la database, creando instancia de la clase DAOUser
	 * 
	 * */
	
	public void actualizaUser(int id) throws SQLException{
		DaoUser dau = new DaoUser();
		User aux = dau.actualizaUser(id);
		
		this.setId(aux.getId());
		this.setNombreUser(aux.getNombreUser());
		this.setEmail(aux.getEmail());
		this.setPasswordUser(aux.getPasswordUser());
		this.setImgUser(aux.getImgUser());
		this.setEsAdmin(aux.getEsAdmin());
		this.setDescripcionPerfil(aux.getDescripcionPerfil());
		System.out.println(aux.toString());

		
	}//fin actualizar()
	
	/**Metodo para devolver los datos al front/html/lado del cliente en formato json
	 * 
	 * */
	
	public String darJson() {
		String json ="";
		Gson gson = new Gson();
		
		json = gson.toJson(this);
		
		return json;
		
		
	}//findarJson()
	
	//actualizador User
	public void actualizador() {
		try {
			DaoUser dao = new DaoUser();
			dao.actualizador(this);
			
		} catch (SQLException eh) {
			
			System.out.println("Error tryCatch actualizador modeloo User");
			eh.getMessage();
		}
		
	}//fin actualizador
	
	//actualizar perfilUser.html
	public void updatePerfil() {
		DaoUser dau;
		try {
			dau = new DaoUser();
			dau.updatePerfil(this);
			
		} catch (SQLException j) {
			System.out.println("Error tryCatch updatePerfil modelo User");
			j.getMessage();
			j.printStackTrace();
			
		} 
		
	}//fin updatePerfil()
	
	
	//borrarUSer
	public void borrar(int id) {
		
		try {
			DaoUser dao;
			dao = new DaoUser();
			dao.borrar(id);
			
		} catch (SQLException e) {
			System.out.println("Error borrar modelo User.java");
			e.getMessage();
		}
		
	}//fin borrar User
	
	/**	metodo logear() devuelve un booleano
	 * 
	 * @param	String password recibe un parámetro de cadena de texto con la contraseña del usuario
	 * 			<ul>Devuelve:
	 * 				<li><strong>true: </strong>  </li>
	 * 				<li><strong>false: </strong>  </li>
	 * 		
	 * 			</ul>
	 * */
	public boolean logear(String password) {
		
		boolean comprueba = false;
		
		try {
			DaoUser dao = new DaoUser();
			//comprueba la base de datos y si devuelve un objeto aux con sus datos, es decir, no es nulo pasa al if
			User aux = dao.logeando(this, (password));
			
			if(aux != null) {
				//al contener datos ponemos booleano a true
				comprueba = true;
				//si no es null contiene datos, asi que los sacamos
				this.setId(aux.getId());
				this.setNombreUser(aux.getNombreUser());
				this.setEmail(aux.getEmail());
				this.setEsAdmin(aux.getEsAdmin());
				System.out.println(aux.toString());
				
				
			}//fin if
					
			
		} catch (SQLException e) {
			System.out.println("Error tryCatch User.logear(pass)");
			e.getMessage();
		}//fin try/catch
			System.out.println(comprueba);
		return comprueba;
	}// fin logear()
	

	
	
	/***************************************/
	
	/**Metodo toString de la clase User*/

	@Override
	public String toString() {
		return "User [id=" + id + ", nombreUser=" + nombreUser + ", email=" + email + ", passwordUser=" + passwordUser
				+", imgUser=" + imgUser + ", esAdmin=" + esAdmin + ", descripcionPerfil=" + descripcionPerfil + "]";
	}
	
	 
	
	/***************************************/
	
	
	

}//fin clase User


/**
 * 
 * */
 