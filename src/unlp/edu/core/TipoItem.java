package unlp.edu.core;

import java.util.Collection;


public class TipoItem {

	public String descripcion;
	public Estado estadoInicial;
	public Collection<Estado> estadosPosibles;
	
	public TipoItem(String descripcion, Estado estadoInicial,
			Collection<Estado> estadosPosibles) {
		this.descripcion = descripcion;
		this.estadoInicial = estadoInicial;
		this.estadosPosibles = estadosPosibles;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public Collection<Estado> getEstadosPosibles() {
		return estadosPosibles;
	}

	public void setEstadosPosibles(Collection<Estado> estadosPosibles) {
		this.estadosPosibles = estadosPosibles;
	}
	
	/**
	 * Metodos
	 */
	
	public Estado agregarEstado(String descripcion) // crea un estado y lo agrega a la lista de estados
	{
		Estado e= new Estado(descripcion);
		this.estadosPosibles.add(e);
		return e;
	}
	
	
	public Estado estadoInicial()
	{
		return this.estadoInicial;
	}

}
