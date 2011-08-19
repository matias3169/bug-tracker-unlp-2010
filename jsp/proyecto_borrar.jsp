<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.id)}"/>
	
<body>
<html:javascript formName="eliminarProyectoForm" />

    <html:form action="/eliminar_proyecto"  method="post">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
        
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Borrar proyecto</h2>
	<br>
    <h3><font color="red">¿Desea eliminar el proyecto <c:out value="${proyecto.getNombre()}"/>?</font></h3>

    <center><input type="submit" name="eliminar_proyecto" value="Eliminar Proyecto"></center>
    <br><br><br>
    
    </html:form>
    <%@ include file="footer.jsp" %>
         				 
</body>

</html:html>