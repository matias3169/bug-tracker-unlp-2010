/**
 * 
 */
package unlp.edu.core;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import unlp.edu.util.HibernateUtil;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Sistema {
	
	private static Sistema sistema;
    private static boolean yaCreado = false;

	private Collection<Usuario> usuarios = new HashSet<Usuario>();
	
	private Sistema(){
	}

	public static Sistema getInstance() 
	{
        if(yaCreado == false) {
            sistema = new Sistema();
            yaCreado = true;
      }
      return sistema;
	}
	
	
	/**
	 * @return the roles
	 */
	@SuppressWarnings("unchecked")
	public Collection<Role> getRolesSistema() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Role.class)
		.add(Restrictions.eq("tipo", "Sistema"));
		
		List<Role> roles = criteria.list();
		
		session.close();
		
		return roles;
	}

	/**
	 * @return the roles
	 */
	@SuppressWarnings("unchecked")
	public Collection<Role> getRolesProyecto() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Role.class)
		.add(Restrictions.eq("tipo", "Proyecto"));
		
		List<Role> roles = criteria.list();
		
		session.close();
		
		return roles;
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
				
		usuarios = (Collection<Usuario>) criteria.list();
		
		session.close();
		
		return usuarios;
	}
  
    
    public Miembro nuevoMiembro(Proyecto proyecto, Usuario usuario, Role rol){

    	Miembro miembro = new Miembro();
    	miembro.setUsuario(usuario);
    	miembro.setRole(rol);

    	miembro.setProyecto(proyecto);
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Miembro savedMiembro = (Miembro) session.merge(miembro);
    	session.getTransaction().commit();
    	
    	return savedMiembro;
    }
    
   
    public Proyecto nuevoProyecto(String nombre, String usuario){

    	Usuario savedUsuario = this.getUsuario(usuario);
    	Role savedRole = this.getRoleProyecto("Lider");

    	Proyecto proyecto = new Proyecto();
    	proyecto.setNombre(nombre);
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
    	Proyecto savedProyecto = (Proyecto) session.merge(proyecto);
    	session.getTransaction().commit();

    	Miembro savedMiembro = this.nuevoMiembro(savedProyecto, savedUsuario, savedRole);
    	savedProyecto.setLiderProyecto(savedMiembro);
    	
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(savedProyecto);
    	session.getTransaction().commit();

    	return proyecto;
    }
    
    public TipoItem nuevoTipoItem(String descripcion, Proyecto proyecto){
    	TipoItem tipoItem = new TipoItem();
    	tipoItem.setDescripcion(descripcion);
    	tipoItem.setProyecto(proyecto);
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
  
    	TipoItem savedTipoItem = (TipoItem) session.merge(tipoItem);
    	session.getTransaction().commit();
    	    	
    	return savedTipoItem;
    	    	
    }
      

	public void nuevoItem(String nombre, String descripcion, TipoItem tipo, int prioridad, Proyecto proyecto, Miembro responsable){
		Session session = null;
		
		//Crear un nuevo Item
		Item item = new Item();
		item.setNombre(nombre);
		item.setDescripcion(descripcion);
		item.setTipoItem(tipo);
		item.setPrioridad(prioridad);
		item.setResponsable(responsable);
		item.setProyecto(proyecto);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
    	Item savedItem = (Item) session.merge(item);
    	session.getTransaction().commit();
    	
		Estado estadoInicial = tipo.getEstadoInicial();
		
		//Crear un nuevo EstadoItem para el nuevoItem
		EstadoItem estadoItem = new EstadoItem();
		estadoItem.setEstado(estadoInicial);
		estadoItem.setFechaInicio(new Date());
		estadoItem.setResponsable(responsable);
		estadoItem.setItem(savedItem);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
    	EstadoItem savedEstadoItem = (EstadoItem) session.merge(estadoItem);
    	session.getTransaction().commit();
    	
		//Crear un nuevo EstadoHistorico para el nuevoItem
		EstadoHistorico estadosHistoricos = new EstadoHistorico();
		estadosHistoricos.setEstadoActual(savedEstadoItem);
		estadosHistoricos.setEstadoHistorico(savedEstadoItem);
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EstadoHistorico savedEstadosHistoricos = (EstadoHistorico) session.merge(estadosHistoricos);
    	session.getTransaction().commit();

    	savedItem.setEstadoActual(savedEstadoItem);
    	savedItem.setHistorialEstados(savedEstadosHistoricos);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
    	session.update(savedItem);
    	session.getTransaction().commit();
    }
    
    public void cambiarEstadoItem(Item item, Estado estado, Miembro responsable, String fichaTrabajo) throws Exception{
    	Session session = null;
    	
		EstadoItem estadoActual = item.getEstadoActual();
		estadoActual.setFichaDeTrabajo(fichaTrabajo);
		estadoActual.setFechaFin(new Date());
		
		//Actualizamos EstadoItem actual
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EstadoItem savedEstadoActual = (EstadoItem) session.merge(estadoActual);
		session.getTransaction().commit();
		
		//Creamos el nuevo estadoItem
		EstadoItem nuevoEstadoItem = new EstadoItem();
		nuevoEstadoItem.setEstado(estado);
		nuevoEstadoItem.setFechaInicio(new Date());
		nuevoEstadoItem.setResponsable(responsable);
		nuevoEstadoItem.setItem(item);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EstadoItem savedEstadoItem = (EstadoItem) session.merge(nuevoEstadoItem);
		session.getTransaction().commit();

		//Actualizamos Item con nuevo Estado Actual
		item.setEstadoActual(savedEstadoItem);
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();

		//Guardamos el nuevo historico
		this.nuevoEstadoHistorico(savedEstadoActual, savedEstadoItem);
    
    }
    
	public void agregarEstadoSiguiente(TipoItem tipo, Estado estadoI, Estado estadoF){

		Estado_EstadoSiguiente estado_estadoSiguiente = new Estado_EstadoSiguiente();
		estado_estadoSiguiente.setEstadoInicial(estadoI);
		estado_estadoSiguiente.setEstadoSiguiente(estadoF);

    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
    	session.save(estado_estadoSiguiente);
    	session.getTransaction().commit();
	}	
    
	@SuppressWarnings("unchecked")
    public Collection<TipoItem> getTiposItems(Proyecto proyecto){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(TipoItem.class)
		.add(Restrictions.eq("proyecto", proyecto));
		
		List<TipoItem> tipoItems = criteria.list();
		
		session.close();

		return tipoItems;
		
    }

	public Estado getEstadoTipoItem(TipoItem tipoItem, String descripcionEstado) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Estado.class)
		.add(Restrictions.eq("descripcion", descripcionEstado))
		.add(Restrictions.eq("tipoItem", tipoItem));
		
		Estado estado = (Estado) criteria.uniqueResult();
		
		session.close();

		return estado;
	}

	public Estado getEstadoID(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Estado.class)
		.add(Restrictions.eq("id", id));
		
		Estado estado = (Estado) criteria.uniqueResult();
		
		session.close();

		return estado;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Estado> getEstadosTipoItem(TipoItem tipoItem) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Estado.class)
		.add(Restrictions.eq("tipoItem", tipoItem));
		
		List<Estado> estados = criteria.list();
		
		session.close();

		return estados;

	}
	
	public Miembro getMiembro(Proyecto savedProyecto, String nombre){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	
		Criteria criteria = session.createCriteria(Miembro.class)
		.add(Restrictions.eq("proyecto", savedProyecto))
		.createCriteria("usuario")
		.add(Restrictions.eq("nombre", nombre));
		
		Miembro savedMiembro = (Miembro) criteria.uniqueResult();
		
		session.close();
		
		return savedMiembro;
	}
	
	public Role getRoleSistema(String descripcion){
		Role role = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Role.class)
		.add(Restrictions.eq("nombre", descripcion))
		.add(Restrictions.eq("tipo", "Sistema"));
		
		role = (Role) criteria.uniqueResult();
		
		session.close();
		
		return role;
	}
	    
	public Proyecto getProyectoPorNombre(String nombre){
		Proyecto proyecto = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Proyecto.class)
		.add(Restrictions.eq("nombre", nombre));
		
		proyecto = (Proyecto) criteria.uniqueResult();
		
		session.close();
		
		return proyecto;
	}
	
	public Proyecto getProyecto(Long id){
		Proyecto proyecto = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Proyecto.class)
		.add(Restrictions.eq("id", id));

		proyecto = (Proyecto) criteria.uniqueResult();
		
		session.close();
		
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
    	TipoItem tipoItem = null;
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(TipoItem.class)
		.add(Restrictions.eq("descripcion",descripcion))
		.add(Restrictions.eq("proyecto",proyecto));
		
		tipoItem = (TipoItem) criteria.uniqueResult();
		
		session.close();
		
		return tipoItem;
    
    }
    
    public TipoItem getTipoItemID(Long id){
    	TipoItem tipoItem = null;
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(TipoItem.class)
		.add(Restrictions.eq("id",id));
		
		tipoItem = (TipoItem) criteria.uniqueResult();
		
		session.close();
		
		return tipoItem;
    
    }

	    
    public Role getRoleProyecto(String descripcion){
		Role role = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Role.class)
		.add(Restrictions.eq("nombre", descripcion))
		.add(Restrictions.eq("tipo", "Proyecto"));

		role = (Role) criteria.uniqueResult();
		
		session.close();
		
		return role;
    }
	    
    public Item getItem(Proyecto proyecto, String nombre){
		Item item = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Item.class)
		.add(Restrictions.eq("nombre", nombre))
		.add(Restrictions.eq("proyecto", proyecto));

		item = (Item) criteria.uniqueResult();
		
		session.close();
		
		return item;
    }
    
    public Item getItemId(Long id){
		Item item = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Item.class)
		.add(Restrictions.eq("id", id));

		item = (Item) criteria.uniqueResult();
		
		session.close();
		
		return item;
    }

	@SuppressWarnings("unchecked")
    public Collection<Item> getItems(Proyecto proyecto){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Item.class)
		.add(Restrictions.eq("proyecto", proyecto));

		List<Item> items = criteria.list();
		
		session.close();
		
		return items;
    }
    
      
	@SuppressWarnings("unchecked")
    public Collection<EstadoItem> getEstadosHistoricosItem(Item item){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(EstadoHistorico.class)
		.setProjection(Projections.property("estadoHistorico"))
		.createCriteria("estadoActual")
		.add(Restrictions.eq("item", item))
		.addOrder(Order.asc("fechaInicio") );
		
		List<EstadoItem> estadosHistoricos = criteria.list();

		session.close();
		
		return estadosHistoricos;
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

	@SuppressWarnings("unchecked")
	public Collection<Proyecto> listarProyectosUsuario(Usuario usuario){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Miembro.class)
		.add(Restrictions.eq("usuario", usuario))
		.setProjection(Projections.property("proyecto"));
		
		List<Proyecto> proyectos = criteria.list();
		
		session.close();

		return proyectos;
	}
	

	public Usuario getUsuarioPorID(Long id){
		Usuario user = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Usuario.class)
		.add(Restrictions.idEq(id));
		
		user = (Usuario) criteria.uniqueResult();
		
		session.close();
		
		return user;
    }
	
	@SuppressWarnings("unchecked")
	public Collection<Miembro> getMiembrosProyecto(Proyecto proyecto1) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Miembro.class)
		.createCriteria("proyecto")
		.add(Restrictions.eq("id", proyecto1.getId()));
		
		List<Miembro> miembros = criteria.list();
		
		session.close();
		
		return miembros;	
	}
	
	public boolean eliminarProyecto(Proyecto proyecto)
	{
		//Eliminamos los items del proyecto
		Collection<Item> items = this.getItems(proyecto);
		for (Item item : items)
		{
			this.eliminarItem(item);
		}
		
		//Eliminamos los tipos items del proyecto
		Collection<TipoItem> tiposItems = this.getTiposItems(proyecto);
		for (TipoItem tipoItem : tiposItems)
		{
			this.eliminarTipoItem(tipoItem);
		}
		
		//Eliminamos los miembros del proyecto
		Collection<Miembro> miembros = this.getMiembrosProyecto(proyecto);
		for (Miembro miembro : miembros) {
			if (!proyecto.getLiderProyecto().equals(miembro))
			{
				this.eliminarMiembroProyecto(miembro);
			}
		}
				
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.delete(proyecto);
		session.getTransaction().commit();
		
		return true;
	}
	
	private void eliminarTipoItem(TipoItem tipoItem) {

		//Eliminamos todos los estados del tipoItem 
		Collection<Estado> estados = this.getEstadosTipoItem(tipoItem);
		for (Estado estado : estados)
		{
			this.eliminarEstadoTipoItem(estado, tipoItem);	
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(tipoItem);
		session.getTransaction().commit();
	}

	public void eliminarItem(Item item)
	{
		item.setHistorialEstados(null);
		item.setEstadoActual(null);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Item savedItem = (Item) session.merge(item);
		session.getTransaction().commit();
		
		Collection<EstadoItem> estadosItems = this.getEstadosItem(savedItem);
		for (EstadoItem estadoItem : estadosItems) {
			this.EliminarEstadoItem(estadoItem);
		}
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(savedItem);
		session.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	private Collection<EstadoItem> getEstadosItem(Item item) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(EstadoItem.class)
		.add(Restrictions.eq("item", item));
		
		List<EstadoItem> estadosItem = criteria.list();
		
		session.close();

		return estadosItem;
	}

	public void editarMiembroProyecto(Proyecto proyecto, String nombreMiembro, String role)
	{
		Role roleMiembro = this.getRoleProyecto(role);
		Miembro miembro = this.getMiembro(proyecto, nombreMiembro);
		
		if (!miembro.getRole().getId().equals(this.getRoleProyecto(role).getId()))
		{
			miembro.setRole(roleMiembro);
			
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
	    	session.update(miembro);
	    	session.getTransaction().commit();
		}
		
	}

	public void eliminarMiembroProyecto(Miembro miembro) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Miembro.class)
		.add(Restrictions.eq("id", miembro.getId()));
		Miembro savedMiembro = (Miembro) criteria.uniqueResult();

		session.delete(savedMiembro);
		session.getTransaction().commit();
				
	}

	public void agregarEstado(TipoItem savedTipoItem, String descripcionEstado) {
		Estado estado = new Estado();
		estado.setDescripcion(descripcionEstado);
		estado.setTipoItem(savedTipoItem);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(estado);
		session.getTransaction().commit();	
	}

	public void eliminarEstadoTipoItem(Estado savedEstado, TipoItem savedTipoItem){
	
		Session session;
		
		//Eliminamos todos los miembros del Estado
		Collection<Miembro> miembros = this.getMiembrosEstado(savedEstado);
		for (Miembro miembro : miembros)
		{
			this.eliminarMiembroEstado(miembro, savedEstado);
		}

		
		if (savedTipoItem.getEstadoInicial() != null)
		{
			/*Debemos setear en null estado inicial antes de empezar a borrar
			 * para evitar violacion de contraint
			*/
			if (savedTipoItem.getEstadoInicial().equals(savedEstado))
			{
				savedTipoItem.setEstadoInicial(null);
				
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				session.update(savedTipoItem);
				session.getTransaction().commit();
			}
		}

		//Eliminamos todos los estados Siguientes al Estado
		this.eliminarEstadosSiguientes(savedEstado);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(savedEstado);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	private void eliminarEstadosSiguientes(Estado savedEstado) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//Criteria criteria = session.createCriteria(Estado_EstadoSiguiente.class)
		//.add(Restrictions.eq("EstadoInicial", savedEstado));
		Criteria criteria = session.createCriteria(Estado_EstadoSiguiente.class)
		.add(Restrictions.disjunction().add(Restrictions.eq("EstadoInicial", savedEstado)).add(Restrictions.eq("EstadoSiguiente", savedEstado)));
		
		List<Estado_EstadoSiguiente> estadosSiguientes = criteria.list();
		session.close();
		
		//Eliminamos los estados siguientes al estado inicial
		for (Estado_EstadoSiguiente estado_siguiente : estadosSiguientes) {
			this.EliminarEstado_EstadoSiguiente(estado_siguiente);
		}

		
		//session = HibernateUtil.getSessionFactory().getCurrentSession();
		//session.beginTransaction();
		//criteria = session.createCriteria(Estado_EstadoSiguiente.class)
		//.add(Restrictions.eq("EstadoSiguiente", savedEstado));
		
		//List<Estado_EstadoSiguiente> estadosApuntanInicial = criteria.list();
		//session.close();

		//Eliminamos los estados siguientes del indicado
		//for (Estado_EstadoSiguiente estado_siguiente : estadosApuntanInicial) {
		//	session = HibernateUtil.getSessionFactory().getCurrentSession();
		//	session.beginTransaction();
			
		//	session.delete(estado_siguiente);
		//	session.getTransaction().commit();
		//}
		//Eliminamos los estados siguientes que apuntan al indicado
		
	}

	private void EliminarEstado_EstadoSiguiente(Estado_EstadoSiguiente estado_siguiente) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(estado_siguiente);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
    public Collection<Estado> getEstadosSiguientes(Estado estado){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Estado_EstadoSiguiente.class)
		.add(Restrictions.eq("EstadoInicial", estado))
		.setProjection(Projections.property("EstadoSiguiente"));
		
		List<Estado> estadosSiguientes = criteria.list();
		
		session.close();
		
		return estadosSiguientes;	
    }
	

    public Collection<Estado> getEstadosDisponibles(Estado estado){
			
		Collection<Estado> todosEstadosItems = this.getEstadosTipoItem(estado.getTipoItem());
		Collection<Estado> estadosSiguientes = this.getEstadosSiguientes(estado);
		
		for (Estado estadoItem : estadosSiguientes) {
			if (todosEstadosItems.contains(estadoItem))
			{
				todosEstadosItems.remove(estadoItem);
			}		
		}
		
		return todosEstadosItems;	
    }

	public void agregarMiembroDisponible(Miembro nuevoMiembro, Estado estadoItem) {
		Estado_Miembro estadoMiembro = new Estado_Miembro();
		estadoMiembro.setEstado(estadoItem);
		estadoMiembro.setMiembro(nuevoMiembro);
		
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
    	session.save(estadoMiembro);
    	session.getTransaction().commit();

	}
	
    public Collection<Miembro> getMiembrosDisponibles(Estado estado){
		
    	Collection<Miembro> miembrosProyecto = this.getMiembrosProyecto(estado.getTipoItem().getProyecto());
    	Collection<Miembro> miembrosEstado = this.getMiembrosEstado(estado);
    	
		for (Miembro miembro : miembrosEstado) {
			if (miembrosProyecto.contains(miembro))
			{
				miembrosProyecto.remove(miembro);
			}		
		}
		
		return miembrosProyecto;	
    }
    
	@SuppressWarnings("unchecked")
	public Collection<Miembro> getMiembrosEstado(Estado estado) {
		List<Miembro> miembrosEstado = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Estado_Miembro.class)
		.add(Restrictions.eq("estado", estado))
		.setProjection(Projections.property("miembro"));
		
		miembrosEstado = criteria.list();
		
		session.close();
		
		return miembrosEstado;
	}

	public void eliminarEstadoSiguiente(Estado estadoInicial,
			Estado estadoSiguiente) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Estado_EstadoSiguiente.class)
		.add(Restrictions.eq("EstadoInicial", estadoInicial))
		.add(Restrictions.eq("EstadoSiguiente", estadoSiguiente));
		
		Estado_EstadoSiguiente savedEstadoEstadoSig = (Estado_EstadoSiguiente) criteria.uniqueResult();
		session.close();
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(savedEstadoEstadoSig);
		session.getTransaction().commit();

	}

	public void eliminarMiembroEstado(Miembro miembro, Estado estado) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Estado_Miembro.class)
		.add(Restrictions.eq("estado", estado))
		.add(Restrictions.eq("miembro", miembro));
		
		Estado_Miembro savedEstadoMiembro = (Estado_Miembro) criteria.uniqueResult();
		session.close();
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(savedEstadoMiembro);
		session.getTransaction().commit();
		
	}

	public void editarItem(Item item, Miembro nuevoResponsable,
			String descripcion, String prioridad, String fichaTrabajo) {
		
		Session session = null;
		EstadoItem estadoItem = item.getEstadoActual();
		estadoItem.setFichaDeTrabajo(fichaTrabajo);
		
		item.setDescripcion(descripcion);
		item.setPrioridad(Integer.parseInt(prioridad));
		
		if (!estadoItem.getResponsable().equals(nuevoResponsable))
		{
			EstadoItem nuevoEstadoItem = new EstadoItem();
			nuevoEstadoItem.setEstado(estadoItem.getEstado());
			nuevoEstadoItem.setFechaInicio(new Date());
			nuevoEstadoItem.setResponsable(nuevoResponsable);
			nuevoEstadoItem.setItem(item);
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			EstadoItem savedEstadoItem = (EstadoItem) session.merge(nuevoEstadoItem);
			session.getTransaction().commit();
			
			estadoItem.setFechaFin(new Date());
		
			item.setEstadoActual(savedEstadoItem);
			item.setResponsable(nuevoResponsable);
	
			this.nuevoEstadoHistorico(estadoItem, savedEstadoItem);
			
		} 
		
		//Actualizamos EstadoItem
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(estadoItem);
		session.getTransaction().commit();
		
		//Actualizamos Item
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
	}

	public void nuevoEstadoHistorico(EstadoItem estadoItem,
			EstadoItem estadoHistoricoSiguiente) {
		EstadoHistorico estadoHistorico = new EstadoHistorico();
		estadoHistorico.setEstadoActual(estadoItem);
		estadoHistorico.setEstadoHistorico(estadoHistoricoSiguiente);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(estadoHistorico);
		session.getTransaction().commit();
		
	}

	public void nuevoUsuario(String nombreUsuario, String claveUsuario,
			Role savedRole) {
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(nombreUsuario);
		nuevoUsuario.setClave(claveUsuario);
		nuevoUsuario.setRole(savedRole);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
        session.save(nuevoUsuario);
        session.getTransaction().commit();
		
	}

	public void editarProyecto(Proyecto savedProyecto,
			String nuevoNombreProyecto, Miembro nuevoLider) {
		
		savedProyecto.setNombre(nuevoNombreProyecto);
		savedProyecto.setLiderProyecto(nuevoLider);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(savedProyecto);
		session.getTransaction().commit();		
	}

	public void editarMiembro(Miembro nuevoLider, Role roleLider) {
		
		nuevoLider.setRole(roleLider);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(nuevoLider);
		session.getTransaction().commit();		
	}

	public void editarTipoItem(TipoItem savedTipoItem,
			String descripcionNuevaTipoItem, Estado estadoIni) {
		
		savedTipoItem.setDescripcion(descripcionNuevaTipoItem);
		savedTipoItem.setEstadoInicial(estadoIni);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(savedTipoItem);
		session.getTransaction().commit();
		
	}

	public void editarUsuario(Usuario usuario, Role role,
			String nuevaClave) {
		
		//Seteo nuevos valores
		usuario.setRole(role);
		usuario.setClave(nuevaClave);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		session.update(usuario);
		session.getTransaction().commit();		
	}

	public void eliminarUsuario(Usuario savedUsuario) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(savedUsuario);
		session.getTransaction().commit();
		
	}
	
	public void EliminarEstadoItem(EstadoItem estadoItem){
		
		this.eliminarHistoricosItem(estadoItem.getItem());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(estadoItem);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public void eliminarHistoricosItem(Item item) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(EstadoHistorico.class)
		.createCriteria("estadoActual")
		.add(Restrictions.eq("item", item));

		List<EstadoHistorico> estadosHistoricos = criteria.list();
		
		session.close();
		
		for (EstadoHistorico estadoHistorico : estadosHistoricos) {
			this.eliminarEstadoHistorico(estadoHistorico);
		}	
		
	}

	public void eliminarEstadoHistorico(EstadoHistorico estadoHistorico) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(estadoHistorico);
		session.getTransaction().commit();
	}
    
}
