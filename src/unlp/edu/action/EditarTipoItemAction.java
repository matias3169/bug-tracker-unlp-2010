package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Sistema;
import unlp.edu.core.Proyecto;
import unlp.edu.core.Estado;
import unlp.edu.core.TipoItem;

public class EditarTipoItemAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarTipoItemForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  nombreProyecto = (String) editarTipoItemForm.get("nombreProyecto");
		String  descripcionInicialTipoItem = (String) editarTipoItemForm.get("descripcion_inicial");
		String descripcionNuevaTipoItem = (String) editarTipoItemForm.get("descripcion_nueva");
		String  nuevoEstadoIni = (String) editarTipoItemForm.get("nuevoEstadoInicial");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		Long idProyecto = proyecto.getId();
		TipoItem savedTipoItem = sistema.getTipoItem(descripcionInicialTipoItem, proyecto);
		Estado estadoIni = sistema.getEstadoTipoItem(savedTipoItem, nuevoEstadoIni);
	
		sistema.editarTipoItem(savedTipoItem, descripcionNuevaTipoItem, estadoIni);
		
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));

	    redirect.addParameter("id", idProyecto);
	    return redirect;
	
	}

}
