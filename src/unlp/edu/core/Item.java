package unlp.edu.core;



import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Item {

	/**
	 * @param args
	 */
	
	private String descripcion;
	private EstadoItem estadoActual;
	private Collection<EstadoItem> historialEstados;
	private int prioridad;
	private TipoItem tipoItem;
	
	public Item(String desc, TipoItem tipo)
	{
		Date date= Calendar.getInstance().getTime();
		this.descripcion = desc;
		this.estadoActual = new EstadoItem(null,null,date,null,null,null);
		this.historialEstados = new HashSet<EstadoItem>();
		this.prioridad = 0;
		this.tipoItem = tipo;
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
	
	public EstadoItem getEstadoActual()
	{
		return this.estadoActual;
	}
		
	public void setDescripcion(EstadoItem  estado)
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
	
	public int getPrioridad()
	{
		return this.prioridad;
	}
	public void setPrioridad(int prio)
	{
		this.prioridad = prio;
	}
	
	public TipoItem getTipoItem()
	{
		return this.tipoItem;
	}
	
	public void setTipoItem(TipoItem tipo)
	{
		this.tipoItem = tipo;
	}
	
	
	/** 
	 * Metodos 
	 * 
	 */
	
	public void cambiarEstadoItem(Estado estado, Miembro responsable, Collection<Miembro> miembrosDisponibles)
	{
		Date date= Calendar.getInstance().getTime();
		estadoActual.setFechaFin(date);
		this.historialEstados.add(estadoActual);	
		this.setEstadoActual(new EstadoItem(estado,null,date,"",miembrosDisponibles,responsable));
	
	}

	public void cambiarResponsable(Miembro responsable)//Guarda el estadoItem y crea un nuevo estadoItem con el responsable
	{
		Date date= Calendar.getInstance().getTime();
		estadoActual.setFechaFin(date);
		this.historialEstados.add(estadoActual);
		this.setEstadoActual(new EstadoItem(estadoActual.getEstado(),null, date, "", estadoActual.getMiembrosDisponibles(), responsable));
	}	
	
	/**
	 * @param estadoActual the estadoActual to set
	 */
	public void setEstadoActual(EstadoItem estadoActual) {
		this.estadoActual = estadoActual;
	}
	public EstadoItem devolverEstadoAtual()
	{
		return this.estadoActual;
	}
	
	public Miembro responsableActual()
	{
		return this.estadoActual.getResponsable();
	}
}
