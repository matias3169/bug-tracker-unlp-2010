/**
 * 
 */
package unlp.edu.core;

import java.util.*;

/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Proyecto {

	private String nombre;
	private Collection<Item> items;
	private Collection<TipoItem> tiposItems;
	private Collection<Miembro> miembros;
	
	public Proyecto(String nombre) {
		this.setNombre(nombre);
		this.setItems(new HashSet<Item>());
		this.setTiposItems(new HashSet<TipoItem>());
		this.setMiembros(new HashSet<Miembro>());
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
	 * @return the items
	 */
	public Collection<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	/**
	 * @return the tiposItems
	 */
	public Collection<TipoItem> getTiposItems() {
		return tiposItems;
	}

	/**
	 * @param tiposItems the tiposItems to set
	 */
	public void setTiposItems(Collection<TipoItem> tiposItems) {
		this.tiposItems = tiposItems;
	}

	/**
	 * @return the miembros
	 */
	public Collection<Miembro> getMiembros() {
		return miembros;
	}

	/**
	 * @param miembros the miembros to set
	 */
	public void setMiembros(Collection<Miembro> miembros) {
		this.miembros = miembros;
	}
	
	public Estado nuevoEstado(TipoItem tipo, String descripcion){ //crea un nuevo estado y lo agrega a la lista de estados del TipoItem
		 return tipo.agregarEstado(descripcion);
	}
	
	public Item nuevoItem(String desc, TipoItem tipo, int prioridad1){
		Item item = new Item(desc, tipo, prioridad1);
		this.items.add(item);
		return item;
	}
	
	public void agregarMiembro(Miembro miembro){
		this.miembros.add(miembro);
	}
	
	public TipoItem nuevoTipoItem(String descripcion){ //creo el tipo de item sin estados
		TipoItem tipo = new TipoItem(descripcion, null, new HashSet<Estado>());
		this.tiposItems.add(tipo);
		return tipo;
	}
	
	public void cambiarEstadoItem( Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles){
		item.cambiarEstadoItem(estado, responsable, miembrosDisponibles);
	}
	public void agregarEstadoSiguiente(TipoItem tipo, Estado estadoI, Estado estadoF){
		tipo.agregarEstadoSiguiente(estadoI, estadoF);
	}
	
	public String verEstadoActualItem(Item item){
		return item.verEstadoActual();
	}
	
	public void listarTiposItem(){
		System.out.println("Tipos de item del proyecto:");
		Iterator<TipoItem> it = tiposItems.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getDescripcion());
    		System.out.print(", ");
    	}
    	System.out.println();
	}
	public void listarItems(){
		System.out.print("Items del proyecto ");
		System.out.print(this.getNombre());
		System.out.println(":");
		Iterator<Item> it = items.iterator();
    	while(it.hasNext())
    	{
    		System.out.print(it.next().getDescripcion());
    		System.out.print(", ");
    	}
    	System.out.println();
	}
	
	public void listarEstadosPosibles(TipoItem tipo){
		tipo.listarEstadosPosibles();
	}
	
	public void listarEstadosSiguientes(TipoItem tipo, Estado estado){
		tipo.listarEstadosSiguientes(estado);
	}
}

