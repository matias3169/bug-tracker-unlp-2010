package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Estado;
import unlp.edu.core.Sistema;
import unlp.edu.core.TipoItem;


public class EliminarEstadoSiguienteAction  extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm eliminarEstadoSiguienteForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  idTipoItem = (String) eliminarEstadoSiguienteForm.get("idTipoItem");
		String  idProyecto = (String) eliminarEstadoSiguienteForm.get("idProyecto");
		String descripcionEstadoInicial =  (String) eliminarEstadoSiguienteForm.get("descripcionEstadoInicial");
		String descripcionEstadoSiguiente =  (String) eliminarEstadoSiguienteForm.get("descripcionEstadoSiguiente");
		
		Sistema sistema = Sistema.getInstance();
		
		TipoItem savedTipoItem = sistema.getTipoItemID(Long.valueOf(idTipoItem));
		Estado estadoInicial = sistema.getEstadoTipoItem(savedTipoItem, descripcionEstadoInicial);
		Estado estadoSiguiente = sistema.getEstadoTipoItem(savedTipoItem, descripcionEstadoSiguiente);
		
		sistema.eliminarEstadoSiguiente(estadoInicial,estadoSiguiente);
					
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));

	    redirect.addParameter("idTI", idTipoItem);
	    redirect.addParameter("idP", idProyecto);
	    
		return redirect;		
	}

}
