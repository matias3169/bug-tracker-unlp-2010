package unlp.edu.core;

import java.util.*;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Collection<Estado> estadosPosibles = new HashSet<Estado>();
		Collection<Miembro> miembrosDisponibles = new HashSet<Miembro>();
			
		Estado estado1 = new Estado("Iniciado");
		Estado estado2 = new Estado("Desarrollo");
		Estado estado3 = new Estado("Validacion");
		Estado estado4 = new Estado("Cerrado");
		estado1.agregarEstadoSiguiente(estado2);
		estado2.agregarEstadoSiguiente(estado3);
		estado3.agregarEstadoSiguiente(estado2);
		estado3.agregarEstadoSiguiente(estado4);
		System.out.print("estado1= ");
		System.out.println(estado1.getDescripcion());
		
		estadosPosibles.add(estado1);
		estadosPosibles.add(estado2);
		estadosPosibles.add(estado3);
		estadosPosibles.add(estado4);
		
		Role role1 = new Role("Analista funcional", new HashSet<String>());
		Role role2 = new Role("Desarrollador", new HashSet<String>());
		Role role3 = new Role("Tester", new HashSet<String>());
		
		Role roleSistema1 = new Role("SysAdmin", new HashSet<String>());
		Role roleSistema2 = new Role("Usuario", new HashSet<String>());
		Role roleSistema3 = new Role("Usuario", new HashSet<String>());
			
		Usuario usuario1 = new Usuario("Vale", roleSistema1);
		Usuario usuario2 = new Usuario("Santi", roleSistema2);
		Usuario usuario3 = new Usuario("Matias", roleSistema3);
		Usuario usuario4 = new Usuario("Usuario4", roleSistema3);
		
		Proyecto proyecto = new Proyecto("PROYECTO1");
		
		Miembro miembro1 = new Miembro(usuario1, role1, proyecto);
		Miembro miembro2 = new Miembro(usuario2, role2, proyecto);
		Miembro miembro3 = new Miembro(usuario3, role3, proyecto);
		Miembro miembro4 = new Miembro(usuario4, role3, proyecto);
		
		miembrosDisponibles.add(miembro1);
		miembrosDisponibles.add(miembro2);
		miembrosDisponibles.add(miembro3);
		
		TipoItem tipo1 = new TipoItem("Reporte de Bug",estado1,estadosPosibles);
		Item item1=new Item("Corregir falla",tipo1,0);
	
		item1.cambiarEstadoItem(estado1, miembro1, miembrosDisponibles);
		System.out.print("estadoActual= ");
		System.out.println(item1.getEstadoActual().getEstado().getDescripcion());
		System.out.print("fechaInicio= ");
		System.out.println(item1.getEstadoActual().getFechaInicio());
		System.out.print("responsable= ");
		System.out.println(item1.getEstadoActual().getResponsable().getUsuario().getNombre());
		System.out.println();
		
		item1.cambiarEstadoItem(estado2, miembro4, miembrosDisponibles);
		System.out.print("estadoActual= ");
		System.out.println(item1.getEstadoActual().getEstado().getDescripcion());
		System.out.print("fechaInicio= ");
		System.out.println(item1.getEstadoActual().getFechaInicio());
		System.out.print("responsable= ");
		System.out.println(item1.getEstadoActual().getResponsable().getUsuario().getNombre());
		System.out.println();
		
		item1.cambiarResponsable(miembro3, miembrosDisponibles);
		System.out.print("estadoActual= ");
		System.out.println(item1.getEstadoActual().getEstado().getDescripcion());
		System.out.print("fechaInicio= ");
		System.out.println(item1.getEstadoActual().getFechaInicio());
		System.out.print("responsable= ");
		System.out.println(item1.getEstadoActual().getResponsable().getUsuario().getNombre());
	}
}
