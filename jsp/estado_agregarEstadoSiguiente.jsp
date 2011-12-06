<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="tipoItem" value="${proyecto.getTipoItemPorId(param.idTI)}"/>
	<c:set var="estado" value="${tipoItem.getEstadoPorId(param.idE)}"/>
	
<body>
	<html:javascript formName="agregarEstadoSiguienteForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar Estado siguiente al estado <c:out value="${estado.getDescripcion()}"/></h2> 
       
    <html:form method="POST" action="/agregarEstadoSiguiente">
    
    <html:hidden property="idProyecto" value="${param.idP}" />
    <html:hidden property="idTipoItem" value="${param.idTI}" />
   	<html:hidden property="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}" />
    <html:hidden property="tipoItemDesc" value="${proyecto.getTipoItemPorId(param.idTI).getDescripcion()}" />
    <html:hidden property="estadoDesc" value="${tipoItem.getEstadoPorId(param.idE).getDescripcion()}" />

	<table>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_descripcion">Estado: </div>
				</td>
				<td class="tabla_centrado">
		    		<select name="estadoSiguienteDesc">
		    			<c:forEach var="estadoPosible" items="${tipoItem.getEstadosPosibles()}">
			    			<c:if test="${estadoPosible.getDescripcion() != estado.getDescripcion()}">
			    				<c:if test="${!estado.getEstadosSiguientes().contains(estadoPosible)}">
			    					<option><c:out value="${estadoPosible.getDescripcion()}"/></option>
			    				</c:if>
			    			</c:if>		         		
		         		</c:forEach>
		    		</select>
		    	</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregarEstadoSiguiente" value="Agregar estado">
				</td>
			</tr>
		</table>
	
	</html:form>
	<%@ include file="footer.jsp" %>

</body>
</html:html>