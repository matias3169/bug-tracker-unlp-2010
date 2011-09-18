package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.TipoItem;
import unlp.edu.core.Miembro;

public class AgregarItemAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarItemForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  proyectoNombre = (String) agregarItemForm.get("nombreProyecto");
		String  itemNombre = (String) agregarItemForm.get("nombre_item");
		String  itemDescripcion = (String) agregarItemForm.get("descripcion_item");
		String  itemTipo = (String) agregarItemForm.get("tipo_item");
		String  itemPrioridad = (String) agregarItemForm.get("prioridad_item");
		String  itemResponsable = (String) agregarItemForm.get("responsable_item");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(proyectoNombre);
		int idProyecto = proyecto.getId();
		TipoItem tipoItem = proyecto.getTipoItem(itemTipo);
		Integer prioridad =  Integer.parseInt(itemPrioridad.trim());
		Miembro  responsable = proyecto.getMiembro(itemResponsable);
				
		if (proyecto == null || tipoItem == null || responsable == null)
		{
			return mapping.findForward("error");
		} else {
			
			proyecto.nuevoItem(itemNombre, itemDescripcion, tipoItem, prioridad, responsable);
			// Mostramos la siguiente vista
			response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);			

		}
		
		return mapping.findForward("ok");		
	}

}
