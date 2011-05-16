package unlp.edu.core;

import java.util.*;


public class TipoItem {

	public String descripcion;
	public Estado estadoInicial;
	public Collection<Estado> estadosPosibles;
	
	public TipoItem(String descripcion, Estado estadoInicial, Collection<Estado> estadosPosibles) {
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
	
	public Estado agregarEstado(String descripcion) // crea un estado y lo agrega a la lista de estados del tipo
	{
		Estado estado = new Estado(descripcion);
		this.estadosPosibles.add(estado);
		return estado;
	}
	
	public void agregarEstadoSiguiente(Estado estadoI, Estado estadoF){
		if (this.estadosPosibles.contains(estadoI)) {
			estadoI.agregarEstadoSiguiente(estadoF);
		} else {
			System.out.println("Estado Inicial inexistente.");
		}
	}
	
	public void listarEstadosPosibles(){
		System.out.print("Estados Posibles del tipo de item ");
		System.out.print(this.getDescripcion());
		System.out.println(":");
		Iterator<Estado> it = estadosPosibles.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getDescripcion());
    		System.out.print(", ");
    	}
    	System.out.println();
	}
	
	public void listarEstadosSiguientes(Estado estado){
		estado.listarEstadosSiguientes();
	}
	
}
