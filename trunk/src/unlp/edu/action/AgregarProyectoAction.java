package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import unlp.edu.core.Sistema;

public class AgregarProyectoAction extends Action{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarProyectoForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  nombreProyecto = (String) agregarProyectoForm.get("nombre_proyecto");
		String  liderProyecto = (String) agregarProyectoForm.get("lider_proyecto");
		
		Sistema sistema = Sistema.getInstance();
		
		sistema.nuevoProyecto(nombreProyecto, liderProyecto);
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}

}
