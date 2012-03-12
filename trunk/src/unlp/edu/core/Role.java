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
public class Role {

	private Long id;
	private String tipo;
	private String nombre;
	private Collection<String> permisos;
	
	//Constructor de la clase
	public Role(Long id, String tipo, String nombre, Collection<String> permisos) {
		this.tipo = tipo;
		this.id = id;
		this.nombre = nombre;
		this.permisos = permisos;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	public Long getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the permisos
	 */
	public Collection<String> getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(Collection<String> permisos) {
		this.permisos = permisos;
	}
	
	public void agregarPermiso(String descripcion){
		this.permisos.add(descripcion);
	}

}
