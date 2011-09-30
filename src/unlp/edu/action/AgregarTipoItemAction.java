package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.Proyecto;;

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
		int idProyecto = proyecto.getId();
		
		proyecto.nuevoTipoItem(tipoItemDescripcion);
			
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);			

		
		return mapping.findForward("ok");		
	}
}
