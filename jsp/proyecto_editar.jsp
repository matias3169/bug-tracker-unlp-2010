<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.id)}"/>
	<c:set var="usuarios" value="${sessionScope.sistema.getUsuarios()}"/>
	<c:set var="lider" value="${proyecto.getLiderProyecto()}"/>

<body>
<html:javascript formName="editarProyectoForm" />

<script type='text/javascript'>
function warning()
{
	alert('El antiguo lider de proyecto tomara un rol de Desarrollador');
}
</script> 

	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar datos del proyecto</h2>

	<html:form action="/editar_proyecto"  method="post">
	
	<html:hidden property="nombreActualProyecto" value="${proyecto.getNombre()}" />
		
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
    		    <input name="nombreProyecto" maxlength="15" type="text" value="${proyecto.getNombre()}" />
    		</td>
    		<td>
    		<select name="liderProyecto" onchange='javascript:warning();'>
	    			<c:forEach var="usuario" items="${usuarios}">
	    				<c:choose>
							<c:when test="${usuario.getNombre() == lider.getUsuario().getNombre()}">
		    		   			<option selected="selected"><c:out value="${usuario.getNombre()}"/></option>
							</c:when>
							<c:otherwise>
								<option><c:out value="${usuario.getNombre()}"/></option>
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
   	
   	</html:form>			
   	<%@ include file="footer.jsp" %>	 
</body>
</html:html>