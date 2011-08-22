package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;
import unlp.edu.core.Role;

public class EditarUsuarioAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarUsuarioForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreUsuario = (String) editarUsuarioForm.get("nombreUsuario");
		
		String  nuevoRolUsuario = (String) editarUsuarioForm.get("nuevoRol");
		String  nuevaClave		= (String) editarUsuarioForm.get("password");
		
		Sistema sistema = Sistema.getInstance();
		Usuario usuario = sistema.getUsuario(nombreUsuario);
		Role rol = sistema.getRoleSistema(nuevoRolUsuario);
		usuario.setRole(rol);
		usuario.setClave(nuevaClave);
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}
		
}
