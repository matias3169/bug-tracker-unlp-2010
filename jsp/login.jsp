<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String usuario = request.getParameter( "nombre_usuario" );
	String clave = request.getParameter( "clave_usuario" );
	
    if(usuario.equals("admin") && clave.equals("admin"))
    	response.sendRedirect("sistema.jsp");
    else
    	response.sendRedirect("index.jsp");

%>

