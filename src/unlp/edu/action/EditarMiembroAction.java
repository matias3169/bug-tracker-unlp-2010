package unlp.edu.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;


import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;

public class EditarMiembroAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarMiembroForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		String  nombreProyecto = (String) editarMiembroForm.get("nombreProyecto");
		String  nombreMiembro = (String) editarMiembroForm.get("nombreMiembro");
		String  descRol = (String) editarMiembroForm.get("descRol");
		
		Sistema sistema = Sistema.getInstance();
	
		
		Proyecto proyecto = sistema.getProyectoPorNombre(nombreProyecto);
		
		sistema.editarMiembroProyecto(proyecto, nombreMiembro, descRol);
		
		
		// Mostramos la siguiente vista
		response.sendRedirect("proyecto_trabajar.jsp?id=" + proyecto.getId());
		
		return mapping.findForward("ok"); 
		
	}

}

