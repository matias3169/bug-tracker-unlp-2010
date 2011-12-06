package unlp.edu.core;

import java.util.*;


public class Estado {
	
	private int id;
	private String descripcion;
	private Collection<Estado> estadosSiguientes;
	
	public Estado(int id, String descripcion){
		this.id = id;
		this.descripcion= descripcion;
		this.estadosSiguientes = new HashSet<Estado>();
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public int getId() {
		return id;
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

	public void agregarEstadoSiguiente(Estado e)
	{
		this.estadosSiguientes.add(e);
	}
	
	public void listarEstadosSiguientes(){
		System.out.print("Estados siguientes del estado ");
		System.out.print(this.getDescripcion());
		System.out.println(":");
		Iterator<Estado> it = estadosSiguientes.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getDescripcion());
    		System.out.print(", ");
    	}
    	System.out.println("\n");
	}
}
