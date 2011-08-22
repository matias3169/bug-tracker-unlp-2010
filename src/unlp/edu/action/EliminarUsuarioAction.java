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
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreUsuario = (String) eliminarUsuarioForm.get("nombreUsuario");
		
		Sistema sistema = Sistema.getInstance();
		Usuario usuario = sistema.getUsuario(nombreUsuario);
		
		if (usuario != null)
		{
			if (sistema.eliminarUsuario(usuario))
			{
				// Mostramos la siguiente vista
				return mapping.findForward("ok");
			} 
			else 
			{
				errors.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("fallo.eliminar"));
				saveErrors(request, errors);
				return mapping.findForward("error");			
			}
		} 
		else
		{
			errors.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("fallo.eliminar"));
			saveErrors(request, errors);
			return mapping.findForward("error");	
		}
	}
}
