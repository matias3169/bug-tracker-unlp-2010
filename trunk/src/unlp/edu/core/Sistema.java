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
	
	private Collection<Proyecto> proyectos;
	private Collection<Role> rolesSistema;
	private Collection<Role> rolesProyecto;
	private Collection<Usuario> usuarios;
	
	public Sistema () 
	{
		this.setProyectos(new HashSet<Proyecto>());
		this.setUsuarios(new HashSet<Usuario>());
		
		this.setRolesSistema();
		this.setRolesProyecto();

		System.out.println("Sistema creado");
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
    	
    	rolesSistema.add(new Role("Admin",this.getPermisosSistema("Super")));
    	rolesSistema.add(new Role("Developer",this.getPermisosSistema("Normal")));
    	rolesSistema.add(new Role("Guest",this.getPermisosSistema("")));
    }

	/**
	 * Establece roles del proyecto hardcodeados
	 * @return Lista de roles del proyecto
	 */
    private void setRolesProyecto(){
    	rolesProyecto = new HashSet<Role>();
    	
    	rolesProyecto.add(new Role("Lider",this.getPermisosProyecto("Super")));
    	rolesProyecto.add(new Role("Desarrollador",this.getPermisosProyecto("Normal")));
    	rolesProyecto.add(new Role("DBA",this.getPermisosProyecto("Normal")));
    	rolesProyecto.add(new Role("Tester",this.getPermisosProyecto("Normal")));
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
    	Proyecto proyecto = new Proyecto(nombre);
    	Role rol = new Role("Lider",this.getPermisosProyecto("Lider"));
    	Miembro miembro = new Miembro(proyecto,usuario,rol);
    	proyecto.agregarMiembro(miembro);
    	this.proyectos.add(proyecto);
    }
    
    public TipoItem nuevoTipoItem(String descripcion, Proyecto proyecto){
    	return proyecto.nuevoTipoItem(descripcion);
    }
    
    public Usuario nuevoUsuario(String nombre, Role rolSistema){
    	Usuario usuario = new Usuario(nombre, rolSistema);
    	this.usuarios.add(usuario);
    	return usuario;
    }
    
    public Role nuevoRolSistema(String descripcion){
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
    }
    
    public Item nuevoItem(Proyecto proyecto, String descripcion, TipoItem tipo, int prioridad2){
    	return proyecto.nuevoItem(descripcion, tipo, prioridad2);
    }
    
    public void cambiarEstadoItem(Proyecto proyecto, Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles){
    	proyecto.cambiarEstadoItem(item, estado, responsable, miembrosDisponibles);
    }
    
    public void agregarEstadoSiguiente(Proyecto proyecto, TipoItem tipo, Estado estadoI, Estado estadoF){
    	proyecto.agregarEstadoSiguiente(tipo, estadoI, estadoF);
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
    	System.out.println();
    }
    
    public void listarRolesSistema(){
    	System.out.println("Roles de sistema:");
    	Iterator<Role> it = rolesSistema.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getNombre());
    		System.out.print(", ");
    	}
    	System.out.println();
    }
    
    public void listarRolesProyecto(){
    	System.out.println("Roles de proyecto:");
    	Iterator<Role> it = rolesProyecto.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getNombre());
    		System.out.print(", ");
    	}
    	System.out.println();
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
}
