<%@ page import="unlp.edu.core.*, java.util.*" %>

<%
    out.println("<select name='responsable_item' id='responsable_item'>");

if(request.getParameter("TipoItemId")!=null){
	
	String tipoItemId = request.getParameter("TipoItemId");
	String proyecto = request.getParameter("Proyecto");
	String estadoparam = request.getParameter("Estado");
	
	Sistema sistema = Sistema.getInstance();
	Proyecto proy = sistema.getProyectoPorNombre(proyecto);
	
	Estado estado;

	if (estadoparam == null)
	{
		estado = sistema.getTipoItem(tipoItemId,proy).getEstadoInicial();	
	} else {
		estado = sistema.getTipoItem(tipoItemId,proy).getEstado(estadoparam);
	}
	
	for (Miembro miembro: estado.getMiembrosDisponibles())
	{
		out.println("<option>" + miembro.getUsuario().getNombre() + "</option>");
	}
	
	out.println("</select>");
}
%>
