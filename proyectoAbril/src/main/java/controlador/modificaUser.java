package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import modelo.User;



/**
 * Servlet implementation class modificaUser para modificar los datosd del usuario 
 * en al database recogiendo los datos del form modificarUsuario.html, guardamos y 
 * enviamos a la lista actualizada 
 */
public class modificaUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificaUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);TODO Auto-generated method stub
		//Variables, recogen datos del formulario
		String id_usuario = request.getParameter("id");
		String nombreUsuario = request.getParameter("nombre");
		String emailUsuario =request.getParameter("email");
		String passwordUsuario = request.getParameter("passUser");
		String imagenUsuario = request.getParameter("imgUser");
		String permiso = request.getParameter("esAdmin");
		String descripcionUsuario = request.getParameter("descripcionPerfil");
		int idInt = Integer.parseInt(id_usuario);
		int permisoInt = Integer.parseInt(permiso);
		
		User ue = new User(idInt, nombreUsuario, emailUsuario, imagenUsuario, permisoInt, descripcionUsuario);
		
		
		//ue.setId(idInt);
		//ue.setEsAdmin(permisoInt);
		System.out.println("datos que recojo para actualizar: "+ue.toString());
		
		ue.actualizador();
		System.out.println("actualizado por servlet modificaUser");
		
		
		PrintWriter pw = response.getWriter();
		pw.print("imprimimos en html lo que queremos enviar a la db pero sale mal \n");
		pw.print(ue);
		
		//response.sendRedirect("gestionAdmin.html");
		
		
		
		
		
		
		
	}

}
