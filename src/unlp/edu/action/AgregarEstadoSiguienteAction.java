package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.TipoItem;
import unlp.edu.core.Estado;

public class AgregarEstadoSiguienteAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarEstadoSiguienteForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  idProyecto = (String) agregarEstadoSiguienteForm.get("idProyecto");
		String  idTipoItem = (String) agregarEstadoSiguienteForm.get("idTipoItem");
		String tipoItemDesc = (String) agregarEstadoSiguienteForm.get("tipoItemDesc");
		String estadoDesc =  (String) agregarEstadoSiguienteForm.get("estadoDesc");
		String estadoSiguienteDesc =  (String) agregarEstadoSiguienteForm.get("estadoSiguienteDesc");
		
		Sistema sistema = Sistema.getInstance();
		Proyecto proyecto = sistema.getProyecto(Integer.valueOf(idProyecto));
		TipoItem tipoItem = sistema.getTipoItem(tipoItemDesc, proyecto);
		Estado estado = tipoItem.getEstado(estadoDesc);
		Estado estadoSiguiente = tipoItem.getEstado(estadoSiguienteDesc);
		
		estado.agregarEstadoSiguiente(estadoSiguiente);
					
		// Mostramos la siguiente vista
		response.sendRedirect("tipoItem_editar.jsp?idTI=" + idTipoItem + "&idP=" + idProyecto);				
		return mapping.findForward("ok");		
	}
		
}
