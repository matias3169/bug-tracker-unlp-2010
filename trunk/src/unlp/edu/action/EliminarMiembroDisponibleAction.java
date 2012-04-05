package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Estado;
import unlp.edu.core.Miembro;
import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.TipoItem;


public class EliminarMiembroDisponibleAction  extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm eliminarEstadoSiguienteForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  idTipoItem = (String) eliminarEstadoSiguienteForm.get("idTipoItem");
		String  idProyecto = (String) eliminarEstadoSiguienteForm.get("idProyecto");
		String descripcionEstado =  (String) eliminarEstadoSiguienteForm.get("descripcionEstado");
		String descripcionMiembro =  (String) eliminarEstadoSiguienteForm.get("descripcionMiembro");
		
		Sistema sistema = Sistema.getInstance();
		
		TipoItem savedTipoItem = sistema.getTipoItemID(Long.valueOf(idTipoItem));
		Estado estado = sistema.getEstadoTipoItem(savedTipoItem, descripcionEstado);
		Proyecto proyecto = sistema.getProyecto(Long.valueOf(idProyecto));
		Miembro miembro = sistema.getMiembro(proyecto, descripcionMiembro);

		sistema.eliminarMiembroEstado(miembro,estado);	
		
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));

	    redirect.addParameter("idTI", idTipoItem);
	    redirect.addParameter("idP", idProyecto);
	    
		return redirect;		
	}

}
