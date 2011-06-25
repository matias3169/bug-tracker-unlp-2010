<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
<body>
	<h1><%= BTUNLP_Titulo %></h1>
	<%
		/* toma el ID de proyecto recibido por GET */
		String id = request.getParameter( "id" );
	%>
    <h2>Proyecto <%= id %></h2>
    
    
    <h3>Items</h3>
    <%@ include file="_tablaItem.jsp" %>
    
    <h3>Miembros</h3>
    <h3>Tipos de &iacute;tems</h3>
    
	<%@ include file="footer.jsp" %>
</body>
</html>