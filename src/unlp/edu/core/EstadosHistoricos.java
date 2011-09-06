package unlp.edu.core;

import java.util.Date;
import java.util.LinkedList;

public class EstadosHistoricos{
	
	private LinkedList<EstadoItem> listaEstados;
    private EstadoItem estadoActual;
    
    public EstadosHistoricos(){
    	
    	this.listaEstados = new LinkedList<EstadoItem>(); // crea una lista vacia
    }
    
    public void setEstadoActual(EstadoItem estado){
    	this.estadoActual = estado;
    }
    
    public void cambiarEstado(EstadoItem estado, String fichaTrabajo){
    	
    	Date fechaFin = new Date();
    	this.estadoActual.setFechaFin(fechaFin);
    	this.estadoActual.setFichaDeTrabajo(fichaTrabajo);
    	this.listaEstados.addFirst(this.estadoActual);
    	this.estadoActual = estado;    	
    }
    
    public LinkedList<EstadoItem> listar(){
    	LinkedList<EstadoItem> lista = new LinkedList<EstadoItem>(this.listaEstados);
    	lista.addFirst(this.estadoActual);
    	return lista;
    }
    
    public Integer size(){
    	Integer tamano = this.listaEstados.size() + 1;
    	return tamano;
    }
   
}
