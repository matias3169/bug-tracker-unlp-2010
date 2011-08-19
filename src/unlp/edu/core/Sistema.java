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
		this.setRolesSistema();
		this.setRolesProyecto();
		this.setUsuarios(new HashSet<Usuario>());
	}

	public static Sistema getInstance() 
	{
        if(yaCreado == false) {
            sistema = new Sistema();
    		Usuario usuario1 = sistema.nuevoUsuario("admin", "admin", sistema.getRoleSistema("Administrador"));
    		Usuario usuario2 = sistema.nuevoUsuario("guest", "guest", sistema.getRoleSistema("Desarrollador"));
    		
    		Proyecto proyecto1 = sistema.nuevoProyecto("PROYECTO1", usuario1);
    		Proyecto proyecto2 = sistema.nuevoProyecto("PROYECTO2", usuario2);
    		Proyecto proyecto3 = sistema.nuevoProyecto("PROYECTO3", usuario1);
    		
    		//Miembro miembro1=sistema.nuevoMiembro(proyecto1, usuario1, sistema.getRoleSistema("Administrador"));
    		//Miembro miembro2=sistema.nuevoMiembro(proyecto2, usuario1, sistema.getRoleSistema("Administrador"));
    		
    		TipoItem tipo1 = sistema.nuevoTipoItem("Reporte de Bug", proyecto1);
    		
    		sistema.nuevoEstado(proyecto1, tipo1, "Creado");
    		sistema.nuevoEstado(proyecto1, tipo1, "Desarrollo");
    		sistema.nuevoEstado(proyecto1, tipo1, "Validacion");
    		sistema.nuevoEstado(proyecto1, tipo1, "Terminado");
    		
    		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Creado", "Desarrollo");
    		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Desarrollo", "Validacion");
    		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Validacion", "Terminado");
    		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Validacion", "Desarrollo");
    		
    		
    		sistema.nuevoItem("ITEM1", "DESCRIPCION1", tipo1, 1,proyecto1,sistema.getMiembro(proyecto1, "admin"));
    		sistema.nuevoItem("ITEM2", "DESCRIPCION2", tipo1, 1,proyecto1,sistema.getMiembro(proyecto1, "admin"));

    		sistema.nuevoItem("ITEM3", "DESCRIPCION3", tipo1, 1,proyecto2,sistema.getMiembro(proyecto2, "guest"));
    		sistema.nuevoItem("ITEM4", "DESCRIPCION4", tipo1, 1,proyecto2,sistema.getMiembro(proyecto2, "guest"));

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
    	rolesSistema.add(new Role(getIdRole(),"Sistema","Administrador",this.getPermisosSistema("Administrador")));
    	setIdRole();
    	rolesSistema.add(new Role(getIdRole(),"Sistema","Desarrollador",this.getPermisosSistema("Desarrollador")));
    }

	/**
	 * Establece roles del proyecto hardcodeados
	 * @return Lista de roles del proyecto
	 */
    private void setRolesProyecto(){
    	rolesProyecto = new HashSet<Role>();
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Lider",this.getPermisosProyecto("Lider")));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Desarrollador",this.getPermisosProyecto("Desarrollador")));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","DBA",this.getPermisosProyecto("DBA")));
    	setIdRole();
    	rolesProyecto.add(new Role(getIdRole(),"Proyecto","Tester",this.getPermisosProyecto("Tester")));
    }
    
    private HashSet<String> getPermisosSistema(String role){
      	Collection<String> permisos = new HashSet<String>();
    	
    	if (role.equals("Administrador")){
    		permisos.add("usuarios");
    		permisos.add("proyectos");
    	}else {
    		if (role.equals("Desarrollador")){
    			permisos.add("proyectos");
    		} 
    	}
    
    	return (HashSet<String>)permisos;
    }
    
    private HashSet<String> getPermisosProyecto(String role){
      	Collection<String> permisos = new HashSet<String>();
    	
    	if (role.equals("Lider")){
    		permisos.add("Creacion");
    		permisos.add("Lectura");
    		permisos.add("Escritura");
    	}else {
    		if (role.equals("Desarrollador") || role.equals("DBA") || role.equals("Tester")){
    			permisos.add("Escritura");
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
    
    public Proyecto nuevoProyecto(String nombre, Usuario usuario){
    	setIdProyecto();
    	Proyecto proyecto = new Proyecto(getIdProyecto(), nombre);
    	Miembro miembro = new Miembro(proyecto,usuario,this.getRoleProyecto("Lider"));
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
		Usuario usuarioValidado = validarUsuario(usuario);
		if (usuarioValidado != null)
		{
			if (usuarioValidado.validarClave(pass))
			{
				return true;
			} else
			{
				//clave invalida
				return false;
			}
		} else
		{
			//usuario invalido
			return false;
		}
		
	}

	private Usuario validarUsuario(String usuario) {
		Usuario user = this.getUsuario(usuario);
		if ( user == null)
		{
			//usuario invalido
			return null;
		} else
		{	
			return user;
		}
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
	
	
}
