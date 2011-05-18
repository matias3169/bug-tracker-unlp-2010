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
	
	public Item nuevoItem(String nombre, String desc, TipoItem tipo, int prioridad1){
		Item item = new Item(nombre, desc, tipo, prioridad1);
		this.items.add(item);
		return item;
	}
	
	public void agregarMiembro(Miembro miembro){
		this.miembros.add(miembro);
	}
	
	public TipoItem nuevoTipoItem(String descripcion){ //creo el tipo de item sin estados
		TipoItem tipoItem = new TipoItem(descripcion, null, new HashSet<Estado>());
		this.tiposItems.add(tipoItem);
		return tipoItem;
	}
	
	public void cambiarEstadoItem( Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fecha){
		item.cambiarEstadoItem(estado, responsable, miembrosDisponibles, fecha);
	}

	public Estado getEstado(TipoItem tipoItem, String descripcion) {
		return tipoItem.getEstado(descripcion);
	}
	
    public TipoItem getTipoItem(String descripcion){
    	Iterator<TipoItem> it = this.getTiposItems().iterator();
    	boolean notFound = true;
    	TipoItem tit, tipoItem = null; 
    		
    	while (it.hasNext() && notFound) {
    		
			tit = (TipoItem) it.next();
			if (tit.getDescripcion().equals(descripcion)){
				notFound = false;
				tipoItem = tit;
			}
		}
    	return tipoItem;
    }
    
    public Item getItem(String nombre){
    	Iterator<Item> it = this.getItems().iterator();
    	boolean notFound = true;
    	Item iit, item = null; 
    		
    	while (it.hasNext() && notFound) {
    		
			iit = (Item) it.next();
			if (iit.getNombre().equals(nombre)){
				notFound = false;
				item = iit;
			}
		}
    	return item;
    }

	public Miembro getMiembro(String nombre) {
    	Iterator<Miembro> it = this.getMiembros().iterator();
    	boolean notFound = true;
    	Miembro mit, miembro = null; 
    		
    	while (it.hasNext() && notFound) {
    		
			mit = (Miembro) it.next();
			if (mit.getUsuario().getNombre().equals(nombre)){
				notFound = false;
				miembro = mit;
			}
		}
    	return miembro;
	}
	public void agregarEstadoSiguiente(TipoItem tipo, String estadoI, String estadoF){
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
    		System.out.print(it.next().getNombre());
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

