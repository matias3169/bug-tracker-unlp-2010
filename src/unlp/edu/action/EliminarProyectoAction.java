package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;

public class EliminarProyectoAction extends Action{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm eliminarProyectoForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreProyecto = (String) eliminarProyectoForm.get("nombreProyecto");
		
		Sistema sistema = Sistema.getInstance();
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		if (proyecto != null)
		{
			if (sistema.eliminarProyecto(proyecto))
			{
				// Mostramos la siguiente vista
				return mapping.findForward("ok");
			} else {
				errors.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("fallo.eliminar"));
				saveErrors(request, errors);
				return mapping.findForward("error");			
			}
		} else{
			errors.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("fallo.eliminar"));
			saveErrors(request, errors);
			return mapping.findForward("error");			
		}
		
	}

}