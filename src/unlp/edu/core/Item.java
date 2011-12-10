package unlp.edu.core;

import java.util.*;

public class Item {

	private int id;
	private String nombre;
	private String descripcion;
	private TipoItem tipoItem;
	private int prioridad;
	private EstadoItem estadoActual;
	private EstadosHistoricos historialEstados;

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
		this.estadoActual = new EstadoItem(estadoIni,date,null,null,responsable);
		this.historialEstados = new EstadosHistoricos(); // creo una lista vacia linkedlist
		this.historialEstados.setEstadoActual(estadoActual); // agrego el estado actual al historial
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
	
	public EstadosHistoricos getHistorialEstados()
	{
		return this.historialEstados;
	}
	public void setHistorialEstados(EstadosHistoricos histo)
	{
		this.historialEstados = histo;
	}
		

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void cambiarEstadoItem(Estado estado, Miembro responsable, Date fechaFin, String fichaTrabajo) throws Exception
	{
		if (this.getTipoItem().getMiembrosDisponibles().contains(responsable)) {
			Date fechaInicio = new Date();
			EstadoItem nuevoEstado = new EstadoItem(estado,fechaInicio,null,null,responsable);
			this.setEstadoActual(nuevoEstado);
			this.historialEstados.cambiarEstado(nuevoEstado, fichaTrabajo);
		} else {
			throw new Exception("El responsable no es un miembro disponible.");
		}
		
	}

	public void cambiarResponsable(Miembro responsable, Date fechaFin, String fichaTrabajo) throws Exception//Guarda el estadoItem y crea un nuevo estadoItem con el responsable
	{
		if (this.getTipoItem().getMiembrosDisponibles().contains(responsable)) {
			Date fechaInicio = new Date();
			EstadoItem nuevoEstado = new EstadoItem(estadoActual.getEstado(),fechaInicio,null,"",responsable);
			this.setEstadoActual(nuevoEstado);
			this.historialEstados.cambiarEstado(nuevoEstado, fichaTrabajo);
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
		todosHistoricos.addAll(this.getHistorialEstados().listar());
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
