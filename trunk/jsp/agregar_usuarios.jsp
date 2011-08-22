<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>

	<%@ include file="html_head.jsp" %>
	<c:set var="rolesUsuario" value="${sessionScope.sistema.getRolesSistema()}"/>
		
<body>

	<html:javascript formName="agregarUsuarioForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar nuevo usuario</h2>
	<html:form method="POST" action="/agregar_usuario">
		<table>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_usuario">Usuario:</div>
				</td>
				<td class="tabla_input">
					<input type="text" name="nombre_usuario">
				</td>
			</tr>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_clave">Contrase&ntilde;a:</div>
				</td>
				<td class="tabla_input">
					<input type="password" name="clave_usuario">
				</td>
			</tr>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_clave">Rol:</div>
				</td>
				<td class="tabla_input">
					<select name="rol_usuario">
						<option value="null">&nbsp;</option>
	    					<c:forEach var="rol" items="${rolesUsuario}">
								<option><c:out value="${rol.getNombre()}"/></option>
			         		</c:forEach>
	    			</select>		
				</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregar_usuario" value="Agregar usuario">
				</td>
			</tr>
		</table>
	</html:form>
	<%@ include file="footer.jsp" %>
</body>
</html:html>