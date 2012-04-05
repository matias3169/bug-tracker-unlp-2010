package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;

public class EliminarUsuarioAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm eliminarUsuarioForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  nombreUsuario = (String) eliminarUsuarioForm.get("nombreUsuario");
		
		Sistema sistema = Sistema.getInstance();
		Usuario savedUsuario = sistema.getUsuario(nombreUsuario);
		
		sistema.eliminarUsuario(savedUsuario);
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok");
		
	}
}
