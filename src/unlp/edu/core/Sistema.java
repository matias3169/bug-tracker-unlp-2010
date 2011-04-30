/**
 * 
 */
package unlp.edu.core;

import java.util.Collection;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Sistema {
	
	private Collection<Proyecto> proyectos;
	private Collection<Role> roles;
	private Collection<Usuario> usuarios;

	public Sistema () 
	{
		System.out.println("Sistema creado");
	}
}
