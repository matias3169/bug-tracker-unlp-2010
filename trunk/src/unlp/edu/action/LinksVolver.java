package unlp.edu.action;

import java.util.HashMap;
import java.util.Map;

public class LinksVolver {
	
	Map<String, String> mapaLink = new HashMap<String, String>();
	
	// Mapa de links para volver al nivel anterior
	public LinksVolver() {
		// Agregando links
		this.mapaLink.put("/sistema.jsp", "/sistema.jsp");
		this.mapaLink.put("/listar_proyectos.jsp", "/sistema.jsp");
		this.mapaLink.put("/agregar_proyecto.jsp", "/sistema.jsp");
		this.mapaLink.put("/proyecto_trabajar.jsp", "/listar_proyectos.jsp");
		this.mapaLink.put("/proyecto_editar.jsp", "/listar_proyectos.jsp");
		this.mapaLink.put("/proyecto_borrar.jsp", "/listar_proyectos.jsp");
		this.mapaLink.put("/listar_usuarios.jsp", "/sistema.jsp");
		this.mapaLink.put("/agregar_usuarios.jsp", "/sistema.jsp");
		this.mapaLink.put("/item_agregar.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/agregar_miembro.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/tipoItem_agregar.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/item_editar.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/item_cambiarEstado.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/item_historial.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/item_eliminar.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/miembro_editarMiembro.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/tipoItem_editar.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/tipoItem_agregarEstado.jsp", "/proyecto_trabajar.jsp");
		this.mapaLink.put("/tipoItem_agregarEstado.jsp", "/tipoItem_editar.jsp");
		this.mapaLink.put("/tipoItem_eliminarEstado.jsp", "/tipoItem_editar.jsp");
		this.mapaLink.put("/estado_agregarEstadoSiguiente.jsp", "/tipoItem_editar.jsp");
		this.mapaLink.put("/estado_eliminarEstadoSiguiente.jsp", "/tipoItem_editar.jsp");
	}

	public String getPaginaAnterior(String actual)
	{
		return this.mapaLink.get(actual);
	}
   
}
