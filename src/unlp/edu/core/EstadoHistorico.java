package unlp.edu.core;


public class EstadoHistorico{
	
	private Long id;
	private EstadoItem estadoHistorico;
	private EstadoItem estadoActual;
	
    
    public EstadoHistorico(){    	
    }

    public EstadoItem getEstadoActual(){
    	return this.estadoActual;
    }
    
    public void setEstadoActual(EstadoItem estado){
    	this.estadoActual = estado;
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
		EstadoHistorico other = (EstadoHistorico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setEstadoHistorico(EstadoItem estadoHistorico) {
		this.estadoHistorico = estadoHistorico;
	}

	public EstadoItem getEstadoHistorico() {
		return estadoHistorico;
	}
   
}
