/**
 * 
 */
package unlp.edu.core;


/**
 * @author G2_UNLP
 * @version 1.0.0
 *
 */
public class Proyecto {

	private Long id;
	private String nombre;
	private Miembro liderProyecto;
	
	public Proyecto(){
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public String verEstadoActualItem(Item item){
		return item.verEstadoActual();
	}
	
	
	public Miembro getLiderProyecto()
	{
		return this.liderProyecto;
	}
	
	public void setLiderProyecto(Miembro lider)
	{
		this.liderProyecto = lider;
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
		Proyecto other = (Proyecto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

