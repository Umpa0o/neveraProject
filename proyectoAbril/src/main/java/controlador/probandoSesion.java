package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class probandoSesion
 */
public class probandoSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//instanciamos
	HttpSession sesion;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public probandoSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Iniciamos la variable con una peticion
		sesion = request.getSession();
		
		String nombre = "Patata"; 
		int id = 23;
		//.setAtribute() guardamos. damos nombre a la clave nombre: y el valor, variable nombre
		//cuando se guarda en la sesión deja de estar en JAVA, se guarda en el servidor, pero no tenemos objeto string
		sesion.setAttribute("nombre" , nombre);
		sesion.setAttribute("id", id);
		
		//Hacemos el paso a String
		//.getAtributte() recoge el valor de los atributos
		String respuesta = (String) sesion.getAttribute("nombre");
		System.out.println(respuesta);
		
		//eliminar los datos de la sesión .invalidate()
		sesion.invalidate();
		
	}//fin doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}//fin doPost

}//fin servlet probandoSesion
