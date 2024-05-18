package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.User;
import dao.DaoUser;
import dao.conectaDB;

/**
 * Servlet implementation class userManage
 */
public class userManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Instanciamos la sessión para poder trabajar con ella
	HttpSession sesion;

    public userManage() {
        super();
        
    }
	/**doGet lo que recibe es el listar usuariosJson cuando recibe llamada xget
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//iniciamos la sesion pidiendosela al navegadorWebs
		//XX sesion = request.getSession();
		
		//variable que recoge el valor del campo id que agregamos comoa tributo ala sesion
		//XX int idSesion = Integer.parseInt((String)sesion.getAttribute("id"));
		//que es lo mismo que la función siguiente:
		//int idSesion3 = (int) sesion.getAttribute("id");
		
		/**si el id es diferente a cero sq es usuario registrado*/
		
		
		PrintWriter out = response.getWriter();
		
		//Procesos de edición, insertado, borrado y buscar
		int opcion = Integer.parseInt(request.getParameter("op")) ;
		System.out.println("estamos en la opcion  "+opcion);
		
		if(opcion == 2) {
			/**EDITAR usuarios con el get**/
			int id = Integer.parseInt(request.getParameter("id")) ;
			System.out.println("Queremo actualizar el id: "+id);
			
			User us = new User();
			System.out.println(us);
			try {
				us.actualizaUser(id);
				System.out.println(us);
				System.out.println("entra a buscar para actualizar usrManage(no actualiza solo recoge)");
				
				//out.print("aqui"+us.darJson());
				out.print(us.darJson());
				System.out.println(us.darJson());
				
				
				
			} catch (SQLException e) {

				e.getMessage();
			}//fin try/catch
			
		}else if(opcion == 1){
			//Opcion 1: listar
			DaoUser daoUs;
			try {
				
				daoUs = new DaoUser();
				out.print(daoUs.listarUeJson());
				System.out.println("Estamos en la opcion 1");
				
				
			} catch (SQLException e) {

				e.getMessage();
			}//fin try/catch
			
		}else if(opcion == 3){
			//Opcion 3: borrar
			int id = Integer.parseInt(request.getParameter("id")) ;
			try {
				DaoUser daoUsuario;
				daoUsuario = new DaoUser();
				daoUsuario.borrar(id);
								
				
			}catch(SQLException ex) {
				System.out.println("error borrar userManage");
				ex.getMessage();
			}
			
			
		}else if(opcion == 4){
			//Opcion 4: buscar
			int tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
			System.out.println("aqui=");
			
			try {
				DaoUser die = new DaoUser();
				out.print(die.listarTipoJson(tipo));
			} catch (SQLException e) {
				
				e.getMessage();
			}
			//como hablamos con una coleccion de objetos Usuario, genermaos metodo dentro del dao, con el out devolvemos el json con un metodo
			//out.print();
			
			
			
			
		}//fin ifelse
		
		//switch para el panelAdministrador para borrar y editar users
		/*switch(opcion) {
			case 1:
				System.out.println("Listar Usuarios: ");
				
				//////**LISTAR usuarios con el get**
				DaoUser daoUs;
				try {
					
					daoUs = new DaoUser();
					out.print(daoUs.listarUeJson());
					
				} catch (SQLException e) {

					e.getMessage();
				}//fin try/catch
				
				
				break;
			//****fin case1 listarUsuario
		
			case 2:
				System.out.println("Editar Usuario: "+id);
				
				if(opcion == 2) {
					User u = new User();
					try {
						u.actualizar(id);
					} catch (SQLException e) {

						e.getMessage();
					}//fin try/catch
					
				}//fin if
				
			
				break;
			//***fin case2 editarUsuario
				
			case 3:
				System.out.println("Borrar Usuario: "+id);
				break;
			//****fin case3 borrarUsuario
				
			default:
				break;
		}//fin switch
		
		**/

		
	}//fin doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//	int id = Integer.parseInt(request.getParameter("id"));
			String nombreUser = request.getParameter("nameUser");
			String emailUser =request.getParameter("mailUser");
			String pswUser = request.getParameter("passUser");
			String id = request.getParameter("id");
		try {
			
		
			//User(String nombreUser, String email, String passwordUser) 
			User ue = new User(nombreUser, emailUser, pswUser);
			System.out.println(ue.toString());
			ue.insertar();
			System.out.println("Usuario insertado");
				
		/**dao = new DaoUser();
			dao.insertarUser(ue);
			
				
			if(id == "") {
				ue.insertar();
				System.out.println("Usuario insertado");
			}else {
				System.out.println("peta aqui");*/
				//int idInt = Integer.parseInt(id);
				//ue.setId(idInt);
				//ue.actualizador();
				//response.sendRedirect("gestionAdmin.html");
			//}//fin ifelse
		
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar");
			e.getMessage();
		}
		//mandamos a la página html de gestion administrador
		response.sendRedirect("gestionAdmin.html");
	
		
	}//fin doPost
	

}//fin Servlet userManage
