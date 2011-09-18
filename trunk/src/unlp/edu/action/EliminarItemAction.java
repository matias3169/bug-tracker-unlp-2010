package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Sistema;
import unlp.edu.core.Proyecto;
import unlp.edu.core.Item;

public class EliminarItemAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarItemForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
				
		String  nombreProyecto = (String) editarItemForm.get("nombreProyecto");
		String nombreItem = (String) editarItemForm.get("nombreItem");
		
		Sistema sistema = Sistema.getInstance();
	
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		int idProyecto = proyecto.getId();
		Item item = proyecto.getItem(nombreItem);
		
		if (!sistema.eliminarItem(proyecto, item))
		{
			//No se pudo eliminar item
			errors.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("fallo.eliminar"));
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
				
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);
		
		return mapping.findForward("ok"); 
		
	}

}
