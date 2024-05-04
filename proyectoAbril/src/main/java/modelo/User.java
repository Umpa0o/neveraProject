package modelo;

import java.sql.SQLException;

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
	private int esAdmin = 1;		//Por defecto el usuario es 1 usuario normal
	private String imgUser;
	private String fechaNacimiento;
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


	/**Constructor User con todos los atributos de la clase User*/
	public User(int id, String nombreUser, String email, String passwordUser, int esAdmin, String imgUser,
			String fechaNacimiento, String descripcionPerfil) {
		super();
		this.id = id;
		this.nombreUser = nombreUser;
		this.email = email;
		this.passwordUser = passwordUser;
		this.esAdmin = esAdmin;
		this.imgUser = imgUser;
		this.fechaNacimiento = fechaNacimiento;
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
	
	/**Metodo getFechaNacimiento que recoge la fecha de nacimiento introducida por el usuario
	 * @return fechaNacimiento String con la fecha de nacimiento del usuario, por la curiosidad del uso de la aplicación*/
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**Metodo setFechaNacimiento donde el atributo apunta asi mismo
	 * @param String fechaNacimiento  recoge en una cadena de caracteres la fecha de nacimiento del user*/
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	
	/**Metodo toString de la clase User*/

	@Override
	public String toString() {
		return "User [id=" + id + ", nombreUser=" + nombreUser + ", email=" + email + ", passwordUser=" + passwordUser
				+ ", esAdmin=" + esAdmin + ", imgUser=" + imgUser + ", fechaNacimiento=" + fechaNacimiento
				+ ", descripcionPerfil=" + descripcionPerfil + "]";
	}
	
	 
	
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
			
		}//fin insertar
	
	
	

}//fin clase User


/**
 * 
 * */
 