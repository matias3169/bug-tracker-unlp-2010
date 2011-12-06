package unlp.edu.core;

import java.util.*;


public class TipoItem {

	private int id;
	private static int id_estado = 0;
	private String descripcion;
	private Estado estadoInicial;
	private Collection<Estado> estadosPosibles;
	
	public TipoItem(int id, String descripcion, Estado estadoInicial, Collection<Estado> estadosPosibles) {
		this.id = id;
		this.descripcion = descripcion;
		this.estadoInicial = estadoInicial;
		this.estadosPosibles = estadosPosibles;
	}

	private static int getIdEstado()
	{
		return id_estado;
	}
	
	private static void setIdEstado()
	{
		id_estado++;
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
	
	public Estado getEstadoPorId(int id){
	   	Iterator<Estado> it = this.getEstadosPosibles().iterator();
	   	boolean notFound = true;
	   	Estado eit, est = null; 
	    		
    	while (it.hasNext() && notFound) {
    		
			eit = (Estado) it.next();
			if (eit.getId() == id){
				notFound = false;
				est = eit;
			}
		}
    	return est;
    }

	public Estado getEstadoInicial() {
		return this.estadoInicial;
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
		setIdEstado();	
		Estado estado = new Estado(getIdEstado(),descripcion);
		this.estadosPosibles.add(estado);
		return estado;
	}

	public Estado getEstado(String descripcion) {
		Iterator<Estado> it = this.getEstadosPosibles().iterator();
    	boolean notFound = true;
    	Estado eit, estado = null; 
    		
    	while (it.hasNext() && notFound) {
    		
			eit = (Estado) it.next();
			if (eit.getDescripcion().equals(descripcion)){
				notFound = false;
				estado = eit;
			}
		}
    	return estado;
	}
	
	public void agregarEstadoSiguiente(String estadoI, String estadoF){
		this.getEstado(estadoI).agregarEstadoSiguiente(this.getEstado(estadoF));
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
    	System.out.println("\n");
	}
	
	public void listarEstadosSiguientes(Estado estado){
		estado.listarEstadosSiguientes();
	}
	
}
