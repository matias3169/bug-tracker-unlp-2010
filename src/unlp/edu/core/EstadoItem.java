package unlp.edu.core;

import java.util.*;

public class EstadoItem {
	
	/**
	 * @param estado
	 * @param fechaFin
	 * @param fechaInicio
	 * @param fichaDeTrabajo
	 * @param responsable
	 */
	private Long id;
	private Estado estado;
	private Date fechaInicio, fechaFin;
	private String fichaDeTrabajo;
	private Miembro responsable;
	private Item item;
	
	public EstadoItem(){
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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
		EstadoItem other = (EstadoItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
	
}
