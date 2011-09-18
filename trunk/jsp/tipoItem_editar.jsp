<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

	<%@ include file="html_head.jsp" %>
	
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="tipoItem" value="${proyecto.getTipoItemPorId(param.idTI)}"/>

<body>
	<html:javascript formName="editarTipoItemForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar <c:out value="${tipoItem.getDescripcion()}"/></h2>
    
    <html:form action="/editarTipoItem"  method="post">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    <html:hidden property="descripcionTipoItem" value="${tipoItem.getDescripcion()}" />
    
    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:90px;">
    			Descripcion
 		   </td>
    	   <td class="tabla_centrado" style="width:90px;">
    			Estado Inicial
		   </td>
   		</tr>
   		<tr>
    		<td colspan="5">
    			<hr>
    		</td>
    	</tr>
    	
    	<tr style='background-color:#eeeeee;'>
  			<td class="tabla_input">
    		    <input type="text" name="descripcion_tipoItem" id="descripcion_tipoItem" value="${tipoItem.getDescripcion()}">
    		</td>
    		<td class="tabla_centrado">
	    		<select name="nuevoEstadoInicial">
	    			<c:forEach var="estado" items="${tipoItem.getEstadosPosibles()}">
	    				<c:choose>
							<c:when test="${estado.getDescripcion() == tipoItem.getEstadoInicial().getDescripcion()}"> //muestra por default el estado inicial actual
		    		   			<option selected="selected"><c:out value="${estado.getDescripcion()}"/></option>
							</c:when> 
							<c:otherwise>
								<option><c:out value="${estado.getDescripcion()}"/></option>
							</c:otherwise>
						</c:choose>
	         		</c:forEach>
	    		</select>
	    	</td>
	    	<tr height="4"></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="4">
				<input type="submit" name="editarTipoItem" value="Guardar cambios">
			</td>
   		</tr>
   	</table>
	</html:form>
   	<%@ include file="footer.jsp" %> 
    
</body>
</html:html>