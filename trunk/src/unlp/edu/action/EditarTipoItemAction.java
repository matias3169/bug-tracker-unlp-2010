package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Sistema;
import unlp.edu.core.Proyecto;
import unlp.edu.core.Item;
import unlp.edu.core.Estado;
import unlp.edu.core.TipoItem;


public class EditarTipoItemAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarTipoItemForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreProyecto = (String) editarTipoItemForm.get("nombreProyecto");
		String descripcionTipoItem = (String) editarTipoItemForm.get("descripcion_tipoItem");
		String  nuevoEstadoIni = (String) editarTipoItemForm.get("nuevoEstadoInicial");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		int idProyecto = proyecto.getId();
		TipoItem tipoItem = proyecto.getTipoItem(descripcionTipoItem);
		Estado estadoIni = proyecto.getEstado(tipoItem, nuevoEstadoIni);
		
		tipoItem.setDescripcion(descripcionTipoItem);
		tipoItem.setEstadoInicial(estadoIni);
		
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);
		
		return mapping.findForward("ok"); 
		
	}

}
