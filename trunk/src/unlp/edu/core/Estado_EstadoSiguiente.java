package unlp.edu.core;

public class Estado_EstadoSiguiente {
	
	private Long id;
	private Estado EstadoInicial;
	private Estado EstadoSiguiente;
	
	public Estado_EstadoSiguiente() {
	}
	
	public void setEstadoSiguiente(Estado estadoSiguiente) {
		EstadoSiguiente = estadoSiguiente;
	}
	public Estado getEstadoSiguiente() {
		return EstadoSiguiente;
	}
	public void setEstadoInicial(Estado estadoInicial) {
		EstadoInicial = estadoInicial;
	}
	public Estado getEstadoInicial() {
		return EstadoInicial;
	}

	public void setIdEstado_EstadoSiguiente(Long id) {
		this.id = id;
	}

	public Long getIdEstado_EstadoSiguiente() {
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
		Estado_EstadoSiguiente other = (Estado_EstadoSiguiente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
