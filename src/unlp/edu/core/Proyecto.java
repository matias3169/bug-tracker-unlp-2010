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

	private int id;
	private String nombre;
	private static int id_item = 0;
	private static int id_tipo_item = 0;
	private Collection<Item> items;
	private Collection<TipoItem> tiposItems;
	private Collection<Miembro> miembros;
	
	public Proyecto(int id, String nombre) {
		this.id = id;
		this.setNombre(nombre);
		this.setItems(new HashSet<Item>());
		this.setTiposItems(new HashSet<TipoItem>());
		this.setMiembros(new HashSet<Miembro>());
	}

	
	private static int getIdItem()
	{
		return id_item;
	}
	
	private static void setIdItem()
	{
		id_item++;
	}
	
	private static int getIdTipoItem()
	{
		return id_tipo_item;
	}
	
	private static void setIdTipoItem()
	{
		id_tipo_item++;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return the nombre
	 */
	public int getId() {
		return id;
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
	
	public Item nuevoItem(String nombre, String desc, TipoItem tipo, int prioridad1, Miembro responsable){
		setIdItem();
		Item item = new Item(getIdItem(),nombre, desc, tipo, prioridad1, responsable);
		this.items.add(item);
		return item;
	}
	
	public void agregarMiembro(Miembro miembro){
		this.miembros.add(miembro);
	}
	
	public TipoItem nuevoTipoItem(String descripcion){ //creo el tipo de item sin estados
		setIdTipoItem();
		TipoItem tipoItem = new TipoItem(getIdTipoItem(), descripcion, null, new HashSet<Estado>());
		this.tiposItems.add(tipoItem);
		return tipoItem;
	}
	
	public void cambiarEstadoItem( Item item, Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fecha) throws Exception{
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
    	System.out.println("\n");
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
    	System.out.println("\n");
	}
	
	public void listarEstadosPosibles(TipoItem tipo){
		tipo.listarEstadosPosibles();
	}
	
	public void listarEstadosSiguientes(TipoItem tipo, Estado estado){
		tipo.listarEstadosSiguientes(estado);
	}

	public HashSet<EstadoItem> getEstadosHistoricosItem(Item item, Date fec_inicio,
			Date fec_fin) {
		return this.getItem(item.getNombre()).getEstadosHistoricos(fec_inicio,fec_fin);
		
	}
	
	public Miembro getLiderProyecto()
	{
		Iterator<Miembro> it = this.getMiembros().iterator();
    	boolean notFound = true;
    	Miembro lider, miembro = null; 
    		
    	while (it.hasNext() && notFound) {
    		
			lider = (Miembro) it.next();
			if (lider.getRole().getNombre().equals("Lider")){
				notFound = false;
				miembro = lider;
			}
		}
    	return miembro;
	}
}
