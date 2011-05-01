/**
 * 
 */
package unlp.edu.core;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Role {

	private String nombre;
	private Collection<String> permisos;
	
	
	public Role(String nombre, Collection<String> permisos) {
		this.setNombre(nombre);
		this.setPermisos(permisos);
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
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

}
