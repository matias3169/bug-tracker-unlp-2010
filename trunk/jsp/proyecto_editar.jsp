<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.id)}"/>
	<c:set var="miembrosProyecto" value="${sessionScope.sistema.getMiembrosProyecto(proyecto)}"/>
	<c:set var="liderProyecto" value="${sessionScope.sistema.getLiderProyecto(proyecto)}"/>

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
    		    <input name="nombre" maxlength="15" type="text" value="${proyecto.getNombre()}" />
    		</td>
    		<td>
    		<select name="lider">
	    			<c:forEach var="miembro" items="${miembrosProyecto}">
	    				<c:choose>
							<c:when test="${miembro.getUsuario().getNombre() == liderProyecto.getRole().getNombre()}">
		    		   			<option selected="selected"><c:out value="${miembro.getUsuario().getNombre()}"/></option>
							</c:when>
							<c:otherwise>
								<option><c:out value="${miembro.getUsuario().getNombre()}"/></option>
							</c:otherwise>
						</c:choose>
	         		</c:forEach>
	    		</select>
    		</td>
    	</tr> 
    	<tr height="4"></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="2">
				<input type="submit" name="editar_proyecto" value="Guardar cambios">
			</td>
		</tr>
   	</table>     			
   	<%@ include file="footer.jsp" %>	 
</body>
</html>