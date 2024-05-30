package modelo;

import java.sql.SQLException;

import dao.DaoSpace;
import dao.DaoUser;

/** Clase Space. Recoge los datos de los espacios 
 * @author patfe
 * @version 12/04/2024A v 1
 * 
 * */

public class Space {
	//VARIABLES
	private int id_espacio;
	private String nombreEspacio;
	private String regimenTemperatura;
	private String icono;
	private int id_usuario;
	
	//CONSTRUCTORES

	/**Constructor Space() por defecto
	 * */
	public Space() {
		super();
	}
	/**Constructor Space() sin parámetro id para insertar espacio
	 * @param nombreEspacio(String) 
	 * @param regimenTemperatura (String) 
	 * @param icono (String) 
	 * @param id_usuario (int) número entero que identifica inequivocamente al usuario propietario de ese espacio
	 * */
	public Space(String nombreEspacio, String regimenTemperatura, String icono, int id_usuario) {
		super();
		this.nombreEspacio = nombreEspacio;
		this.regimenTemperatura = regimenTemperatura;
		this.icono = icono;
		this.id_usuario = id_usuario;
	}
	
	/**constructor completo Space()
	 * @param id_espacio(int) recoge número entero de identificación único del objeto de tipo espacio
	 * @param nombreEspacio(String) cadena de caracteres que representa el nombre dado al espacio de almacenaje
	 * @param regimenTemperatura (String) cadena de caracteres que recoge el tipo de temperatura(ambiente, frio, congelado)
	 * @param icono (String) cadena de caracteres que presenta la ruta hacia una imagen para representar al espacio
	 * @param id_usuario (int) número entero que identifica inequivocamente al usuario propietario de ese espacio
	 * */
	public Space(int id_espacio, String nombreEspacio, String regimenTemperatura, String icono, int id_usuario) {
		super();
		this.id_espacio = id_espacio;
		this.nombreEspacio = nombreEspacio;
		this.regimenTemperatura = regimenTemperatura;
		this.icono = icono;
		this.id_usuario = id_usuario;
	}


	//GETTER && SETTERS

	public int getId_espacio() {
		return id_espacio;
	}

	public void setId_espacio(int id_espacio) {
		this.id_espacio = id_espacio;
	}

	public String getNombreEspacio() {
		return nombreEspacio;
	}

	public void setNombreEspacio(String nombreEspacio) {
		this.nombreEspacio = nombreEspacio;
	}

	public String getRegimenTemperatura() {
		return regimenTemperatura;
	}

	public void setRegimenTemperatura(String regimenTemperatura) {
		this.regimenTemperatura = regimenTemperatura;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	//MÉTODOS del objeto Space
	
	/**Insertar Espacio*/
	public void insertarEspacio() throws SQLException {
		//Esta sección va por patrón !*
		/**Redundancia: como norma en POO cada objeto debe ser dueño de sus acciones, aunque sea redundante
		 * 	Si el usuario/User(dueño) es el que se inserta, es él mismo quien debe ejecutar esa acción
		 * */
		DaoSpace das = new DaoSpace(); 
		das.insertarEspacio(this);
		

	}//fin insertarEspacio()
	
	/**Listar Espacios de un usuario
	public void listarEspacios(int id_usuario) {
		DaoSpace das = new DaoSpace();
		Space spa = das.listarEspacios(id_usuario); //ayy que es un arrayyy
		
	}//fin listarEspacios()
	*/
	
	
	

	//metodo 	.toString()
	@Override
	public String toString() {
		return "Space [id_espacio=" + id_espacio + ", nombreEspacio=" + nombreEspacio + ", regimenTemperatura="
				+ regimenTemperatura + ", icono=" + icono + ", id_usuario=" + id_usuario + "]";
	}
	
	
	
	
	
	
	
	
	

}//fin clase objeto Space
