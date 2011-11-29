<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>
	
	<c:set var="proyecto" value="${sessionScope.sistema.getProyectoPorNombre(param.idP)}"/>
	<c:set var="usuario" value="${param.idN}"/>
	<c:set var="rol" value="${param.idR}"/>
	<c:set var="rolesProyecto" value="${sessionScope.sistema.getRolesProyecto()}"/>
	
<body>
	<html:javascript formName="editarItemForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar Miembro <c:out value="${usuario}"/></h2>
    
    <html:form action="/editarMiembro"  method="post">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    
   <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:90px;">
    			Nombre
 		   </td>
 		   <td class="tabla_centrado" style="width:90px;">
    			Rol
 		   </td>
   		</tr>
   		<tr>
    		<td colspan="2">
    			<hr>
    		</td>
    	</tr>      				 
    
  		<tr style='background-color:#eeeeee;'>
  			<td class="tabla_input">
    		    <input type="text" name="nombreMiembro" id="nombreMiembro" value="${usuario}" readonly="readonly">
    		</td>
    		<td>
				<select name="descRol">
	    			<c:forEach var="role" items="${rolesProyecto}">
	    				<c:if test='${role.getNombre() != "Lider"}'>
	    					<c:choose>
								<c:when test="${role.getNombre() == rol}">
			    		   			<option selected="selected"><c:out value="${role.getNombre()}"/></option>
								</c:when> 
								<c:otherwise>
									<option><c:out value="${role.getNombre()}"/></option>
								</c:otherwise>
							</c:choose>
						</c:if>
	         		</c:forEach>
	    		</select>
	    	</td>   
	   </tr> 
    	<tr height="4"></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="4">
				<input type="submit" name="editarMiembro" value="Guardar cambios">
			</td>
   		</tr>
   	</table>
	</html:form>
   	<%@ include file="footer.jsp" %> 
    
</body>
</html:html> 	