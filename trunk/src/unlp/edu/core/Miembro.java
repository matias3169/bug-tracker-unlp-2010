package unlp.edu.core;


/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Miembro {

	private Usuario usuario;
	private Role role;
	private Proyecto proyecto;
	
	public Miembro(Usuario usuario, Role role, Proyecto proyecto){
		this.setUsuario(usuario);
		this.setRole(role);
		this.setProyecto(proyecto);
	}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	/**
	 * @return the proyecto
	 */
	public Proyecto getProyecto() {
		return proyecto;
	}
	/**
	 * @param proyecto the proyecto to set
	 */
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
