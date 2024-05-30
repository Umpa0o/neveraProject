package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Space;
//clase DAO para el tipo objeto Space
/**CLASE DAO: clase reservada para todas las acciones que tiene 
 * un objeto con iteración en una base de datos. En este caso tipo Objeto Space
 * @author Patricia Fedriani
 * @version  02/05/2024 v1
 * */

public class DaoSpace {
	//atributo estatico tipo connection
	public static Connection con = null;
	
	//constructor con conexion a DB
	public DaoSpace() throws SQLException {		
		this.con = conectaDB.getConexion();
 		
	}//fin constructor 
	
	//MÉTODOS CRUD
	
	/***insertarEspacio en DB metodo para insertar un espacio en la database nevera MySQL
	 * @param sp objeto de tipo Space
	 * */
	public void insertarEspacio(Space sp) {
		PreparedStatement pr;
		ResultSet rs=null;
		
		String query = "INSERT INTO espacio(nombreEspacio, regimenTemperatura, icono, id_usuario) VALUES (?,?,?,?) ";
		try {
			pr= con.prepareStatement(query);
			pr.setString(1, sp.getNombreEspacio());
			pr.setString(2, sp.getRegimenTemperatura());
			
			//que cambie el icono x defecto segun regimen tde temperatura del espacio
			if(sp.getRegimenTemperatura().equalsIgnoreCase("ambiente")) {
				pr.setString(3, ("/img/icon/cestaVerdura.png"));
			}else if(sp.getRegimenTemperatura().equalsIgnoreCase("frio")) {
				pr.setString(3, ("/img/icon/fridge32.png"));
			}else if(sp.getRegimenTemperatura().equalsIgnoreCase("congelado")) {
				pr.setString(3, ("/img/icon/congelador32.png"));
			}
			/**/
			//pr.setString(3, sp.getIcono());
			pr.setInt(4, sp.getId_usuario());	
			
			
			System.out.println(pr.toString());
			
			int filas = pr.executeUpdate();
			
			//pr.close();
			
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
			System.out.println("id \t  nombre \t  regiment \t icono \t id_usuario");
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t" +rs.getString(2)+"\t" +rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5));
			}//fin while rs.next()
			

		} catch (SQLException e) {
			
			System.out.println("Error excepcion listarUsuarios"+e.getMessage());
		}//fin try/catch
		
	}//fin insertarEspacio()
	
	/**metodo listarEspacios() recoge en arrayList de tipo Space los espacios del usuario
	 * @param id_usuario de tipo int, recibe el id de usuario para poder trabajar con el en
	 * la consulta a la base de datos
	 * */
	public ArrayList<Space> listarEspacios(int id_usuario){
		ArrayList<Space> listaSpace = null;
		ResultSet rs;
		
		String lol = "SELECT * FROM espacio WHERE id_usuario=?";
		try {
			PreparedStatement pre = con.prepareStatement(lol);
			
			//establecemos el idUsuario del que queremos mostrar los espacios
			pre.setInt(1, id_usuario);
			
			rs = pre.executeQuery();
			System.out.println("comprobamos consulta: "+lol);
			
			while(rs.next()) {
				//si no existe lo crea, que si no me sobreescribe(pisa la información anterior)
				if (listaSpace == null) {
					listaSpace = new ArrayList<Space> ();
				}//fin if
				
				//recoge id_espacio, nombreEspacio, regimenTemperatura, icono, id_usuario
				listaSpace.add(new Space(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			
			}//fin while
			
			
		} catch (SQLException e) {
			System.out.println("Error tryCatch DAO listarEspacios");
			e.getMessage();
			e.printStackTrace();
			
		}//fin tryCatch
		
		System.out.println(listaSpace);
		
		return listaSpace;
		
		
	}//fin listarEspacios()
	
	/**metodo espacioJson que devuelve un objeto de tipo String
	 * es un método que emplea json para lista respacios
	 * @param id_usuario de tipo int, recibe el numero entero que identifica al usuario 
	 * para saber a quien listarle los espacios
	 * 
	 * */
	
	public String espacioJson(int id_usuario) {
		String paco = "";
		//referencia a la libreria que hemos cargado de json de google
		Gson gson = new Gson();
		
		//dentro del string que meta lo que devuelva lo que he creado con el metodo toJson 
		paco = gson.toJson(this.listarEspacios(id_usuario));
		
		return paco;
		
		
	}// fin listar espacios en json()
	

}//fin daoSpace
