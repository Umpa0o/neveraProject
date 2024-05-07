package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
	
	//public static Connection con = null;//borrar con la continuacion

    public userManage() {
        super();
        
    }
	/**doGet lo que recibe es el listar usuariosJson cuando recibe llamada xget
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		int opcion = Integer.parseInt(request.getParameter("op")) ;
		
		if(opcion == 2) {
			/**EDITAR usuarios con el get**/
			int id = Integer.parseInt(request.getParameter("id")) ;
			//editar
			User u = new User();
			try {
				u.actualizar(id);
				System.out.println("entra a actualizar");
				System.out.println(u.toString());
				out.print(u.darJson());
				System.out.println(u.darJson());
			} catch (SQLException e) {

				e.getMessage();
			}//fin try/catch
			
		}else if(opcion == 1){
			//listar
			DaoUser daoUs;
			try {
				
				daoUs = new DaoUser();
				out.print(daoUs.listarUeJson());
				
			} catch (SQLException e) {

				e.getMessage();
			}//fin try/catch
			
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
//		
		/**LISTAR usuarios con el get**/
		/*DaoUser daoUs;
		try {
			
			daoUs = new DaoUser();
			out.print(daoUs.listarUeJson());
			
		} catch (SQLException e) {

			e.getMessage();
		}//fin try/catch
		*/
		
	}//fin doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//	int id = Integer.parseInt(request.getParameter("id"));
		try {
		String nombreUser = request.getParameter("nameUser");
		String emailUser =request.getParameter("mailUser");
		String pswUser = request.getParameter("passUser");

		
		//User(String nombreUser, String email, String passwordUser) 
		User ue = new User(nombreUser, emailUser, pswUser);
		System.out.println(ue.toString());
		
		DaoUser dao;
		
			//ue.insertar();
			dao = new DaoUser();
			dao.insertarUser(ue);
			
			System.out.println("Usuario insertado");
		} catch (SQLException e) {
			System.out.println("Error al insertar");
			e.getMessage();
		}
		
		response.sendRedirect("gestionAdmin.html");
		
		///////////////////////////////////////////////////////////////////////////////////////
		/*****************BORRAR A CONTINUACION con la variable global Connection con****************/
		/*con = conectaDB.getConexion();
		ResultSet rsLis=null;
		try {
			Statement st = con.createStatement();
			String query = "SELECT * FROM usuario";
			
			rsLis = st.executeQuery(query);
			System.out.println("TABLA USUARIOS");
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<div>");
			pw.println("<table>");
			pw.println("<tr><th></th></tr>");
			
			
			while(rsLis.next()) {
				System.out.println(rsLis.getString(1)+"\t" +rsLis.getString(2)+"\t" +rsLis.getString(3)+"\t" +
			rsLis.getString(4)+"\t" +rsLis.getString(5)+"\t" +rsLis.getString(6)+"\t" +rsLis.getString(7)+"\t" 
			+rsLis.getString(8)+"\t" +rsLis.getString(9));
				
				pw.println("<tr><td>"+rsLis.getString(1)+"</td>");
				pw.println("<td>"+rsLis.getString(2)+"</td>");
				pw.println("<td>"+rsLis.getString(3)+"</td>");
				pw.println("<td>"+rsLis.getString(4)+"</td>");
				pw.println("<td>"+rsLis.getString(5)+"</td>");
				pw.println("<td>"+rsLis.getString(6)+"</td>");
				pw.println("<td>"+rsLis.getString(7)+"</td>");
				pw.println("<td>"+rsLis.getString(8)+"</td>");
				pw.println("<td>"+rsLis.getString(9)+"</td>");
				pw.println("</tr>");
			}
			

			
			pw.println("</table>");
			pw.println("</div>");
			

		} catch (SQLException e) {
			
			System.out.println("Error excepcion listarUsuarios"+e.getMessage());
		}
		*/
		
		///////////////////////////////////////////////////////////////////////////////////////

		
		
		
	}//fin doPost

}//fin Servlet userManage
