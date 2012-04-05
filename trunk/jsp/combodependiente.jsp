<%@ page import="unlp.edu.core.*, java.util.*" %>

<%
    out.println("<select name='responsable_item' id='responsable_item'>");

if(request.getParameter("TipoItemId")!=null){
	
	String tipoItemDesc = request.getParameter("TipoItemId");
	String proyecto = request.getParameter("Proyecto");
	String estadoparam = request.getParameter("Estado");
	
	Sistema sistema = Sistema.getInstance();
	Proyecto proy = sistema.getProyectoPorNombre(proyecto);
	TipoItem tipoItem = sistema.getTipoItem(tipoItemDesc,proy);
	
	Estado estado;

	if (estadoparam == null)
	{	
		estado = tipoItem.getEstadoInicial();	
	} else {
		estado = sistema.getEstadoTipoItem(tipoItem,estadoparam);
	}
	
	for (Miembro miembro: sistema.getMiembrosEstado(estado))
	{
		out.println("<option>" + miembro.getUsuario().getNombre() + "</option>");
	}
	
	out.println("</select>");
}
%>