package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Role;
import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;

public class EditarUsuarioAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarUsuarioForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  nombreUsuario = (String) editarUsuarioForm.get("nombreUsuario");
		String  nuevoRolUsuario = (String) editarUsuarioForm.get("nuevoRol");
		String  nuevaClave		= (String) editarUsuarioForm.get("password");
		
		Sistema sistema = Sistema.getInstance();
		Usuario usuario = sistema.getUsuario(nombreUsuario);
		Role nuevoRol = sistema.getRoleSistema(nuevoRolUsuario);
		
		sistema.editarUsuario(usuario,nuevoRol,nuevaClave);
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}
		
}
