import java.util.Collection;
import java.util.Date;


public class EstadoItem {
	
	private Estado estado;
	private Date fechaFin, fechaInicio;
	private String fichaDeTrabajo;
	private Collection<Miembro> historialResponsables;
	private Item item;
	private Collection<Miembro> miembrosDisponibles;
	private Miembro responsable;
	
	public EstadoItem()
	{

	}
	

	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public String getFichaDeTrabajo() {
		return fichaDeTrabajo;
	}


	public void setFichaDeTrabajo(String fichaDeTrabajo) {
		this.fichaDeTrabajo = fichaDeTrabajo;
	}


	public Collection<Miembro> getHistorialResponsables() {
		return historialResponsables;
	}


	public void setHistorialResponsables(Collection<Miembro> historialResponsables) {
		this.historialResponsables = historialResponsables;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Collection<Miembro> getMiembrosDisponibles() {
		return miembrosDisponibles;
	}


	public void setMiembrosDisponibles(Collection<Miembro> miembrosDisponibles) {
		this.miembrosDisponibles = miembrosDisponibles;
	}


	public Miembro getResponsable() {
		return responsable;
	}


	public void setResponsable(Miembro responsable) {
		this.responsable = responsable;
	}


	public Estado getEstado()
	{
		return this.estado;
	}
	
	public void setEstado(Estado e)
	{
		this.estado = e;
	}
	
	
	public Date getFechaFin()
	{
		return this.fechaFin;
	}
	
	public void setFechaFin(Date f)
	{
		this.fechaFin = f;
	}
	
	/**
	 * Metodos
	 * 
	 * 
	 */
	public void cambiarResponsable(Miembro m)
	{
	}
	
	public Collection<Miembro> obtenerMiembrosDisponibles()
	{
	}
	
	public Miembro responsableActual()
	{
		return this.getResponsable();
	}

}
