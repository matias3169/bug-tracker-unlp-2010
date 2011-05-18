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
		
		Item item1 = sistema1.nuevoItem("Item1", "descripcion item1", tipo1, 0, proyecto1);
		Item item2 = sistema1.nuevoItem("Item2", "descripcion item2", tipo2, 3, proyecto1);
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
		miembrosDisponibles.add(miembro2);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		sistema1.cambiarEstadoItem(proyecto1, item1, estado1, miembro1, miembrosDisponibles,cal.getTime());
		System.out.print("Estado actual del item ");
		System.out.print(item1.getNombre());
		System.out.print(": ");
		System.out.println(sistema1.verEstadoActualItem(proyecto1, item1));
		
		cal.set(2010, 11, 20);
		sistema1.cambiarEstadoItem(proyecto1, item1, estado3, miembro2, miembrosDisponibles,cal.getTime());
		System.out.print("Estado actual del item ");
		System.out.print(item1.getNombre());
		System.out.print(": ");
		System.out.println(sistema1.verEstadoActualItem(proyecto1, item1));
		
		/*
		//Creacion de sistema, usuarios y proyecto
		Sistema sistema = new Sistema();
		sistema.nuevoUsuario("Linus Torvals", sistema.getRoleSistema("Developer"));
		sistema.nuevoUsuario("Steve Jobs", sistema.getRoleSistema("Developer"));
		sistema.nuevoUsuario("Richard Stallman", sistema.getRoleSistema("Developer"));
		sistema.nuevoProyecto("PROYECTO1", sistema.getUsuario("Linus Torvals"));
		
		//Se agregan nuevos miembros al proyecto
		sistema.nuevoMiembro(sistema.getProyecto("PROYECTO1"), sistema.getUsuario("Steve Jobs"), sistema.getRoleProyecto("DBA"));
		sistema.nuevoMiembro(sistema.getProyecto("PROYECTO1"), sistema.getUsuario("Richard Stallman"), sistema.getRoleProyecto("Tester"));
		
		//Se crean nuevos tipos de items para el proyecto y se settean sus estados
		sistema.nuevoTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1"));
		sistema.nuevoEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Iniciado");
		sistema.nuevoEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Desarrollo");
		sistema.nuevoEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Validacion");
		sistema.nuevoEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Cerrado");
		sistema.asignarEstadoSiguiente(sistema.getProyecto("PROYECTO1"),sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Iniciado","Desarrollo");
		sistema.asignarEstadoSiguiente(sistema.getProyecto("PROYECTO1"),sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Desarrollo","Validacion");
		sistema.asignarEstadoSiguiente(sistema.getProyecto("PROYECTO1"),sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Desarrollo","Cerrado");
		sistema.asignarEstadoSiguiente(sistema.getProyecto("PROYECTO1"),sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Validacion","Desarrollo");
		sistema.asignarEstadoSiguiente(sistema.getProyecto("PROYECTO1"),sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")),"Validacion","Cerrado");
		
		sistema.nuevoTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1"));
		sistema.nuevoEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1")),"Iniciado");
		sistema.nuevoEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1")),"Desarrollo");
		sistema.nuevoEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1")),"Cerrado");
		sistema.asignarEstadoSiguiente(sistema.getProyecto("PROYECTO1"),sistema.getTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1")),"Iniciado","Desarrollo");
		sistema.asignarEstadoSiguiente(sistema.getProyecto("PROYECTO1"),sistema.getTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1")),"Desarrollo","Cerrado");		
		
		//Se crean nuevos items y setean sus estados iniciales
		sistema.nuevoItem("RB0001","Error en casteo en formater", sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")), 5, sistema.getProyecto("PROYECTO1"));
		sistema.cambiarEstadoItem(sistema.getProyecto("PROYECTO1"), 
				sistema.getItem(sistema.getProyecto("PROYECTO1"),"RB0001"), 
				sistema.getEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")), "Iniciado"),
				sistema.getMiembro(sistema.getProyecto("PROYECTO1"), "Steve Jobs"),
				sistema.getProyecto("PROYECTO1").getMiembros(),
				new Date());
		sistema.nuevoItem("E0002","Cambios para interoperabilidad", sistema.getTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1")), 0, sistema.getProyecto("PROYECTO1"));
		sistema.cambiarEstadoItem(sistema.getProyecto("PROYECTO1"), 
				sistema.getItem(sistema.getProyecto("PROYECTO1"),"E0002"), 
				sistema.getEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Evolutivo", sistema.getProyecto("PROYECTO1")), "Iniciado"),
				sistema.getMiembro(sistema.getProyecto("PROYECTO1"), "Steve Jobs"),
				sistema.getProyecto("PROYECTO1").getMiembros(),
				new Date());
		
		//Se cambian de estados los items
		System.out.println("Estado inicial:" + sistema.getEstadoActualItem(sistema.getProyecto("PROYECTO1"), "RB0001").getEstado().getDescripcion());
		sistema.cambiarEstadoItem(sistema.getProyecto("PROYECTO1"), 
				sistema.getItem(sistema.getProyecto("PROYECTO1"),"RB0001"), 
				sistema.getEstado(sistema.getProyecto("PROYECTO1"), sistema.getTipoItem("Reporte de Bug", sistema.getProyecto("PROYECTO1")), "Validacion"),
				sistema.getMiembro(sistema.getProyecto("PROYECTO1"), "Steve Jobs"),
				sistema.getProyecto("PROYECTO1").getMiembros(),
				new Date());
		System.out.println("Estado final:" + sistema.getEstadoActualItem(sistema.getProyecto("PROYECTO1"), "RB0001").getEstado().getDescripcion());
		
		//Se mantiene el estado y se cambia el responsable
		System.out.println("Responsable inicial:" + sistema.getProyecto("PROYECTO1").getItem("RB0001").getEstadoActual().getResponsable().getUsuario().getNombre());
		sistema.getProyecto("PROYECTO1").getItem("RB0001").cambiarResponsable(sistema.getProyecto("PROYECTO1").getMiembro("Richard Stallman"), sistema.getProyecto("PROYECTO1").getMiembros(), new Date());
		System.out.println("Responsable final:" + sistema.getProyecto("PROYECTO1").getItem("RB0001").getEstadoActual().getResponsable().getUsuario().getNombre());
		*/
	}
	
}
