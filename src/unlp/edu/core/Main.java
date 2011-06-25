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
		
		//Creacion del sistema
		Sistema sistema = Sistema.getInstance();
		
		//Creacion de roles sistema y de proyecto
		//Role rolsistema = sistema.nuevoRolSistema("Super");
		//Role rolSistema2 = sistema.nuevoRolSistema("Normal");
		//Role rolProyecto1 = sistema.nuevoRolProyecto("Normal");
		
		//Listado de roles sistema y de proyecto
		sistema.listarRolesSistema();
		sistema.listarRolesProyecto();

		//Creacion de usuarios
		Usuario usuario1 = sistema.nuevoUsuario("Usuario1", "clave1", sistema.getRoleSistema("Admin"));
		Usuario usuario2 = sistema.nuevoUsuario("Usuario2", "clave2", sistema.getRoleSistema("Developer"));
		Usuario usuario3 = sistema.nuevoUsuario("Usuario3", "clave3", sistema.getRoleSistema("Guest"));
		
		//Listado de usuarios
		sistema.listarUsuarios();
		
		//Creacion de nuevo proyecto
		sistema.nuevoProyecto("PROYECTO1", usuario1);
		sistema.nuevoProyecto("PROYECTO1", usuario1);
		
		Proyecto proyecto1 = sistema.getProyecto("PROYECTO1");
		
		//Creacion de nuevos tipos de items para el proyecto
		TipoItem tipo1 = sistema.nuevoTipoItem("Reporte de Bug", proyecto1);
		TipoItem tipo2 = sistema.nuevoTipoItem("Nueva funcionalidad", proyecto1);
		
		//Listado de tipos de items para proyecto
		sistema.listarTiposItem(proyecto1);
		
		//Creacion de estados y flowchart para tipos de items
		Estado estado1 = sistema.nuevoEstado(proyecto1, tipo1, "Creado");
		Estado estado2 = sistema.nuevoEstado(proyecto1, tipo1, "Desarrollo");
		Estado estado3 = sistema.nuevoEstado(proyecto1, tipo1, "Validacion");
		Estado estado4 = sistema.nuevoEstado(proyecto1, tipo1, "Terminado");
		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Creado", "Desarrollo");
		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Desarrollo", "Validacion");
		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Validacion", "Terminado");
		sistema.agregarEstadoSiguiente(proyecto1, tipo1, "Validacion", "Desarrollo");
		
		//Listado estados posibles tipo item
		sistema.listarEstadosPosibles(proyecto1, tipo1);
		//Listado estados siguientes tipo item para un estado
		sistema.listarEstadosSiguientes(proyecto1, tipo1, estado3);
		
		//Creacion de nuevos miembros para proyecto
		Miembro miembro1 = sistema.nuevoMiembro(proyecto1, usuario1,sistema.getRoleProyecto("Lider"));
		Miembro miembro2 = sistema.nuevoMiembro(proyecto1, usuario2,sistema.getRoleSistema("Desarrollador"));
		Miembro miembro3 = sistema.nuevoMiembro(proyecto1, usuario3,sistema.getRoleSistema("DBA"));
		
		//Listado de miembros de un proyecto
		sistema.listarMiembros(proyecto1);
		
		//Creacion de nuevos items para proyecto
		Item item1 = sistema.nuevoItem("Item1", "descripcion item1", tipo1, 0, proyecto1, sistema.getMiembro(proyecto1, "Usuario1"));
		Item item2 = sistema.nuevoItem("Item2", "descripcion item2", tipo2, 3, proyecto1,  sistema.getMiembro(proyecto1, "Usuario2"));
		
		//Listado de items para proyecto
		sistema.listarItems(proyecto1);
		
		//Creacion de miembros disponibles para un item
		Collection<Miembro> miembrosDisponibles = new HashSet<Miembro>();
		miembrosDisponibles.add(miembro1);
		miembrosDisponibles.add(miembro2);
		
		Calendar cal = Calendar.getInstance();
		
		//Cambiar estado de un item y mostrar estado actual
		cal.set(2010, 11, 11);
		
		try
		{
			sistema.cambiarEstadoItem(proyecto1, item1, estado1, miembro1, miembrosDisponibles,cal.getTime());
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.print("Estado actual del item:" + item1.getNombre() + " Estado:"+sistema.verEstadoActualItem(proyecto1, item1) + "\n");
		
		//Cambiar estado de un item y mostrar estado actual
		cal.set(2010, 11, 20);
		
		try
		{
			sistema.cambiarEstadoItem(proyecto1, item1, estado2, miembro2, miembrosDisponibles,cal.getTime());
		}  catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.print("Estado actual del item:"+item1.getNombre()+ " Estado:"+sistema.verEstadoActualItem(proyecto1, item1)+ "\n");
		
		//Cambiar estado de un item y probamos funcionalidad de historicos
		cal.set(2010, 12, 10);
		
		try
		{
			sistema.cambiarEstadoItem(proyecto1, item1, estado3, miembro1, miembrosDisponibles,cal.getTime());
		}  catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.print("Estado actual del item:"+item1.getNombre()+ " Estado:"+sistema.verEstadoActualItem(proyecto1, item1)+ "\n\n");
		
		cal.set(2010, 11, 12);
		sistema.listarEstadosHistoricosItem(proyecto1, item1, cal.getTime() , null);
		
		
		//Cambiar estado de un item con miembro no valido
		
		cal.set(2011, 01, 01);
		try
		{
			sistema.cambiarEstadoItem(proyecto1, item1, estado4, miembro3, miembrosDisponibles,cal.getTime());
		}  catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		

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
