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
		String  descripcionInicialTipoItem = (String) editarTipoItemForm.get("descripcion_inicial");
		String descripcionNuevaTipoItem = (String) editarTipoItemForm.get("descripcion_nueva");
		String  nuevoEstadoIni = (String) editarTipoItemForm.get("nuevoEstadoInicial");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		int idProyecto = proyecto.getId();
		TipoItem tipoItem = proyecto.getTipoItem(descripcionInicialTipoItem);
		Estado estadoIni = proyecto.getEstadoTipoItem(tipoItem, nuevoEstadoIni);
		
		tipoItem.setDescripcion(descripcionNuevaTipoItem);
		tipoItem.setEstadoInicial(estadoIni);
		
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);
		
		return mapping.findForward("ok"); 
		
	}

}
