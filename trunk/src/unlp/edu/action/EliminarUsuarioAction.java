package unlp.edu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import unlp.edu.core.Sistema;
import unlp.edu.core.Usuario;
import unlp.edu.util.HibernateUtil;

public class EliminarUsuarioAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm eliminarUsuarioForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		
		// Extraemos los datos del formulario 
		String  nombreUsuario = (String) eliminarUsuarioForm.get("nombreUsuario");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Recupero el usuario
		Criteria criteria = session.createCriteria(Usuario.class)
		.add(Restrictions.eq("nombre", nombreUsuario));
		Usuario usuario = (Usuario) criteria.uniqueResult();
		
		session.delete(usuario);
		session.getTransaction().commit();
		
		// Mostramos la siguiente vista
		return mapping.findForward("ok");
		
	}
}
