<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
<body>
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Usuarios del sistema</h2>
    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
    		<td style="width:200px;">
    			Usuario
    		</td>
    		<td class="tabla_centrado" style="width:80px;">
    			Propiedades
    		</td>
    		<td class="tabla_centrado" style="width:80px;">
    			Eliminar
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<hr>
    		</td>
    	</tr>
    	
    	<%
    	/* ----- codigo de prueba, recorrer colección de proyectos */
    	/* --- Cómo se identifica unívocamente a un proyecto? LINKS */
    	for(int i=1;i<=10;i++)
    	{
    		%><tr <%= i%2==0?"style=\"background-color:#eeeeee;\"":""%>>
	    		<td>
	    			Nombre Usuario <%= i%>
	    		</td>
	    		<td class="tabla_centrado">
	    			<a href="usuario_editar.jsp?id=<%= i %>"> 
	    				<img class="icono_chico" src="iconos/usuario_editar.png" 
	    					title="Editar _usuarioNombre<%= i %>_">
	    			</a>
	    		</td>
	    		<td class="tabla_centrado">
	    			<a href="usuario_eliminar.jsp?id=<%= i %>">
	    				<img class="icono_chico" src="iconos/borrar.png" 
	    					title="Eliminar _usuarioNombre<%= i %>_">
	    			</a>
	    		</td>
    		</tr><% 
    	}
    	%>
	</table>
	<%@ include file="footer.jsp" %>
</body>
</html>