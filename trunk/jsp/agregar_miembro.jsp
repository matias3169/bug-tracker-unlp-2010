<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>

	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.id)}"/>
	<c:set var="rolesProyecto" value="${sessionScope.sistema.getRolesProyecto()}"/>
	<c:set var="usuarios" value="${sessionScope.sistema.getUsuarios()}"/>
	<c:set var="miembrosProyecto" value="${sessionScope.sistema.getMiembrosProyecto(proyecto)}"/>
		
<body>

	<html:javascript formName="agregarMiembroForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar miembro a <c:out value="${proyecto.getNombre()}"/></h2>
    
	<html:form method="POST" action="/agregar_miembro">
	
	    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
		<table>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_usuario">Usuario</div>
				</td>
				<td class="tabla_etiqueta">
					<div id="etiqueta_clave">Rol</div>
				</td>
			</tr>
			<tr>
				<td class="tabla_input">
					<select name="usuario">
						<option value="null">&nbsp;</option>
	    					<c:forEach var="usuario" items="${usuarios}">
	    						<c:set var="contains" value="false" />
	    						<c:forEach var="miembroProyecto" items="${miembrosProyecto}">
	    							<c:if test="${miembroProyecto.getUsuario().equals(usuario)}">
	    								<c:set var="contains" value="true" />								
									</c:if>
			         			</c:forEach>
			         			
			         			<c:if test="${contains eq false}">
			         				<option><c:out value="${usuario.getNombre()}"/></option>
			         			</c:if>
			         		</c:forEach>
	    			</select>		
				</td>
			
				<td class="tabla_input">
					<select name="rol_miembro">
						<option value="null">&nbsp;</option>
	    					<c:forEach var="rol" items="${rolesProyecto}">
	    						<c:if test='${rol.getNombre() ne "Lider"}'>
									<option><c:out value="${rol.getNombre()}"/></option>
								</c:if>
			         		</c:forEach>
	    			</select>		
				</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregar_miembro" value="Agregar miembro">
				</td>
			</tr>
		</table>
	</html:form>
	<%@ include file="footer.jsp" %>
</body>
</html:html>