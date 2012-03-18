/**
 * 
 */
package unlp.edu.core;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import unlp.edu.util.HibernateUtil;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Sistema {
	
	private static Sistema sistema;
	private static int id_proyecto = 0;
	private static Long id_usuario = 0L;
	private static Long id_role = 0L;
    private static boolean yaCreado = false;

	private Collection<Proyecto> proyectos;
	private Collection<Role> rolesSistema;
	private Collection<Role> rolesProyecto;
	private Collection<Usuario> usuarios;
	
	private Sistema(){
		this.setProyectos(new HashSet<Proyecto>());
		this.setRolesSistema();
		this.setRolesProyecto();
		this.setUsuarios(new HashSet<Usuario>());
	}

	public static Sistema getInstance() 
	{
        if(yaCreado == false) {
            sistema = new Sistema();
    		sistema.nuevoUsuario("admin", "admin", sistema.getRoleSistema("Administrador"));
    		sistema.nuevoUsuario("guest", "guest", sistema.getRoleSistema("Desarrollador"));
    		sistema.nuevoUsuario("developer", "developer", sistema.getRoleSistema("Desarrollador"));

            yaCreado = true;
      }
      return sistema;
	}
	
	private static int getIdProyecto()
	{
		return id_proyecto;
	}
	
	private static void setIdProyecto()
	{
		id_proyecto++;
	}

	private static Long getIdUsuario()
	{
		return id_usuario;
	}
	
	private static void setIdUsuario()
	{
		id_usuario++;
	}
	
	private static Long getIdRole()
	{
		return id_role;
	}
	
	private static void setIdRole()
	{
		id_role++;
	}
	
	/**
	 * @return the proyectos
	 */
	public Collection<Proyecto> getProyectos() {
		return proyectos;
	}

	/**
	 * @param proyectos the proyectos to set
	 */
	public void setProyectos(Collection<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	/**
	 * @return the roles
	 */
	public Collection<Role> getRolesSistema() {
		return rolesSistema;
	}

	/**
	 * @return the roles
	 */
	public Collection<Role> getRolesProyecto() {
		return rolesProyecto;
	}

	/**
	 * @return the usuarios
	 */
	@SuppressWarnings("unchecked")
	public Collection<Usuario> getUsuarios() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Role.class)
		.add(Restrictions.eq("tipo", "Sistema"));
		
		List<Role> roles = criteria.list();
		
		criteria = session.createCriteria(Usuario.class)
		.add(Restrictions.in("role", roles));
		//.add(Restrictions.in("role", new Long[]{(long) 1,(long) 2}));
				
		usuarios = (Collection<Usuario>) criteria.list();
		
		session.close();
		
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Establece roles del sistema hardcodeados
	 * @return Lista de roles del sistema
	 */
    private void setRolesSistema(){
    	rolesSistema = new HashSet<Role>();
    	setIdRole();
    	rolesSistema.add(new Role(getIdRole(),"Sistema","Administrador"));
    	setIdRole();
    	rolesSistema.add(new Role(getIdRole(),"Sistema","Desarrollador"));
    }

	/**
	 * Establece roles del proyecto hardcodeados
	 * @return Lista de roles del proyecto
	 */
    private void setRolesProyecto(){
    	rolesProyecto = new HashSet<Role>();
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Lider"));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Desarrollador"));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","DBA"));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Tester"));
    }
    
    
    public Miembro nuevoMiembro(Proyecto proyecto, Usuario usuario, Role rol){
    	Miembro miembro = new Miembro(proyecto, usuario, rol);
    	proyecto.agregarMiembro(miembro);
    	return miembro;
    }
    
    public Estado nuevoEstado(Proyecto proyecto, TipoItem tipoItem, String descripcion){
    	return proyecto.nuevoEstado(tipoItem, descripcion);
    }
    
    public Proyecto nuevoProyecto(String nombre, Usuario usuario){
    	setIdProyecto();
    	Proyecto proyecto = new Proyecto(getIdProyecto(), nombre);
    	Miembro miembro = new Miembro(proyecto,usuario,this.getRoleProyecto("Lider"));
    	proyecto.agregarMiembro(miembro);
    	proyecto.setLiderProyecto(miembro);
    	this.proyectos.add(proyecto);
    	return proyecto;
    }
    
    public TipoItem nuevoTipoItem(String descripcion, Proyecto proyecto){
    	return proyecto.nuevoTipoItem(descripcion);
    }
    
    public Usuario nuevoUsuario(String nombre, String clave, Role rolSistema){
    	setIdUsuario();
    	Usuario usuario = new Usuario(getIdUsuario(),nombre, clave, rolSistema);
    	this.usuarios.add(usuario);
    	return usuario;
    }
    
    
    public Item nuevoItem(String nombre, String descripcion, TipoItem tipo, int prioridad, Proyecto proyecto, Miembro responsable){
    	return proyecto.nuevoItem(nombre, descripcion, tipo, prioridad, responsable);
    }
    
    public void cambiarEstadoItem(Proyecto proyecto, Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fecha, String fichaTrabajo) throws Exception{
    	proyecto.cambiarEstadoItem(item, estado, responsable, fecha, fichaTrabajo);
    }
    
	public void agregarEstadoSiguiente(Proyecto proyecto, TipoItem tipo, String estadoI, String estadoF){
	    	proyecto.agregarEstadoSiguiente(tipo, estadoI, estadoF);
	}
    
    public Collection<TipoItem> getTiposItems(Proyecto proyecto){
    	return proyecto.getTiposItems();
    }

	public Estado getEstadoTipoItem(Proyecto proyecto, TipoItem tipoItem, String descripcion) {
		return proyecto.getEstadoTipoItem(tipoItem,descripcion);
	}
	
	public Miembro getMiembro(Proyecto proyecto, String nombre){
		return proyecto.getMiembro(nombre);
	}
	
	public Role getRoleSistema(String descripcion){
	  	Iterator<Role> it = this.getRolesSistema().iterator();
	   	boolean notFound = true;
	   	Role rit, role = null; 
	    		
	   	while (it.hasNext() && notFound) {
	   		
			rit = (Role) it.next();
			if (rit.getNombre().equals(descripcion)){
				notFound = false;
				role = rit;
			}
		}
	   	return role;
	}
	    
	public Proyecto getProyectoPorNombre(String nombre){
	   	Iterator<Proyecto> it = this.getProyectos().iterator();
	   	boolean notFound = true;
	   	Proyecto pit, proyecto = null; 
	    		
	  	while (it.hasNext() && notFound) {
	    		
			pit = (Proyecto) it.next();
			if (pit.getNombre().equals(nombre)){
				notFound = false;
				proyecto = pit;
			}
		}
	   	return proyecto;
	}
	
	public Proyecto getProyecto(int id){
	   	Iterator<Proyecto> it = this.getProyectos().iterator();
	   	boolean notFound = true;
	   	Proyecto pit, proyecto = null; 
	    		
	  	while (it.hasNext() && notFound) {
	    		
			pit = (Proyecto) it.next();
			if (pit.getId() == id){
				notFound = false;
				proyecto = pit;
			}
		}
	   	return proyecto;
	}
	    
	public Usuario getUsuario(String nombre){

		Usuario user = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Usuario.class)
		.add(Restrictions.eq("nombre", nombre));
		
		user = (Usuario) criteria.uniqueResult();
		
		session.close();
		
		return user;
		
		
    }
	    
    public TipoItem getTipoItem(String descripcion, Proyecto proyecto){
    	Iterator<TipoItem> it = this.getTiposItems(proyecto).iterator();
    	boolean notFound = true;
    	TipoItem tit, tipoItem = null; 
    		
    	while (it.hasNext() && notFound) {
    		
			tit = (TipoItem) it.next();
			if (tit.getDescripcion().equals(descripcion)){
				notFound = false;
				tipoItem = tit;
			}
		}
    	return tipoItem;
    }
	    
    public Role getRoleProyecto(String descripcion){
    	Iterator<Role> it = this.getRolesProyecto().iterator();
    	boolean notFound = true;
    	Role rit, role = null; 
    		
    	while (it.hasNext() && notFound) {
    		
			rit = (Role) it.next();
			if (rit.getNombre().equals(descripcion)){
				notFound = false;
				role = rit;
			}
		}
    	return role;
    }
	    
    public Item getItem(Proyecto proyecto, String nombre){
    	return proyecto.getItem(nombre);
    }
    
    public String verEstadoActualItem(Proyecto proyecto, Item item){
    	return proyecto.verEstadoActualItem(item);
    }
    
    public void listarUsuarios(){
    	System.out.println("Usuarios del sistema:");
    	Iterator<Usuario> it = usuarios.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getNombre());
    		System.out.print(", ");
    	}
    	System.out.println("\n");
    }
    
    public void listarRolesSistema(){
    	System.out.println("Roles de sistema:");
    	Iterator<Role> it = rolesSistema.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getNombre());
    		System.out.print(", ");
    	}
    	System.out.println("\n");
    }
    
    public void listarRolesProyecto(){
    	System.out.println("Roles de proyecto:");
    	Iterator<Role> it = rolesProyecto.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getNombre());
    		System.out.print(", ");
    	}
    	System.out.println("\n");
    }
    
    public void listarTiposItem(Proyecto proyecto){
    	proyecto.listarTiposItem();
    }
    
    public void listarItems(Proyecto proyecto){
    	proyecto.listarItems();
    }
    
    public void listarEstadosPosibles(Proyecto proyecto, TipoItem tipo){
    	proyecto.listarEstadosPosibles(tipo);
    }
    
    public void listarEstadosSiguientes(Proyecto proyecto, TipoItem tipo, Estado estado){
    	proyecto.listarEstadosSiguientes(tipo, estado);
    }
    
    public HashSet<EstadoItem> getEstadosHistoricosItem(Proyecto proyecto, Item item, Date fec_inicio, Date fec_fin){
    	return proyecto.getEstadosHistoricosItem(item,fec_inicio,fec_fin);
    }
    
    public void listarEstadosHistoricosItem(Proyecto proyecto, Item item, Date fec_inicio, Date fec_fin){
	    System.out.println("Estados historicos de item:" + item.getNombre());
		Iterator<EstadoItem> it = this.getEstadosHistoricosItem(proyecto, item, fec_inicio, fec_fin).iterator();
		
		while(it.hasNext())
		{
			EstadoItem ei = it.next();
			if (ei.getEstado() != null)
			{
				System.out.println("Estado:" + ei.getEstado().getDescripcion() + " Fecha inicio:" + ei.getFechaInicio() + " Responsable:" + ei.getResponsable().getUsuario().getNombre());
			}
		}
		System.out.println("\n");
    }

	public void listarMiembros(Proyecto proyecto1) {
	    System.out.println("Miembros de proyecto:" + proyecto1.getNombre());
		Iterator<Miembro> it = proyecto1.getMiembros().iterator();
		Miembro mi;
		
		while(it.hasNext())
		{
			mi = (Miembro)it.next();
			if (mi.getUsuario() != null)
			{
				System.out.println(mi.getUsuario().getNombre());
			}
		}
		System.out.println("\n");
	}

	public boolean validarCredenciales(String usuario, String pass) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Usuario.class)
		.add(Restrictions.eq("nombre", usuario))
		.add(Restrictions.eq("clave", pass));
		
		Usuario user = (Usuario) criteria.uniqueResult();
		
		session.close();
		
		return (user != null);
	}

	
	public HashSet<Proyecto> listarProyectosUsuario(Usuario usuario){
		Iterator<Proyecto> pr = proyectos.iterator();
		Iterator<Miembro> mi;
		Proyecto proy;
		Collection<Miembro> miembros;
		Miembro miem;
		Collection<Proyecto> proyectosUsuario = new HashSet<Proyecto>();
		while(pr.hasNext())
		{
			proy = pr.next();
			miembros = proy.getMiembros();
			mi = miembros.iterator();
			while(mi.hasNext())
			{
				miem = mi.next();
				if(miem.getUsuario()== usuario){
					proyectosUsuario.add(proy);
				}
			}
		}
		return (HashSet<Proyecto>)proyectosUsuario;
	}
	

	public Usuario getUsuarioPorID(int id){
	   	Iterator<Usuario> it = this.getUsuarios().iterator();
	   	boolean notFound = true;
	   	Usuario uit, usuario = null; 
	    		
    	while (it.hasNext() && notFound) {
    		
			uit = (Usuario) it.next();
			if (uit.getId() == id){
				notFound = false;
				usuario = uit;
			}
		}
    	return usuario;
    }
	
	public Miembro getLiderProyecto(Proyecto proyecto)
	{
		return proyecto.getLiderProyecto();
	}
	
	public Collection<Miembro> getMiembrosProyecto(Proyecto proyecto1) {
		return proyecto1.getMiembros();	
	}
	
	public void setLiderProyecto(Proyecto proyecto, Miembro liderProyecto)
	{
		proyecto.setLiderProyecto(liderProyecto);
	}
	
	public boolean eliminarProyecto(Proyecto proyecto)
	{
		return this.proyectos.remove(proyecto);
	}
	

	public boolean eliminarUsuario(Usuario usuario)
	{
		return this.usuarios.remove(usuario);
	}
	
	public boolean eliminarItem(Proyecto proyecto, Item item)
	{
		return proyecto.eliminarItem(item);
	}
	
	public void editarMiembroProyecto(Proyecto proyecto, String nombreMiembro, String role)
	{
		Role roleMiembro = this.getRoleProyecto(role);
		proyecto.editarMiembro(nombreMiembro,roleMiembro);
	}
}
