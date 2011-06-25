package unlp.edu.core;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Usuario {

	private int id;
	private String clave;
	private String nombre;
	private Role role;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	/**
	 * @param nombre
	 * @param role
	 */
	public Usuario(int id, String nombre, String clave, Role role) {
		this.id = id;
		this.clave = clave;
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
