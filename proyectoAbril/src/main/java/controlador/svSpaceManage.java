package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoSpace;
import dao.DaoUser;
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
		miSesion = request.getSession(true);
		
		int id_usuario= (Integer)miSesion.getAttribute("id_usuario");
		String nombreUsuario = (String) miSesion.getAttribute("nombre");
		String opcionEspacio = (request.getParameter("opSpace")) ;
	}//fin doGet

	//doPOST()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		
		
		//variables que reocgen las variables bajo el input nombre name del form
		String id_espacio;//pasar a int
		String nombreEspacio = request.getParameter("nombreEspacio") ;
		String regimenTemperatura = request.getParameter("temperaturaEspacio");
		String icono = request.getParameter("iconoEspacio");//¿?para la source de la img¿?

		
		String opcionEspacio = (request.getParameter("opSpace")) ;
		
		miSesion = request.getSession();
		//miSesion.getAttribute("id");
		
		int id_usuario= (Integer)miSesion.getAttribute("id_usuario");
		String nombreUsuario = (String) miSesion.getAttribute("nombre");
		
		
		/**int id_usuario = 0;
		String compruebaId = (String)miSesion.getAttribute("id");
		
		if (compruebaId ==  null) {
			response.sendRedirect("login.html");
			//out.print("document.getElementById('mensajeLogin').innerHTML='Necesitar logearte para crear espacios'");
			
			String ht = "<div class='mensajeLogin'>";
			
			ht += "<h2>";
			ht += "Error al crear Espacio, debes registrarte antes";
			ht += "</h2>";
			
			ht += "</div>";
			
			System.out.println("mensaje de error que no slta en html");
			response.setContentType("text/html");
			out.println(ht);	
		} else {
			
			id_usuario=(Integer)miSesion.getAttribute("id");
		}*/
		//hacemos un Casting de la sesion a tipo entero int
		//int id_usuario=(Integer)miSesion.getAttribute("id");
		
		/*if(id_usuario == 0 ) {
			
			request.getRequestDispatcher("login.html");
		}*/
		
		
		Space space = new Space(nombreEspacio, regimenTemperatura, icono, id_usuario);
		
		try {
			space.insertarEspacio();
			System.out.println("Espacio insertado correctamente");
			response.sendRedirect("espacios.html");
		} catch (SQLException e) {
			System.out.println("Error tryCatch insertar Espacio servlet");
			e.getMessage();
		}
		
		//System.out.println("datos para insertar Espacio");
		System.out.println("El espacio le pertenece al usuario: "+id_usuario);
		
		/**llamar a listar al servlet*/
		if(opcionEspacio=="1") {
			DaoSpace daoSp;
			try {
				daoSp = new DaoSpace();
				out.print(daoSp.listarEspacios(id_usuario));
				System.out.println("Estamos en la opcion 1(listar) del servlet spacio");
				
				
			} catch (SQLException e) {
				
				System.out.println("Error tryCatch servlet Space listar");
				e.getMessage();
			}//fin try/catch
			
		}//fin opcion 1 listarEspacio
		
		//response.sendRedirect("espacios.html");
		
	}//fin doPost

}//fin Servlet SpaceManage
