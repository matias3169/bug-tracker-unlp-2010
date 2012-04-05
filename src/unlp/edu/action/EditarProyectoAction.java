package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Role;
import unlp.edu.core.Sistema;
import unlp.edu.core.Miembro;

public class EditarProyectoAction extends Action{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarProyectoForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  nombreActualProyecto = (String) editarProyectoForm.get("nombreActualProyecto");
		String  nuevoNombreProyecto = (String) editarProyectoForm.get("nombreProyecto");
		String  nuevoLiderProyecto = (String) editarProyectoForm.get("liderProyecto");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto savedProyecto = sistema.getProyectoPorNombre(nombreActualProyecto);		
		Role roleLider = sistema.getRoleProyecto("Lider");
		Miembro actualLider = savedProyecto.getLiderProyecto();
		
		Miembro nuevoLider = sistema.getMiembro(savedProyecto, nuevoLiderProyecto);

		//Es un usuario que aun no es miembro del proyecto - Se crea y persiste
		if (nuevoLider == null)
		{
			nuevoLider = sistema.nuevoMiembro(savedProyecto, sistema.getUsuario(nuevoLiderProyecto), roleLider);
		} else if (!nuevoLider.equals(actualLider)) {
			//Es miembro del proyecto pero no es el lider actual	
			sistema.editarMiembro(nuevoLider,roleLider);
		}
		
		sistema.editarProyecto(savedProyecto,nuevoNombreProyecto,nuevoLider);
		
		//Se asigna un rol de desarrollador al antiguo Lider
		if (!nuevoLider.equals(actualLider))
		{
			sistema.editarMiembro(actualLider, sistema.getRoleProyecto("Desarrollador"));
		}
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}

}
