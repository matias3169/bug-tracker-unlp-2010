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
	<html:javascript formName="agregarMiembroDisponibleForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar Miembro disponible al estado <c:out value="${estado.getDescripcion()}"/></h2> 
    
  	<html:form method="POST" action="/agregarMiembroDisponible">
  	
  	<html:hidden property="idProyecto" value="${param.idP}" />
    <html:hidden property="idTipoItem" value="${param.idTI}" />
   	<html:hidden property="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}" />
    <html:hidden property="tipoItemDesc" value="${tipoItem.getDescripcion()}" />
    <html:hidden property="estadoDesc" value="${estado.getDescripcion()}" />
    
    <table>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_descripcion">Miembro: </div>
				</td>
				<td class="tabla_centrado">
		    		<select name="nuevoMiembroDispNombre">
		    			<c:forEach var="miembroProyecto" items="${proyecto.getMiembros()}">
			    			<c:if test="${!estado.getMiembrosDisponibles().contains(miembroProyecto)}">
			    				<option><c:out value="${miembroProyecto.getUsuario().getNombre()}"/></option>
			    			</c:if>		         		
		         		</c:forEach>
		    		</select>
		    	</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregarMiembroDisponible" value="Agregar miembro disponible">
				</td>
			</tr>
		</table>  
  	
  	</html:form>

	<%@ include file="footer.jsp" %>

</body>
</html:html>