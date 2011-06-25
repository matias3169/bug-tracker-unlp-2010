/**
 * 
 */
package unlp.edu.core;

import java.util.*;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Sistema {
	
	private static Sistema sistema;
	private static int id_proyecto = 0;
	private static int id_usuario = 0;
	private static int id_role = 0;
    private static boolean yaCreado = false;

	private Collection<Proyecto> proyectos;
	private Collection<Role> rolesSistema;
	private Collection<Role> rolesProyecto;
	private Collection<Usuario> usuarios;
	
	private Sistema(){
		this.setProyectos(new HashSet<Proyecto>());
		this.setUsuarios(new HashSet<Usuario>());
		this.setRolesSistema();
		this.setRolesProyecto();
	}

	public static Sistema getInstance() 
	{
        if(yaCreado == false) {
            sistema = new Sistema();
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

	private static int getIdUsuario()
	{
		return id_usuario;
	}
	
	private static void setIdUsuario()
	{
		id_usuario++;
	}
	
	private static int getIdRole()
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
	public Collection<Usuario> getUsuarios() {
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
    	rolesSistema.add(new Role(getIdRole(),"Sistema","Admin",this.getPermisosSistema("Super")));
    	setIdRole();
    	rolesSistema.add(new Role(getIdRole(),"Sistema","Developer",this.getPermisosSistema("Normal")));
    	setIdRole();
    	rolesSistema.add(new Role(getIdRole(),"Sistema","Guest",this.getPermisosSistema("")));
    }

	/**
	 * Establece roles del proyecto hardcodeados
	 * @return Lista de roles del proyecto
	 */
    private void setRolesProyecto(){
    	rolesProyecto = new HashSet<Role>();
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Lider",this.getPermisosProyecto("Super")));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Desarrollador",this.getPermisosProyecto("Normal")));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","DBA",this.getPermisosProyecto("Normal")));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Tester",this.getPermisosProyecto("Normal")));
    }
    
    private HashSet<String> getPermisosSistema(String tipoUsuario){
      	Collection<String> permisos = new HashSet<String>();
    	
    	if (tipoUsuario.equals("Super")){
    		permisos.add("Creacion");
    		permisos.add("Lectura");
    		permisos.add("Escritura");
    	}else {
    		if (tipoUsuario.equals("Normal")){
    			permisos.add("Escritura");
    			permisos.add("Lectura");
    		} else {
    			permisos.add("Lectura");
    		}
    	}
    
    	return (HashSet<String>)permisos;
    }
    
    private HashSet<String> getPermisosProyecto(String tipoUsuario){
      	Collection<String> permisos = new HashSet<String>();
    	
    	if (tipoUsuario.equals("Lider")){
    		permisos.add("Creacion");
    		permisos.add("Lectura");
    		permisos.add("Escritura");
    	}else {
    		if (tipoUsuario.equals("Normal")){
    			permisos.add("Escritura");
    			permisos.add("Lectura");
    		} else {
    			permisos.add("Lectura");
    		}
    	}
    
    	return (HashSet<String>)permisos;
    }
    
    public Miembro nuevoMiembro(Proyecto proyecto, Usuario usuario, Role rol){
    	Miembro miembro = new Miembro(proyecto, usuario, rol);
    	proyecto.agregarMiembro(miembro);
    	return miembro;
    }
    
    public Estado nuevoEstado(Proyecto proyecto, TipoItem tipoItem, String descripcion){
    	return proyecto.nuevoEstado(tipoItem, descripcion);
    }
    
    public void nuevoProyecto(String nombre, Usuario usuario){
    	setIdProyecto();
    	Proyecto proyecto = new Proyecto(getIdProyecto(), nombre);
    	Miembro miembro = new Miembro(proyecto,usuario,this.getRoleProyecto("Lider"));
    	proyecto.agregarMiembro(miembro);
    	this.proyectos.add(proyecto);
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
    
    /*public Role nuevoRolSistema(String descripcion){
    	Collection<String> permisos = getPermisosSistema(descripcion); 
    	Role rol = new Role(descripcion, permisos);
    	this.rolesSistema.add(rol);
    	return rol;
    }
    
    public Role nuevoRolProyecto(String descripcion){
    	Collection<String> permisos = getPermisosSistema(descripcion); 
    	Role rol = new Role(descripcion, permisos);
    	this.rolesProyecto.add(rol);
    	return rol;
    }*/
    
    public Item nuevoItem(String nombre, String descripcion, TipoItem tipo, int prioridad, Proyecto proyecto, Miembro responsable){
    	return proyecto.nuevoItem(nombre, descripcion, tipo, prioridad, responsable);
    }
    
    public void cambiarEstadoItem(Proyecto proyecto, Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fecha) throws Exception{
    	proyecto.cambiarEstadoItem(item, estado, responsable, miembrosDisponibles, fecha);
    }
    
	public void agregarEstadoSiguiente(Proyecto proyecto, TipoItem tipo, String estadoI, String estadoF){
	    	proyecto.agregarEstadoSiguiente(tipo, estadoI, estadoF);
	}
    
    public Collection<TipoItem> getTiposItems(Proyecto proyecto){
    	return proyecto.getTiposItems();
    }

	public Estado getEstado(Proyecto proyecto, TipoItem tipoItem, String descripcion) {
		return proyecto.getEstado(tipoItem,descripcion);
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
	    
	public Proyecto getProyecto(String descripcion){
	   	Iterator<Proyecto> it = this.getProyectos().iterator();
	   	boolean notFound = true;
	   	Proyecto pit, proyecto = null; 
	    		
	  	while (it.hasNext() && notFound) {
	    		
			pit = (Proyecto) it.next();
			if (pit.getNombre().equals(descripcion)){
				notFound = false;
				proyecto = pit;
			}
		}
	   	return proyecto;
	}
	    
	public Usuario getUsuario(String nombre){
	   	Iterator<Usuario> it = this.getUsuarios().iterator();
	   	boolean notFound = true;
	   	Usuario uit, usuario = null; 
	    		
    	while (it.hasNext() && notFound) {
    		
			uit = (Usuario) it.next();
			if (uit.getNombre().equals(nombre)){
				notFound = false;
				usuario = uit;
			}
		}
    	return usuario;
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
		if (validarUsuario(usuario,pass))
		{
			return true;
		} else
		{
			return false;
		}
		
	}

	private boolean validarUsuario(String usuario, String pass) {
		Usuario user = this.getUsuario(usuario);
		if ( user == null)
		{
			return false;
		} else
		{
			//aca hay que agregar validacion de password
			return true;
		}
		
	}
}
