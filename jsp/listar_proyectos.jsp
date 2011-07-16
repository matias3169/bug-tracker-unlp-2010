<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp"%>
	<% int i = 0; %>
<body>
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Proyectos del sistema</h2>
    
    <c:set var="proyectosUsuario" value="${sessionScope.sistema.listarProyectosUsuario(sessionScope.user)}" />
    
    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
    		<td style="width:200px;">
    			Proyecto
    		</td>
    		<td class="tabla_centrado" style="width:80px;">
    			Trabajar
    		</td>
    		<td class="tabla_centrado" style="width:80px;">
    			Propiedades
    		</td>
    		<td class="tabla_centrado" style="width:80px;">
    			Eliminar
    		</td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			<hr>
    		</td>
    	</tr>
    	<c:forEach var="proyectoUsuario" items="${proyectosUsuario}">
    	<%
    	 i = i + 1;
    		%><tr <%= i%2==0?"style=\"background-color:#eeeeee;\"":""%>>
	    		<td>
	    			<c:out value="${proyectoUsuario.getNombre()}"/>
	    		</td>
	    		<td class="tabla_centrado">
	    			<a href="proyecto_trabajar.jsp?id=<c:out value="${proyectoUsuario.getId()}"/>"> 
	    				<img class="icono_chico" src="iconos/proyecto_trabajar.png" 
	    					title="Trabajar en <c:out value="${proyectoUsuario.getNombre()}"/>">
	    			</a>
	    		</td>
	    		<td class="tabla_centrado">
	    			<a href="proyecto_editar.jsp?id=<c:out value="${proyectoUsuario.getId()}"/>">
	    				<img class="icono_chico" src="iconos/proyecto_editar.png" 
	    					title="Editar <c:out value="${proyectoUsuario.getNombre()}"/>">
	    			</a>
	    		</td>
	    		<td class="tabla_centrado">
	    			<a href="proyecto_borrar.jsp?id=<c:out value="${proyectoUsuario.getId()}"/>">
	    				<img class="icono_chico" src="iconos/borrar.png" 
	    					title="Eliminar <c:out value="${proyectoUsuario.getNombre()}"/>">
	    			</a>
	    		</td>
    		</tr>    	
    	</c:forEach>
    	
	</table>
	<%@ include file="footer.jsp" %>
</body>
</html:html>