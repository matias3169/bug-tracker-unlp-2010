<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="item" value="${sessionScope.sistema.getItemId(param.idI)}"/>
	
<body>
<html:javascript formName="eliminarItemForm" />

	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Eliminar Item</h2>
	<h3><font color="red"><html:errors/></font></h3>
	
    <html:form action="/eliminarItem"  method="post">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    <html:hidden property="nombreItem" value="${item.getNombre()}" />
    
	<br>
    <h3><font color="red">¿Desea eliminar el item <c:out value="${item.getNombre()}"/>?</font></h3>
	<h4 align="center"><font color="blue">** Tambien se eliminara la informacion historica del item **</font></h4>
    <center><input type="submit" name="eliminar_item" value="Eliminar Item"></center>
    <br><br><br>
    
    </html:form>
    <%@ include file="footer.jsp" %>
         				 
</body>

</html:html>