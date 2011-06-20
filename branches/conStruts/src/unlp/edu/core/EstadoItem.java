package unlp.edu.core;

import java.util.*;

public class EstadoItem {
	
	/**
	 * @param estado
	 * @param fechaFin
	 * @param fechaInicio
	 * @param fichaDeTrabajo
	 * @param miembrosDisponibles
	 * @param responsable
	 */
	private Estado estado;
	private Date fechaFin, fechaInicio;
	private String fichaDeTrabajo;
	private Collection<Miembro> miembrosDisponibles;
	private Miembro responsable;
	
	// Constructor de la clase
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
	
}
