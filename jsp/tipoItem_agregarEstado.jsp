<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	
<body>
	<html:javascript formName="agregarEstadoTipoItemForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar Estado a Tipo de Item <c:out value="${sessionScope.sistema.getProyecto(param.idP).getTipoItemPorId(param.idTI).getDescripcion()}"/></h2>
    
    <html:form method="POST" action="/agregarEstadoTipoItem">
    
    <html:hidden property="idProyecto" value="${param.idP}" />
    <html:hidden property="idTipoItem" value="${param.idTI}" />
    <html:hidden property="tipoItem" value="${sessionScope.sistema.getProyecto(param.idP).getTipoItemPorId(param.idTI).getDescripcion()}" />
    
    <table>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_descripcion">Estado para Tipo de Item: </div>
				</td>
				<td class="tabla_input">
					<input type="text" name="descripcionEstado">
				</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregarEstadoTipoItem" value="Agregar estado">
				</td>
			</tr>
		</table>
	
	</html:form>
	<%@ include file="footer.jsp" %>
</body>
</html:html>