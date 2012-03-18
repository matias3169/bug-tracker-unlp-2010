<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

<%@ include file="html_head.jsp" %>

<body>

	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Acciones del sistema</h2>
    <h3>Usuario:<bean:write name="user" property="nombre"/> </h3>
    <table>
	    	<tr id= "proyectos" >
	    		<td>
	    			Proyectos
	    		</td>
	    		<td>
	    			<a href="listar_proyectos.jsp">
	    				<img class="icono" src="iconos/listar_proyectos.png" title="Listar proyectos">
	    			</a>
	    		</td>
	    		<td>
	    			<a href="agregar_proyecto.jsp">
	    				<img class="icono" src="iconos/agregar_proyecto.png" title="Nuevo proyecto">
	    			</a>
	    		</td>
	    	</tr>
    	<c:if test="${sessionScope.user.getRole().getNombre() == 'Administrador'}">
	    	<tr id= "usuarios">
	    		<td>
	    			Usuarios
	    		</td>
	    		<td>
	    			<a href="listar_usuarios.jsp">
	    				<img class="icono" src="iconos/listar_usuarios.png" title="Listar usuarios">
	    			</a>
	    		</td>
	    		<td>
	    			<a href="agregar_usuarios.jsp">
	    				<img class="icono" src="iconos/agregar_usuario.png" title="Nuevo usuario">
	    			</a>
	    		</td>
	    	</tr>
	      </c:if>
	</table>
	<%@ include file="footer.jsp" %>
</body>
</html:html>