package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Space;

import java.io.IOException;

/**
 * Servlet implementation class svSpaceManage
 */
public class svSpaceManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		 * ***/
		int id_usuario= Integer.parseInt(request.getParameter("id_usuario"));
		
		Space space = new Space(nombreEspacio, regimenTemperatura, icono, id_usuario);
		
		
		System.out.println("datos para insertar Espacio");
		
		
		//response.sendRedirect("espacios.html");
		
	}//fin doPost

}//fin Servlet SpaceManage
