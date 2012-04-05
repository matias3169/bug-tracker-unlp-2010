package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

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
		String idEstadoInicial =  (String) agregarEstadoSiguienteForm.get("idEstado");
		String estadoSiguienteDesc =  (String) agregarEstadoSiguienteForm.get("estadoSiguienteDesc");
		
		Sistema sistema = Sistema.getInstance();
		
		TipoItem tipoItem = sistema.getTipoItemID(Long.valueOf(idTipoItem));
		Estado savedEstadoInicial = sistema.getEstadoID(Long.valueOf(idEstadoInicial));
		Estado savedEstadoSiguiente = sistema.getEstadoTipoItem(tipoItem, estadoSiguienteDesc);
				
		sistema.agregarEstadoSiguiente(tipoItem, savedEstadoInicial, savedEstadoSiguiente);
		
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));

	    redirect.addParameter("idTI", idTipoItem);
	    redirect.addParameter("idP", idProyecto);
	    return redirect;
	}
		
}
