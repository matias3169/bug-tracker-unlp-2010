package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Sistema;
import unlp.edu.core.Proyecto;
import unlp.edu.core.Item;
import unlp.edu.core.Estado;
import unlp.edu.core.Miembro;

public class CambiarEstadoAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm cambiarEstadoForm = (DynaActionForm) form;
		
		// Extraemos los datos del formulario 
		String  nombreProyecto = (String) cambiarEstadoForm.get("nombreProyecto");
		String nombreItem = (String) cambiarEstadoForm.get("nombreItem");
		String descNuevoEstado = (String) cambiarEstadoForm.get("descNuevoEstado");
		String nomNuevoResponsable = (String) cambiarEstadoForm.get("responsable_item");
		String fichaTrabajo = (String) cambiarEstadoForm.get("fichaTrabajoItem");

		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		Long idProyecto = proyecto.getId();
		Item item = sistema.getItem(proyecto, nombreItem);
		Estado nuevoEstado = sistema.getEstadoTipoItem(item.getTipoItem(), descNuevoEstado);
		Miembro nuevoResponsable = sistema.getMiembro(proyecto, nomNuevoResponsable);
				
		sistema.cambiarEstadoItem(item, nuevoEstado, nuevoResponsable, fichaTrabajo);
		
	    ActionRedirect redirect = new ActionRedirect(mapping.findForward("ok"));

	    redirect.addParameter("id", idProyecto);

	    return redirect;
	}

}
