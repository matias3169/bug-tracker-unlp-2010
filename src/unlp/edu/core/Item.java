

import java.util.Collection;
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
		this.descripcion = desc;
		this.estadoActual = new EstadoItem(); /** ver parámetro */
		this.historialEstados = new HashSet<EstadoItem>(); /** Esta bien? */
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
	
	public void cambiarEstadoItem(Estado e)
	{
	}

	public void cambiarResponsable(Miembro m, EstadoItem ei)
	{		
	}
	
	public EstadoItem devolverEstadoAtual()
	{
	}
	
	public Usuario responsableActual()
	{
	}
}
