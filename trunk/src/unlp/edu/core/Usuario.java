package unlp.edu.core;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Usuario {

	private String nombre;
	private Role role;
	
	/**
	 * @param nombre
	 * @param role
	 */
	public Usuario(String nombre, Role role) {
		this.nombre = nombre;
		this.role = role;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
}
