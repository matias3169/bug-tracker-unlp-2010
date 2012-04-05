package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Estado;
import unlp.edu.core.Sistema;
import unlp.edu.core.TipoItem;


public class EliminarEstadoTipoItemAction  extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm eliminarEstadoTipoItemForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  idTipoItem = (String) eliminarEstadoTipoItemForm.get("idTipoItem");
		String  idProyecto = (String) eliminarEstadoTipoItemForm.get("idProyecto");
		String descripcionEstado =  (String) eliminarEstadoTipoItemForm.get("descripcionEstado");
		
		Sistema sistema = Sistema.getInstance();
		
		TipoItem savedTipoItem = sistema.getTipoItemID(Long.valueOf(idTipoItem));
		Estado savedEstado = sistema.getEstadoTipoItem(savedTipoItem, descripcionEstado);
		
		sistema.eliminarEstadoTipoItem(savedEstado, savedTipoItem);
					
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));

	    redirect.addParameter("idTI", idTipoItem);
	    redirect.addParameter("idP", idProyecto);
	    
		return redirect;		
	}

}

