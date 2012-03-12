package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;
import org.hibernate.classic.Session;

import unlp.edu.core.Role;
import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;
import unlp.edu.util.HibernateUtil;

public class AgregarUsuarioAction extends Action{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm agregarProyectoForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreUsuario = (String) agregarProyectoForm.get("nombre_usuario");
		String  claveUsuario = (String) agregarProyectoForm.get("clave_usuario");
		String  rolUsuario = (String) agregarProyectoForm.get("rol_usuario");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Sistema sistema = Sistema.getInstance();
		//Aqui se deberia recuperar el rol de sistema de la BD
		Role role = sistema.getRoleSistema(rolUsuario);
		
		Usuario nuevoUsuario = new Usuario(nombreUsuario, claveUsuario, role);
		nuevoUsuario.setNombre(nombreUsuario);
		nuevoUsuario.setClave(claveUsuario);
		nuevoUsuario.setRole(role);
        session.save(nuevoUsuario);
        session.getTransaction().commit();

		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}

}
