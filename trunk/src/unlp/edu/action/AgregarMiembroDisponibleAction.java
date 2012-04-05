package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
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
		String idEstado = (String) agregarMiembroDisponibleForm.get("idEstado");
		String nuevoMiembroDispNombre =  (String) agregarMiembroDisponibleForm.get("nuevoMiembroDispNombre");
		
		Sistema sistema = Sistema.getInstance();
		Proyecto proyecto = sistema.getProyecto(Long.valueOf(idProyecto));
		Estado	estadoItem = sistema.getEstadoID(Long.valueOf(idEstado));
		Miembro nuevoMiembro = sistema.getMiembro(proyecto, nuevoMiembroDispNombre);
		
		sistema.agregarMiembroDisponible(nuevoMiembro, estadoItem);

	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));
	    redirect.addParameter("idTI", idTipoItem);
	    redirect.addParameter("idP", idProyecto);
	    
	    return redirect;
	}
}
