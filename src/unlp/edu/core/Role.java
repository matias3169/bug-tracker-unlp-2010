/**
 * 
 */
package unlp.edu.core;


/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Role {

	private Long id;
	private String tipo;
	private String nombre;
	
	//Constructor de la clase
	public Role() {
	}
	
	//Constructor de la clase
	public Role(Long id, String tipo, String nombre) {
		this.tipo = tipo;
		this.id = id;
		this.nombre = nombre;
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

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
