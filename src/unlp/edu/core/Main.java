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
		
		Sistema sistema1 = new Sistema();
		
		Role rolSistema1 = sistema1.nuevoRolSistema("Super");
		Role rolSistema2 = sistema1.nuevoRolSistema("Normal");
		Role rolProyecto1 = sistema1.nuevoRolProyecto("Normal");
		Usuario usuario1 = sistema1.nuevoUsuario("Usuario1", rolSistema1);
		Usuario usuario2 = sistema1.nuevoUsuario("Usuario2", rolSistema2);
		sistema1.listarUsuarios();
		
		Proyecto proyecto1 = new Proyecto("PROYECTO1");
		
		TipoItem tipo1 = sistema1.nuevoTipoItem("Reporte de Bug", proyecto1);
		TipoItem tipo2 = sistema1.nuevoTipoItem("Nueva funcionalidad", proyecto1);
		sistema1.listarTiposItem(proyecto1);
		
		sistema1.listarRolesSistema();
		sistema1.listarRolesProyecto();
		
		Miembro miembro1 = sistema1.nuevoMiembro(proyecto1, usuario1,rolProyecto1);
		Miembro miembro2 = sistema1.nuevoMiembro(proyecto1, usuario2,rolProyecto1);
		
		Item item1 = sistema1.nuevoItem(proyecto1, "Item1", tipo1, 0);
		Item item2 = sistema1.nuevoItem(proyecto1, "Item2", tipo2, 3);
		sistema1.listarItems(proyecto1);
		
		Estado estado1 = sistema1.nuevoEstado(proyecto1, tipo1, "Creado");
		Estado estado2 = sistema1.nuevoEstado(proyecto1, tipo1, "Desarrollo");
		Estado estado3 = sistema1.nuevoEstado(proyecto1, tipo1, "Validación");
		Estado estado4 = sistema1.nuevoEstado(proyecto1, tipo1, "Terminado");
		sistema1.agregarEstadoSiguiente(proyecto1, tipo1, estado1, estado2);
		sistema1.agregarEstadoSiguiente(proyecto1, tipo1, estado2, estado3);
		sistema1.agregarEstadoSiguiente(proyecto1, tipo1, estado3, estado4);
		sistema1.agregarEstadoSiguiente(proyecto1, tipo1, estado3, estado2);
		sistema1.listarEstadosPosibles(proyecto1, tipo1);
		sistema1.listarEstadosSiguientes(proyecto1, tipo1, estado3);
		
		Collection<Miembro> miembrosDisponibles = new HashSet<Miembro>();
		miembrosDisponibles.add(miembro1);
		
		sistema1.cambiarEstadoItem(proyecto1, item1, estado1, miembro1, miembrosDisponibles);
		System.out.print("Estado actual del item ");
		System.out.print(item1.getDescripcion());
		System.out.print(": ");
		System.out.println(sistema1.verEstadoActualItem(proyecto1, item1));
		
		sistema1.cambiarEstadoItem(proyecto1, item1, estado3, miembro2, miembrosDisponibles);
		System.out.print("Estado actual del item ");
		System.out.print(item1.getDescripcion());
		System.out.print(": ");
		System.out.println(sistema1.verEstadoActualItem(proyecto1, item1));
		
		/*
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
		
		Miembro miembro1 = new Miembro(proyecto, usuario1, role1);
		Miembro miembro2 = new Miembro(proyecto, usuario2, role2);
		Miembro miembro3 = new Miembro(proyecto, usuario3, role3);
		Miembro miembro4 = new Miembro(proyecto, usuario4, role3);
		
		miembrosDisponibles.add(miembro1);
		miembrosDisponibles.add(miembro2);
		miembrosDisponibles.add(miembro3);
		
		TipoItem tipo1 = new TipoItem("Reporte de Bug",estado1,estadosPosibles);
		Item item1=new Item("Corregir falla",tipo1,0);
	
		proyecto.cambiarEstadoItem(item1, estado1, miembro1, miembrosDisponibles);
		//item1.cambiarEstadoItem(estado1, miembro1, miembrosDisponibles);
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
	*/
	}
	
}
