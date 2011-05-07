package unlp.edu.core;

import java.util.Collection;
import java.util.Date;


public class EstadoItem {
	
	/**
	 * @param estado
	 * @param fechaFin
	 * @param fechaInicio
	 * @param fichaDeTrabajo
	 * @param historialResponsables
	 * @param miembrosDisponibles
	 * @param responsable
	 */
	private Estado estado;
	private Date fechaFin, fechaInicio;
	private String fichaDeTrabajo;
	private Collection<Miembro> historialResponsables;
	private Collection<Miembro> miembrosDisponibles;
	private Miembro responsable;
	
	//metodos
	
	public EstadoItem(Estado estado, Date fechaFin, Date fechaInicio,
			String fichaDeTrabajo,Collection<Miembro> miembrosDisponibles,
			Miembro responsable) {
		this.estado = estado;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.fichaDeTrabajo = fichaDeTrabajo;
		this.miembrosDisponibles = miembrosDisponibles;
		this.responsable = responsable;
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
	public void cambiarResponsable(Miembro responsable)
	{

	}
	
	public Collection<Miembro> obtenerMiembrosDisponibles()
	{
		return this.miembrosDisponibles;
	}
	
	public Miembro responsableActual()
	{
		return this.getResponsable();
	}

}
