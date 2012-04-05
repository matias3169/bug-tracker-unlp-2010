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
				
		String  nombreProyecto = (String) editarItemForm.get("nombreProyecto");
		String nombreItem = (String) editarItemForm.get("nombreItem");
		
		Sistema sistema = Sistema.getInstance();
	
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		Long idProyecto = proyecto.getId();
		Item item = sistema.getItem(proyecto, nombreItem);
		
		sistema.eliminarItem(item);
		
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));
	    redirect.addParameter("id", idProyecto);
	    
	    return redirect;
	}

}
