package unlp.edu.action;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

import unlp.edu.core.Role;
import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;


public class LoginAction extends Action{

	
		public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			DynaActionForm loginForm = (DynaActionForm) form;
			ActionErrors errors = new ActionErrors();
			// Extraemos los datos del formulario 
			String  nombre = (String) loginForm.get("nombre_usuario");
			String  clave = (String) loginForm.get("clave_usuario");
			
			HttpSession actualSession = request.getSession(true);
			
			Sistema sistema = Sistema.getInstance();

			if(sistema.validarCredenciales(nombre,clave))
			{  
			   actualSession.setAttribute("user", sistema.getUsuario(nombre));
			   actualSession.setAttribute("sistema", sistema);

			   // Agrega links para volver al nivel anterior según la página
			   LinksVolver lv = new LinksVolver();
			   actualSession.setAttribute("linkVolver", lv);
			   
				// Mostramos la siguiente vista
				return mapping.findForward("ok"); 

			} else {
				errors.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("credenciales.invalidas"));
				saveErrors(request, errors);
				return mapping.findForward("error");
			}
		}
}

