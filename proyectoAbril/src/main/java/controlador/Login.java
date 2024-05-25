package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.User;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Servlet implementation class Login
 * 		Recibe los datos del formulario de login.html para validar al usuario e iniciar sesión o reenviarle al login/registro
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Instanciamos la sesión
	HttpSession sison;
       

    public Login() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}//fin doGet

	/** metodo doPost que controla el proceso del login y la salida al cliente de este proceso.
	 * 		usamos un metodo miMD5 para cifrar la contraseña del usuario
	 * 
	 * **/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Variables que recogen los datos introducidos en el formulario LOGIN.HTML
		String mail = request.getParameter("mailUser");
		System.out.println("do post login");
		
		//contandor intentos contraseña
		int contador=0;
		
		//pasamos el metodo para cifrar la contraseña en MD5 
		/****
		 * String password = miMD5(request.getParameter("passUser")) ;
		 * 
		 * **/
		String password = (request.getParameter("passUser")) ;
		
		
		User u = new User();
		u.setEmail(mail);
		System.out.println(u.toString());
		//System.out.println("contraseña cifrada check: "+password);
		
		//protección: si el u esta logeao o no?
		//true-> guardar id y permiso de ese usuario
		//false-> mandar a pagina de registro/inicio
		if(u.logear(password)) {
			//Iniciamos la sesión
			sison =  request.getSession(true);
			
			//añadimos a la sesión campo id y permiso
			sison.setAttribute("id_usuario", u.getId());
			sison.setAttribute("nombre", u.getNombreUser());
			//sison.setAttribute("email", u.getEmail());
			sison.setAttribute("permiso", u.getEsAdmin());
			
			System.out.println(sison.getAttribute("id_usuario"));
			System.out.println(sison.getAttribute("nombre"));
			System.out.println(sison.getAttribute("permiso"));
			
			
			
			
			if(u.getEsAdmin() == 9) {
				response.sendRedirect("gestionAdmin.html");
				System.out.println("Administrador logeado: "+ mail);
			}else {
				response.sendRedirect("preperfil.html");
				System.out.println("Usuario logeado: "+ mail);
			}
			
		
			System.out.println(u.toString());
			//System.out.println(sison.toString());
		}else {
			response.sendRedirect("login.html");
			System.out.println("Usuario NO logeado: "+ mail);
			System.out.println("puede que no sea correcto");
			contador ++;
			System.out.println("llevas intentandolo "+contador+" veces, cuidado");
		}
		/*
		if(contador == 3) {
			System.out.println("Usuario bloqueado, contacta con un administrador");
			u.setEsAdmin(0);
		}*/
		
		
		
	}//fin doPost
	
	/**metodo miMD5 para cifrar la contraseña del usuario
	 * 
	 * @retun hastext devuelve la contraseña cifrada
	 * 
	 * */
	private static String miMD5(String inputPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(inputPassword.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }//fin miMD5

}//fin servlet login
