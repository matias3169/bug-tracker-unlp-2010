package unlp.edu.core;

import java.util.*;

public class Item {

	/**
	 * @param args
	 */
	
	private String descripcion;
	private TipoItem tipoItem;
	private int prioridad;
	private EstadoItem estadoActual;
	private Collection<EstadoItem> historialEstados;
	
	//Constructor de la clase
	public Item(String desc, TipoItem tipo, int prioridad1)
	{
		Date date= new Date();
		this.descripcion = desc;
		this.tipoItem = tipo;
		this.prioridad = prioridad1;
		this.estadoActual = new EstadoItem(null,null,date,null,null,null);
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
	
	public void cambiarEstadoItem(Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles)
	{
		Date date= new Date();
		if (miembrosDisponibles.contains(responsable)) {
			estadoActual.setFechaFin(date);
			this.historialEstados.add(estadoActual);
			this.setEstadoActual(new EstadoItem(estado,null,date,"",miembrosDisponibles,responsable));
		} else {
			System.out.println("El responsable no es un miembro disponible");
		}
	}

	public void cambiarResponsable(Miembro responsable, Collection<Miembro> miembrosDisponibles)//Guarda el estadoItem y crea un nuevo estadoItem con el responsable
	{
		Date date= new Date();
		if (miembrosDisponibles.contains(responsable)) {
			estadoActual.setFechaFin(date);
			this.historialEstados.add(estadoActual);
			this.setEstadoActual(new EstadoItem(estadoActual.getEstado(),null, date, "", estadoActual.getMiembrosDisponibles(), responsable));
		} else {
			System.out.println("El responsable no es un miembro disponible");
		}
	}	
	
	public Usuario responsableActual()
	{
		return this.estadoActual.getResponsable().getUsuario();
	}
}
