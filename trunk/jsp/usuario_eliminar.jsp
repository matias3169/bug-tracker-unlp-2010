<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>
	<c:set var="usuario" value="${sessionScope.sistema.getUsuarioPorID(param.id)}"/>
<body>
	<html:javascript formName="eliminarUsuarioForm" />

    <html:form action="/eliminar_usuario"  method="post">
    
    <html:hidden property="nombreUsuario" value="${usuario.getNombre()}" />
    
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Borrar usuario</h2>
	<br>
    <h3><font color="red">¿Desea eliminar el usuario <c:out value="${usuario.getNombre()}"/>?</font></h3>
    <center><input type="submit" name="eliminar_usuario" value="Eliminar Usuario"></center>
    <br><br><br>
    
     </html:form>
    <%@ include file="footer.jsp" %>     				 
</body>
</html:html>