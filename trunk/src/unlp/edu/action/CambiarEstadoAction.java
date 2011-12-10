package unlp.edu.action;

import java.util.Calendar;
import java.util.HashSet;

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
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String fecha[] = new String[3];
		
		String  nombreProyecto = (String) cambiarEstadoForm.get("nombreProyecto");
		String nombreItem = (String) cambiarEstadoForm.get("nombreItem");
		String descNuevoEstado = (String) cambiarEstadoForm.get("descNuevoEstado");
		String nomNuevoResponsable = (String) cambiarEstadoForm.get("nomNuevoResponsable");
		String fichaTrabajo = (String) cambiarEstadoForm.get("fichaTrabajoItem");
		String fechaEstado = (String) cambiarEstadoForm.get("fechaEstado");
		
		Sistema sistema = Sistema.getInstance();
		
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		int idProyecto = proyecto.getId();
		Item item = proyecto.getItem(nombreItem);
		Estado nuevoEstado = item.getTipoItem().getEstado(descNuevoEstado);
		Miembro nuevoResponsable = proyecto.getMiembro(nomNuevoResponsable);
		
		Calendar calendar = Calendar.getInstance();
		fecha = fechaEstado.split("/");
		
		int year= Integer.parseInt(fecha[2]);
		int month= Integer.parseInt(fecha[1]) - 1;
		int day= Integer.parseInt(fecha[0]);
		
		calendar.set(year,month,day);
		
		//Hay que pasar la lista de miembros del estadoitem
		item.cambiarEstadoItem(nuevoEstado, nuevoResponsable, calendar.getTime(), fichaTrabajo);
		
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + idProyecto);
		
		return mapping.findForward("ok"); 
		
	}

}
