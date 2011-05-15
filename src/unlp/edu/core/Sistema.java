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
    	
    	rolesProyecto.add(new Role("Lider de Proyecto",this.getPermisosProyecto("Super")));
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
    
    public void nuevoMiembro(Proyecto proyecto, Usuario usuario, Role rol){
    	Miembro miembro = new Miembro(proyecto, usuario, rol);
    	proyecto.agregarMiembro(miembro);
    }
    
    public void nuevoEstado(Proyecto proyecto, TipoItem tipoItem, String descripcion){
    	proyecto.nuevoEstado(tipoItem, descripcion);
    }
    
    public void nuevoProyecto(String nombre, Usuario usuario){
    	Proyecto proyecto = new Proyecto(nombre);
    	Role rol = new Role("Lider",new HashSet<String>()); // Revisar como queremos poner el rol y los permisos
    	Miembro miembro = new Miembro(proyecto,usuario,rol);
    	proyecto.agregarMiembro(miembro);
    	this.proyectos.add(proyecto);
    }
    
    public void nuevoTipoItem(String descripcion, Proyecto proyecto){
    	proyecto.nuevoTipoItem(descripcion);
    }
    
    public void nuevoUsuario(String nombre, Role rolSistema){
    	this.usuarios.add(new Usuario(nombre, rolSistema));
    }
    
    public void cambiarEstadoItem(Proyecto proyecto, Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles){
    	proyecto.cambiarEstadoItem(item, estado, responsable, miembrosDisponibles);
    }
}
