package unlp.edu.action;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Role;
import unlp.edu.core.Usuario;


public class LoginAction extends Action{

	
		public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			DynaActionForm loginForm = (DynaActionForm) form;
			
			// Extraemos los datos del formulario 
			String  nombre = (String) loginForm.get("nombre");
			String  clave = (String) loginForm.get("clave");
			
			
			// Configurariamos los objetos Request, Session, etc. que necesita la siguiente vista a mostrar
			Role role = new Role("Sysadmin", new HashSet<String>());
			Usuario user = new Usuario(nombre, role);
			
			request.setAttribute("user", user);
			
			// Mostramos la siguiente vista
			return mapping.findForward("ok"); 
		}
}

