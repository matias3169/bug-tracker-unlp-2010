package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;

public class AgregarTipoItemAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarTipoItemForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  proyectoNombre = (String) agregarTipoItemForm.get("nombreProyecto");
		String  tipoItemDescripcion = (String) agregarTipoItemForm.get("descripcion_tipoItem");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(proyectoNombre);
		Long idProyecto = proyecto.getId();
		
		sistema.nuevoTipoItem(tipoItemDescripcion, proyecto);
		
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));
	    redirect.addParameter("id", idProyecto);
		
		return redirect;		
	}
}
