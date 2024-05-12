package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class contactoControlador
 */
public class contactoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public contactoControlador() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Variables recogidas del formulario de Contacto
		String nombreUsuario = request.getParameter("nameUser");
		String mailUsuario = request.getParameter("mailUser");
		String departamento = request.getParameter("depart");
		String asunto = request.getParameter("asunto");
		String txtUser = request.getParameter("txtUser");


	}// fin doPost

}
