<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	
	<c:set var="tipoItem" value="${sessionScope.sistema.getTipoItemID(param.idTI)}"/>
	<c:set var="estado" value="${sessionScope.sistema.getEstadoID(param.idE)}"/>
	
<body>
	<html:javascript formName="eliminarMiembroDisponibleForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Eliminar Miembro de Estado <c:out value="${estado.getDescripcion()}"/></h2>
    
    <html:form method="POST" action="/eliminarMiembroDisponible">

	<html:hidden property="descripcionEstado" value="${estado.getDescripcion()}" />
	<html:hidden property="idTipoItem" value="${param.idTI}" />
	<html:hidden property="idProyecto" value="${param.idP}" />
    
    <table>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_descripcion">Seleccione miembro a eliminar: </div>
				</td>
				<td class="tabla_centrado">
	    		<select name="descripcionMiembro">
	    			<c:forEach var="miembro" items="${sessionScope.sistema.getMiembrosEstado(estado)}">
						<option><c:out value="${miembro.getUsuario().getNombre()}"/></option>
	         		</c:forEach>
	    		</select>
	    		</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="eliminarMiembroDisponible" value="Eliminar miembro">
				</td>
			</tr>
		</table>
	
	</html:form>
	<%@ include file="footer.jsp" %>
</body>
</html:html>