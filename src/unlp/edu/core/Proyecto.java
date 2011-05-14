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
	};
}
