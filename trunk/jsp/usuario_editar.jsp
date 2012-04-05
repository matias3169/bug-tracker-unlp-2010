<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>

	<c:set var="usuario" value="${sessionScope.sistema.getUsuarioPorID(param.id)}"/>
	<c:set var="rolesUsuario" value="${sessionScope.sistema.getRolesSistema()}"/>
	
<body>
	<html:javascript formName="editarUsuarioForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar datos del usuario</h2>
	
	<html:form action="/editar_usuario"  method="post">
	
	<html:hidden property="nombreUsuario" value="${usuario.getNombre()}" />
	
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
    		    <c:out value="${usuario.getNombre()}"/>
    		</td>
    		<td>
	    		<select name="nuevoRol">
	    			<c:forEach var="rol" items="${rolesUsuario}">
	    				<c:choose>
							<c:when test="${rol.equals(usuario.getRole())}">
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
    		    <input name="password" maxlength="8" type="password" value="${usuario.getClave()}"/>
    		</td>
    	</tr> 
    	<tr height="4"></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="4">
				<input type="submit" name="editar_usuario" value="Guardar cambios">
			</td>
   		</tr>
   	</table>
	</html:form>
   	<%@ include file="footer.jsp" %>     				 
</body>
</html:html>