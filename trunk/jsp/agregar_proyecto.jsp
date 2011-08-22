<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>
	
	<c:set var="usuariosSistema" value="${sessionScope.sistema.getUsuarios()}"/>
	
<body>
	<html:javascript formName="agregarProyectoForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar nuevo proyecto</h2>
    
	<html:form method="POST" action="/agregar_proyecto">
		<table>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_usuario">Nombre del proyecto:</div>
				</td>
				<td class="tabla_input">
					<input type="text" name="nombre_proyecto">
				</td>
			</tr>
			
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_clave">L&iacute;der de proyecto:</div>
				</td>
				<td class="tabla_input">
					<select name="lider_proyecto">
						<option value="null">&nbsp;</option>
							<c:forEach var="usuario" items="${usuariosSistema}">
							<option><c:out value="${usuario.getNombre()}"/></option>
						 </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregar_proyecto" value="Crear proyecto">
				</td>
			</tr>
		</table>
	</html:form>
	<%@ include file="footer.jsp" %>
</body>
</html:html>