package unlp.edu.action;

import java.util.ArrayList;
import java.util.List;

public class LinksVolver {
	
	List<String> historial; 
	String paginaActual;
	
	public LinksVolver() {
		this.historial =  new ArrayList<String>();
		this.paginaActual ="";
	}
 
	public boolean visita(String p)
	{
		
		if(!p.equals(this.paginaActual))
		{
			if(!this.paginaActual.equals(""))
				if(this.historial.size()>0)
				{
					if(this.historial.get(this.historial.size()-1).toString().equals(p))
						this.historial.remove(this.historial.size()-1);
					else
						this.historial.add(this.paginaActual);
				}
				else
				{
					this.historial.add(this.paginaActual);
				}
			this.paginaActual = p;
		}
//	
//		System.out.println("Pagina actual: "+this.paginaActual+"\n");
//		System.out.println(this.historial.size());
//		for (Iterator it=this.historial.iterator(); it.hasNext( ); ) {
//		    Object anObject = it.next( );
//		    System.out.println( anObject.toString() );
//		}
//		System.out.println("--------------------\n");
//		
		return true;
	}
	
	public String paginaAnterior()
	{
		if(this.historial.size()>0)
			return this.historial.get(this.historial.size()-1).toString();
		return "";
	}
	
	public void vaciar()
	{
		this.historial.clear();
		this.paginaActual ="sistema.jsp";
	}
}
