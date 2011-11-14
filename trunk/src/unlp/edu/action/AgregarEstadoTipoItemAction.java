package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.TipoItem;


public class AgregarEstadoTipoItemAction  extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarEstadoTipoItemForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  idProyecto = (String) agregarEstadoTipoItemForm.get("idProyecto");
		String  tipoItemDescripcion = (String) agregarEstadoTipoItemForm.get("tipoItem");
		String descripcionEstado =  (String) agregarEstadoTipoItemForm.get("descripcionEstado");
		
		Sistema sistema = Sistema.getInstance();
		Proyecto proyecto = sistema.getProyecto(Integer.valueOf(idProyecto));
		TipoItem tipoItem = sistema.getTipoItem(tipoItemDescripcion, proyecto);
		
		tipoItem.agregarEstado(descripcionEstado);
			
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);			

		
		return mapping.findForward("ok");		
	}

}

