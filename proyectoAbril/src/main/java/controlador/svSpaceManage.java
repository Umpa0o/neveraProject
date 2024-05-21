package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import modelo.Space;



/**
 * Servlet implementation class svSpaceManage
 */
public class svSpaceManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession miSesion;
    
    //Constructor
    public svSpaceManage() {
        super();
        
    }

	//doGET()
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}//fin doGet

	//doPOST()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variables que reocgen las variables bajo el input nombre name del form
		String id_espacio;//pasar a int
		String nombreEspacio = request.getParameter("nombreEspacio") ;
		String regimenTemperatura = request.getParameter("temperaturaEspacio");
		String icono = request.getParameter("iconoEspacio");
		/**no es get parameter es get source para la imagen????
		 * 
		 * src = /img/icon/
		 * 
		 * ***/
		miSesion = request.getSession(true);
		//miSesion.getAttribute("id");
		//hacemos un Casting de la sesion a tipo entero int
		int id_usuario=(Integer)miSesion.getAttribute("id");
		
		
		Space space = new Space(nombreEspacio, regimenTemperatura, icono, id_usuario);
		
		try {
			space.insertarEspacio();
			System.out.println("Espacio insertado correctamente");
		} catch (SQLException e) {
			System.out.println("Error tryCatch insertar Espacio servlet");
			e.getMessage();
		}
		
		System.out.println("datos para insertar Espacio");
		System.out.println(id_usuario);
		
		
		//response.sendRedirect("espacios.html");
		
	}//fin doPost

}//fin Servlet SpaceManage
