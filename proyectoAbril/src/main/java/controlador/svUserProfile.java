package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**anotación-> @nombre  pueden llevar atributos, en este caso avisamos de que usaremos multipart
Si no lo ponemos, no reconocerá ninguna funcion correspondiente al envio de archivos*/
@MultipartConfig

/**También vamos a etiquetar el nombre del server por defecto, aunque no siempre haga falta según servidor
buscará el nombre del servlet mediante anotación, indicamos que está en la raíz 
como todos los servlets "/" y el nombre del servlet*/
//@WebServlet("/svUserProfile")

/**
 * Servlet implementation class svUserProfile para la página de perfil de usuario
 * 	podrá subir una imagen como imagen de su propio prefil, marco de foto, descripcion del perfil, gestion de espacios y listaas
 * 
 * 
 */
public class svUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//creamos atributo de la clase privado de objeto tipo file
		private String rutaCarpeta = "C:\\xampp\\htdocs\\neveraProject\\proyectoAbril\\src\\main\\webapp\\img\\usr";
		private File subidas = new File(rutaCarpeta);

    public svUserProfile() {
        super();
 
    }//fin contructor


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}//fin doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Variables, recogen datos del formulario
		
		String nombreUsuario = request.getParameter("nombre");
		String emailUsuario =request.getParameter("email");
		String imagenUsuario = request.getParameter("imgUser");
		String descripcionUsuario = request.getParameter("descripcionPerfil");
		
		
		
		
		
		/**SUBIR IMÁGENES:
		 *  recibimos el nombre del campo del formulario para subir la img fotoUsuario
		 * y recuperamos la foto del formulario
		 * <ul>
		 * 		<li>.getPart()-> part: bloque de datos	</li>
		 * 		<li>.getParts()-> para listas con varias fotos, permite subir varias img a la vez</li>
		 * </ul>
		 * @see 
		 * <ul>javadocs
		 * 		<li> Part: https://docs.oracle.com/javaee%2F7%2Fapi%2F%2F/javax/servlet/http/Part.html#getSubmittedFileName--	</li>
		 * 		<li> Paths: https://docs.oracle.com/javase/8/docs/api/java/nio/file/Paths.html#get-java.lang.String-java.lang.String...- </li>
		 * 		<li> File: https://docs.oracle.com/javase/8/docs/api/java/io/File.html </li>
		 * 		<li> Files: https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html    </li>
		 *  <li></li>
		 * 
		 * </ul>
		 * 		
		 *
		 * **/
		Part part= request.getPart("fotoUsuario");
		
		//Coger el nombre del archivo que se ha subido, "ruta/nombre.png" con objeto tipo path del archivo part que obtenemos
		Path path = Paths.get(part.getSubmittedFileName());
		
		//Recogemos el string para guardar en la db
		String fileName = path.getFileName().toString();
		
		//Preparamos buffer para transmitir los datos(part, el bloque que recoge la img del form)
		InputStream input = part.getInputStream();
		
		//creamos un archivo en la carpeta de subidas de usuario que será el contenedor de la img "file"
		File file = new File(subidas, fileName);
		
		//Copiamos al contenedor los datos que tiene el part mediante el buffer usamos libreria Files
		Files.copy(input, file.toPath());
		System.out.println("Foto subida a img/usr/: "+fileName);
		
		/***
		 * modificar usuario, nombre/img/descripcion
		*/
		//User ue = new User(nombreUsuario, emailUsuario, "/img/usr/"+imagenUsuario, descripcionUsuario);

		
	}//fin doPost
	/**Subir una imagen, pasos a seguir:				tutoria Antonio	16/03/2024
	 *		-Decidir en que carpetas se guardan las fotos 
	 *		-Recuperar el nombre del archivo que subir
	 *		-Recuperar la ruta temporal donde el servidor guardo el archivo
	 *		-Decidir el nombre que va a tener el archivo, hacerlo objeto FILE (java usa clase file para gestionar tanto carpetas como archivos)
	 *		-Trasmitir los datos del archivo
	 *		-Guardar el archivo
	 * 
	 * **/

}//fin servlet User Profile
