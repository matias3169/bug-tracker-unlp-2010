package unlp.edu.core;

public class Item {

	private Long id;
	private String nombre;
	private String descripcion;
	private TipoItem tipoItem;
	private int prioridad;
	private EstadoItem estadoActual;
	private EstadoHistorico historialEstados;
	private Miembro responsable;
	private Proyecto proyecto;

	public Item(){
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
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
	
	public EstadoHistorico getHistorialEstados()
	{
		return this.historialEstados;
	}
	
	public void setHistorialEstados(EstadoHistorico histo)
	{
		this.historialEstados = histo;
	}
		

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario responsableActual()
	{
		return this.estadoActual.getResponsable().getUsuario();
	}
	
	public String verEstadoActual(){
		return this.estadoActual.getEstado().getDescripcion();
	}

	public void setResponsable(Miembro responsable) {
		this.responsable = responsable;
	}

	public Miembro getResponsable() {
		return responsable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}
}
