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
	
	public static Connection con = null;

    public userManage() {
        super();
        
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Mensaje default del do GET::  Served at: ").append(request.getContextPath());
	}//fin doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
	//	int id = Integer.parseInt(request.getParameter("id"));
		
		String nombreUser = request.getParameter("nameUser");
		String emailUser =request.getParameter("mailUser");
		String pswUser = request.getParameter("password");
		//int permUser = Integer.parseInt(request.getParameter("permiso"));
		
	//	int isAdmin = Integer.parseInt(request.getParameter("permiso"));
		
		//User(String nombreUser, String email, String passwordUser) 
		User ue = new User(nombreUser, emailUser, pswUser);
		System.out.println(ue.toString());
		
		DaoUser dao;
		try {
			//ue.insertar();
			dao = new DaoUser();
			dao.insertarUser(ue);
			
			System.out.println("Usuario insertado");
		} catch (SQLException e) {
			System.out.println("Error al insertar");
			e.getMessage();
		}
		///////////////////////////////////////////////////////////////////////////////////////
		con = conectaDB.getConexion();
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
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		
		/*try {
			//si el elemento no tenia id
			if(id == "") {
				ue.insertar();
				System.out.println("Usuario insertado");
			} else {		
				int idCambiado = Integer.parseInt(id);
				ue.setId(idCambiado);
				System.out.println("Error al insertar");
	
			}//fin if/else id vacio, no existe en la DB
			
		}catch(SQLException e) {
			e.printStackTrace();
			e.getMessage();
			System.out.println("Error al insertar");
		}//fin try/catch
		
		*/
		
		
		
	}//fin doPost

}//fin Servlet userManage
