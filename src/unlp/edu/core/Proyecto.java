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
	
	public void nuevoEstado(TipoItem tipo, String estado){ //agregar un nuevo estado a la lista de estados del TipoItem
		
		tipo.agregarEstado(estado);
	}
	
	public void nuevoItem(String desc, TipoItem tipo, int prioridad1){
		this.items.add(new Item(desc, tipo, prioridad1));
	}
	
	public void agregarMiembro(Miembro miembro){
		this.miembros.add(miembro);
	}
	
	public void nuevoTipoItem(String descripcion){ //creo el tipo de item sin estados
		this.tiposItems.add(new TipoItem(descripcion, null, new HashSet<Estado>()));
	}
	
	public void cambiarEstadoItem( Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles){
		item.cambiarEstadoItem(estado, responsable, miembrosDisponibles);
	}
}

