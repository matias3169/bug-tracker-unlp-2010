package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Role;
import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;

public class AgregarMiembroAction extends Action{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarMiembroForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  usuarioNombre = (String) agregarMiembroForm.get("usuario");
		String  proyectoNombre = (String) agregarMiembroForm.get("nombreProyecto");
		String  rolNombre = (String) agregarMiembroForm.get("rol_miembro");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(proyectoNombre);
		Long idProyecto = proyecto.getId();
		
		Usuario usuario = sistema.getUsuario(usuarioNombre);
		Role rol = sistema.getRoleProyecto(rolNombre);
		
		sistema.nuevoMiembro(proyecto, usuario, rol);
		
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));
	    redirect.addParameter("id", idProyecto);

	    return redirect;

	}
	

}
