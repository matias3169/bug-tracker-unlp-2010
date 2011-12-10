package unlp.edu.action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Sistema;
import unlp.edu.core.Proyecto;
import unlp.edu.core.Item;
import unlp.edu.core.Miembro;

public class EditarItemAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarItemForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String fecha[] = new String[3];
		
		String  nombreProyecto = (String) editarItemForm.get("nombreProyecto");
		String nombreItem = (String) editarItemForm.get("nombreItem");
		String nomNuevoResponsable = (String) editarItemForm.get("nomNuevoResponsable");
		String fichaTrabajo = (String) editarItemForm.get("fichaTrabajoItem");
		String prioridad = (String) editarItemForm.get("prioridad");
		String descripcion = (String) editarItemForm.get("descripcion");
		
		Sistema sistema = Sistema.getInstance();
		Calendar calendar = Calendar.getInstance();
	
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		int idProyecto = proyecto.getId();
		Item item = proyecto.getItem(nombreItem);
		Miembro nuevoResponsable = sistema.getMiembro(proyecto, nomNuevoResponsable);
		
		//No se trata del mismo responsable - Crear nuevo estado item
		if (!item.getEstadoActual().getResponsable().getUsuario().getNombre().equals(nomNuevoResponsable))
		{
			item.cambiarResponsable(nuevoResponsable, calendar.getTime(), fichaTrabajo);
			item.setPrioridad(Integer.parseInt(prioridad));
			item.setDescripcion(descripcion);
		}
		else
		{		
			item.setPrioridad(Integer.parseInt(prioridad));
			item.setDescripcion(descripcion);
			item.getEstadoActual().setFichaDeTrabajo(fichaTrabajo);
		}
		
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);
		
		return mapping.findForward("ok"); 
		
	}

}
