<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
	<c:set var="rolesUsuario" value="${sessionScope.sistema.getRolesSistema()}"/>
	<c:set var="Usuario" value="${sessionScope.sistema.getUsuarioPorID(param.id)}"/>
<body>
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar datos del usuario</h2>
	
    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td>
    			Nombre
 		   </td>
    	   <td>
    			Rol
		   </td>
   		   <td>
			    Clave 
   		   </td>
   		</tr>
   		<tr>
    		<td colspan="3">
    			<hr>
    		</td>
    	</tr>      				 
    
  		<tr style='background-color:#eeeeee;'>
    		<td class="tabla_centrado">
    		    <input name="nombre" maxlength="15" type="text" value="${Usuario.getNombre()}" />
    		</td>
    		<td>
	    		<select name="rol">
	    			<c:forEach var="rol" items="${rolesUsuario}">
	    				<c:choose>
							<c:when test="${rol.getNombre() == Usuario.getRole().getNombre()}">
		    		   			<option selected="selected"><c:out value="${rol.getNombre()}"/></option>
							</c:when>
							<c:otherwise>
								<option><c:out value="${rol.getNombre()}"/></option>
							</c:otherwise>
						</c:choose>
	         		</c:forEach>
	    		</select>
	    	</td>
    		<td>
    		    <input name="password" maxlength="8" type="password" value="${sessionScope.sistema.getUsuarioPorID(param.id).getClave()}"/>
    		</td>
    	</tr> 
    	<tr height="4"></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="4">
				<input type="submit" name="editar_usuario" value="Guardar cambios">
			</td>
   		</tr>
   	</table>
   	<%@ include file="footer.jsp" %>     				 
</body>
</html>