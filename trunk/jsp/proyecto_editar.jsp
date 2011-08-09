<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
	<% int i = 0; %>
<body>
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar datos del proyecto</h2>
	
    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td>
    			Nombre
 		   </td>
    	   <td>
    			Lider de Proyecto
		   </td>
   		</tr>
   		<tr>
    		<td colspan="3">
    			<hr>
    		</td>
    	</tr>      				 
    
  		<tr style='background-color:#eeeeee;'>
    		<td class="tabla_centrado">
    		    <input name="nombre" maxlength="15" type="text" value="${sessionScope.sistema.getProyecto(param.id).getNombre()}" />
    		</td>
    		<td>
    		    <input name="lider" maxlength="15" type="text" value="${sessionScope.sistema.getLiderProyecto(sessionScope.sistema.getProyecto(param.id)).getUsuario().getNombre()}"/>
    		</td>
    	</tr> 
    	<tr> 
    		<td></td>
    		<td>   		
    			<a href="editar_propiedades_proyecto.jsp?id=<c:out value='${param.id}'/>"> 
    				<img class="icono_chico" src="iconos/cambiar_estado.png" 
    					title="Editar propiedades de <c:out value='${sessionScope.sistema.getProyecto(param.id).getNombre()}'/>">
    			</a>
    		</td>
   		</tr>
   	</table>     				 
</body>
</html>