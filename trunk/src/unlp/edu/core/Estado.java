package unlp.edu.core;

import java.util.*;


public class Estado {
	
	private String descripcion;
	private Collection<Estado> estadosSiguientes;
	
	public Estado(String descripcion)
	{
		this.descripcion= descripcion;
		this.estadosSiguientes = new HashSet<Estado>();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Estado> getEstadosSiguientes() {
		return estadosSiguientes;
	}

	public void setEstadosSiguientes(Collection<Estado> estadosSiguientes) {
		this.estadosSiguientes = estadosSiguientes;
	}
	
	/**
	 * Metodos
	 */

	public Estado cambiarEstado(Estado e)
	{
		/**
		 * ¿qué tiene que retornar???
		 */
		return e;
	}
	public void agregarEstadoSiguiente(Estado e)
	{
		this.estadosSiguientes.add(e);
	}
}
