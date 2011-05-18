package unlp.edu.core;

import java.util.*;

public class Item {

	/**
	 * @param args
	 */
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String descripcion;
	private TipoItem tipoItem;
	private int prioridad;
	private EstadoItem estadoActual;
	private Collection<EstadoItem> historialEstados;
	
	//Constructor de la clase
	public Item(String nombre, String desc, TipoItem tipo, int prioridad1)
	{
		Date date= new Date();
		this.nombre = nombre;
		Estado estadoIni = null;
		this.descripcion = desc;
		this.tipoItem = tipo;
		this.prioridad = prioridad1;
		this.estadoActual = new EstadoItem(estadoIni,null,date,null,null,null);
		this.historialEstados = new HashSet<EstadoItem>();
	}
	/**
	 * 
	 * getters y setters
	 * 
	 */
	public String getDescripcion()
	{
		return this.descripcion;
	}
		
	public void setDescripcion(String desc)
	{
		this.descripcion = desc;
	}
	
	public TipoItem getTipoItem()
	{
		return this.tipoItem;
	}
	
	public void setTipoItem(TipoItem tipo)
	{
		this.tipoItem = tipo;
	}
	
	public int getPrioridad()
	{
		return this.prioridad;
	}
	
	public void setPrioridad(int prioridad1)
	{
		this.prioridad = prioridad1;
	}
	
	public EstadoItem getEstadoActual()
	{
		return this.estadoActual;
	}
		
	public void setEstadoActual(EstadoItem estado)
	{
		this.estadoActual = estado;
	}
	
	public Collection<EstadoItem> getHistorialEstados()
	{
		return this.historialEstados;
	}
	public void setHistorialEstados(Collection<EstadoItem> histo)
	{
		this.historialEstados = histo;
	}
		
	
	/** 
	 * Metodos 
	 * 
	 */
	
	public void cambiarEstadoItem(Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fechaFin)
	{
	
		if (miembrosDisponibles.contains(responsable)) {
			estadoActual.setFechaFin(fechaFin);
			this.historialEstados.add(estadoActual);
			this.setEstadoActual(new EstadoItem(estado,null,fechaFin,"",miembrosDisponibles,responsable));
		/*	if (this.estadoActual.getEstado().getEstadosSiguientes().contains(estado)) { // Controla si estado es un estado siguiente posible
				estadoActual.setFechaFin(date);
				this.historialEstados.add(estadoActual);
				this.setEstadoActual(new EstadoItem(estado,null,date,"",miembrosDisponibles,responsable));
			} else {
				System.out.println("El estado al que quiere pasar no es un estado posible.");
			}*/
		} else {
			System.out.println("El responsable no es un miembro disponible.");
		}
	}

	public void cambiarResponsable(Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fechaFin)//Guarda el estadoItem y crea un nuevo estadoItem con el responsable
	{
	
		if (miembrosDisponibles.contains(responsable)) {
			estadoActual.setFechaFin(fechaFin);
			this.historialEstados.add(estadoActual);
			this.setEstadoActual(new EstadoItem(estadoActual.getEstado(),null, fechaFin, "", estadoActual.getMiembrosDisponibles(), responsable));
		} else {
			System.out.println("El responsable no es un miembro disponible");
		}
	}	
	
	public Usuario responsableActual()
	{
		return this.estadoActual.getResponsable().getUsuario();
	}
	
	public String verEstadoActual(){
		return this.estadoActual.getEstado().getDescripcion();
	}
}