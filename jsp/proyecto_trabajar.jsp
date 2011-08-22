<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
	
<body>
	<h1><%= BTUNLP_Titulo %></h1>
	
    <h2><c:out value="${sessionScope.sistema.getProyecto(param.id).getNombre()}"/> </h2>
    
    <c:set var="itemsProyecto" value="${sessionScope.sistema.getProyecto(param.id).getItems()}" />
    <h3>Items</h3>
    <%@ include file="_tablaItem.jsp" %>
    
    <c:set var="miembrosProyecto" value="${sessionScope.sistema.getProyecto(param.id).getMiembros()}" />
    <h3>Miembros</h3>
    <%@ include file="_tablaMiembros.jsp" %>
    
    
    <h3>Tipos de &iacute;tems</h3>
    
	<%@ include file="footer.jsp" %>
</body>
</html>