package dao;

import java.sql.*;
import java.util.Properties;

/**clase conectaDB Esta clase contiene las variables y metodos
 * para conectar con la base de datos nevera mySQL 
 * @author Patricia Fedriani
 * @version 28/04/2024 v1
 * 
 * */

public class conectaDB {
	/**<p>CONSTANTE urlJD de tipo String va a ser STATIC por que es un atributo estático de la clase, por que se extiende fuera del objeto
	 *  y es común para todos los objetos instanciados de ese tipo</p>
	 * <p>FINAL por que será constante y no cambiará, como en este caso la dirección del servidor de la DataBase </p>
	 * */
	public static final String urlJDBC = "jdbc:mysql://localhost:3306/nevera";
	
	/**<p>variable instance tipo Connection, clase que manejamos gracias al java data base connectivity(JDBC) </p>
	 * <p>Nos sirve para establecer una conexión/sesión con una database específica</p>
	 * @see Documentación clase Connection https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html
	 * **/
	public static Connection instance = null;
	
	/** Metodo getConexion
	 * Queremos generar una instancia que sera igual al Driver Manager y la url de ls db a la que se conecta, usuario y contraseña
	 * Si la instancia es null que se conecte y devuelva la instancia
	 * @throws SQLException
	 * @see clase Properties  para representar una lista de propiedades persistentes
	 * https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html
	 * 
	 * 
	 * */
	
	public static Connection getConexion() {
		try {
			if(instance == null) {
				Properties props = new Properties();
				props.put("user", "root");
				props.put("password", "");
				props.put("charset", "UTF-8");
				
				instance = DriverManager.getConnection(urlJDBC,props);
				System.out.println("Conectado a la DB nevera");
			}
			
			     
		} catch (SQLException e) {
			System.out.println("Excepción: Error al conectarse a la DB nevera"+e.getMessage());
			e.getMessage();

		}//fin try/catch
	
		//si la instancia no es null devuelve la instancia existente
		return instance;
		
	}//fin getConexion
	

}// fin clase conectaDB
