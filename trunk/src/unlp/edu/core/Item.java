package unlp.edu.core;

import java.util.*;

public class Item {

	private int id;
	private String nombre;
	private String descripcion;
	private TipoItem tipoItem;
	private int prioridad;
	private EstadoItem estadoActual;
	private Collection<EstadoItem> historialEstados;

	/**
	 * 
	 * @param nombre
	 * @param desc
	 * @param tipo
	 * @param prioridad
	 * @param responsable
	 */
	public Item(int id, String nombre, String desc, TipoItem tipo, int prioridad, Miembro responsable)
	{
		this.id = id;
		Date date= new Date();
		this.nombre = nombre;
		Estado estadoIni = tipo.getEstadoInicial(); //podría ser null
		this.descripcion = desc;
		this.tipoItem = tipo;
		this.prioridad = prioridad;
		this.estadoActual = new EstadoItem(estadoIni,null,date,null,null,responsable);
		this.historialEstados = new HashSet<EstadoItem>();
	}

	public int getId()
	{
		return this.id;
	}
	
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
		

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void cambiarEstadoItem(Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fechaFin) throws Exception
	{
		estadoActual.setFechaFin(fechaFin);
		this.historialEstados.add(estadoActual);
		this.setEstadoActual(new EstadoItem(estado,null,fechaFin,"",miembrosDisponibles,responsable));
		
		/* if (miembrosDisponibles.contains(responsable)) {
			estadoActual.setFechaFin(fechaFin);
			this.historialEstados.add(estadoActual);
			this.setEstadoActual(new EstadoItem(estado,null,fechaFin,"",miembrosDisponibles,responsable));
		} else {
			throw new Exception("El responsable no es un miembro disponible.");
		}*/
	}

	public void cambiarResponsable(Miembro responsable, Collection<Miembro> miembrosDisponibles, Date fechaFin) throws Exception//Guarda el estadoItem y crea un nuevo estadoItem con el responsable
	{
	
		if (miembrosDisponibles.contains(responsable)) {
			estadoActual.setFechaFin(fechaFin);
			this.historialEstados.add(estadoActual);
			this.setEstadoActual(new EstadoItem(estadoActual.getEstado(),null, fechaFin, "", estadoActual.getMiembrosDisponibles(), responsable));
		} else {
			throw new Exception("El responsable no es un miembro disponible.");
		}
	}	
	
	public Usuario responsableActual()
	{
		return this.estadoActual.getResponsable().getUsuario();
	}
	
	public String verEstadoActual(){
		return this.estadoActual.getEstado().getDescripcion();
	}

	/**
	 * Se retorna la lista de Estados items entre las fechas indicadas.
	 * Si busquedaCriterio = 0 -> retornar EstadosItem entre fechas indicadas.
	 * Si busquedaCriterio = 1 -> retornar EstadosItem menores a fec_fin.
	 * Si busquedaCriterio = 2 -> retornar EstadosItems mayores a fec_inicio.
	 * Si busquedaCriterio = 3 -> retornar todos los EstadosItem.
	 * 
	 * @param fec_inicio
	 * @param fec_fin
	 */
	public HashSet<EstadoItem> getEstadosHistoricos(Date fec_inicio, Date fec_fin) {
		HashSet<EstadoItem> todosHistoricos = new HashSet<EstadoItem>();

		//Todos los estados historicos incluyendo el estado actual
		todosHistoricos.addAll(this.getHistorialEstados());
		todosHistoricos.add(this.getEstadoActual());
		Iterator<EstadoItem> it = todosHistoricos.iterator();
		
		HashSet<EstadoItem> historicosCriterio = new HashSet<EstadoItem>();
	   	
		EstadoItem ei; 
	    int busquedaCriterio;
	    
	    busquedaCriterio = 0;
	    
	   	if (fec_inicio == null)
	   	{
	   		busquedaCriterio = 1;
	   		if (fec_fin == null)
	   		{
	   			busquedaCriterio = 3;
	   		}
	   	} else {
	   		if (fec_fin == null)
	   		{
	   			busquedaCriterio = 2;
	   		}
	   	}
	   	
	   	while (it.hasNext()) {
	   		ei = (EstadoItem) it.next();
	   		
	   		if (busquedaCriterio == 0){	
	   			if (ei.getFechaInicio().after(fec_inicio) && ei.getFechaInicio().before(fec_fin))
	   				historicosCriterio.add(ei);
			} else {
				if (busquedaCriterio == 1){	
		   			if (ei.getFechaInicio().before(fec_fin))
		   				historicosCriterio.add(ei);
				}else {
					if (busquedaCriterio == 2){	
			   			if (ei.getFechaInicio().after(fec_inicio))
			   				historicosCriterio.add(ei);
					} else 
						historicosCriterio.add(ei);	
				}
			}
	   	}
	   	return historicosCriterio;
		
	}
}
