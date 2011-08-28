package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.Miembro;;

public class EditarProyectoAction extends Action{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarProyectoForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreActualProyecto = (String) editarProyectoForm.get("nombreActualProyecto");
		
		String  nuevoNombreProyecto = (String) editarProyectoForm.get("nombreProyecto");
		String  nuevoLiderProyecto = (String) editarProyectoForm.get("liderProyecto");
		
		Sistema sistema = Sistema.getInstance();
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreActualProyecto);
		proyecto.setNombre(nuevoNombreProyecto);
		Miembro liderProyecto = sistema.getMiembro(proyecto, nuevoLiderProyecto);
		proyecto.setLiderProyecto(liderProyecto);
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}

}
