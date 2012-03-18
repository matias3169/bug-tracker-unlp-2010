package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import unlp.edu.core.Proyecto;
import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;
import unlp.edu.core.Role;
import unlp.edu.util.HibernateUtil;

public class EditarUsuarioAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm editarUsuarioForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreUsuario = (String) editarUsuarioForm.get("nombreUsuario");
		String  nuevoRolUsuario = (String) editarUsuarioForm.get("nuevoRol");
		String  nuevaClave		= (String) editarUsuarioForm.get("password");
				
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Recupero el usuario
		Criteria criteria = session.createCriteria(Usuario.class)
		.add(Restrictions.eq("nombre", nombreUsuario));
		Usuario usuario = (Usuario) criteria.uniqueResult();
		
		//Recupero el nuevo Rol
		criteria = session.createCriteria(Role.class)
		.add(Restrictions.eq("nombre", nuevoRolUsuario))
		.add(Restrictions.eq("tipo", "Sistema"));
		Role role = (Role) criteria.uniqueResult();

		//Seteo nuevos valores
		usuario.setRole(role);
		usuario.setClave(nuevaClave);
		
		session.update(usuario);
		
		session.getTransaction().commit();
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok"); 
		
	}
		
}
