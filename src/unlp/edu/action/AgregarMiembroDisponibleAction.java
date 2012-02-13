package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.TipoItem;
import unlp.edu.core.Miembro;
import unlp.edu.core.Estado;

public class AgregarMiembroDisponibleAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarMiembroDisponibleForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String idProyecto = (String) agregarMiembroDisponibleForm.get("idProyecto");
		String idTipoItem = (String) agregarMiembroDisponibleForm.get("idTipoItem");
		String tipoItemDesc = (String) agregarMiembroDisponibleForm.get("tipoItemDesc");
		String estadoDesc = (String) agregarMiembroDisponibleForm.get("estadoDesc");
		String nuevoMiembroDispNombre =  (String) agregarMiembroDisponibleForm.get("nuevoMiembroDispNombre");
		
		Sistema sistema = Sistema.getInstance();
		Proyecto proyecto = sistema.getProyecto(Integer.valueOf(idProyecto));
		TipoItem tipoItem = sistema.getTipoItem(tipoItemDesc, proyecto);
		Estado	estadoItem = sistema.getEstadoTipoItem(proyecto, tipoItem, estadoDesc);
		Miembro nuevoMiembro = proyecto.getMiembro(nuevoMiembroDispNombre);
		
		estadoItem.agregarMiembroDisponible(nuevoMiembro);
					
		// Mostramos la siguiente vista
		response.sendRedirect("tipoItem_editar.jsp?idTI=" + idTipoItem + "&idP=" + idProyecto);				
		return mapping.findForward("ok");		
	}
}
