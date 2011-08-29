package unlp.edu.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

import unlp.edu.core.EstadoItem;
import unlp.edu.core.Sistema;
import unlp.edu.core.Proyecto;
import unlp.edu.core.Item;
import unlp.edu.core.Estado;
import unlp.edu.core.Miembro;
import unlp.edu.core.TipoItem;

public class CambiarEstadoAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm cambiarEstadoForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreProyecto = (String) cambiarEstadoForm.get("nombreProyecto");
		String nombreItem = (String) cambiarEstadoForm.get("nombreItem");
		String descNuevoEstado = (String) cambiarEstadoForm.get("descNuevoEstado");
		String nomNuevoResponsable = (String) cambiarEstadoForm.get("nomNuevoResponsable");
		String fichaTrabajo = (String) cambiarEstadoForm.get("fichaTrabajoItem");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		Item item = proyecto.getItem(nombreItem);
		Estado nuevoEstado = item.getTipoItem().getEstado(descNuevoEstado);
		Miembro nuevoResponsable = proyecto.getMiembro(nomNuevoResponsable);
		
		//Date date = new Date();
		Calendar date = Calendar.getInstance();
		//Hay que pasar la lista de miembros del estadoitem
		item.cambiarEstadoItem(nuevoEstado, nuevoResponsable, new HashSet<Miembro>(), date.getTime(), fichaTrabajo);
		
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}

}
