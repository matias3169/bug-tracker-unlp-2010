<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
	<% int i = 0; %>
<body>
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Usuarios del sistema</h2>
    
     <c:set var="usuariosSistema" value="${sessionScope.sistema.getUsuarios()}" />
    
    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
    		<td style="width:200px;">
    			Usuario
    		</td>
    		<td class="tabla_centrado" style="width:80px;">
    			Editar
    		</td>
    		<td class="tabla_centrado" style="width:80px;">
    			Eliminar
    		</td>
    	</tr>
    	<tr>
    		<td colspan="3">
    			<hr>
    		</td>
    	</tr>
    	
    	<c:forEach var="usuario" items="${usuariosSistema}">
    	<%
    	 i = i + 1;
    		%><tr <%= i%2==0?"style=\"background-color:#eeeeee;\"":""%>>
	    		<td>
	    			<c:out value="${usuario.getNombre()}"/>
	    		</td>
	    		<td class="tabla_centrado">
	    			 <a href="usuario_editar.jsp?id=<c:out value="${usuario.getId()}"/>"> 
         			<img class="icono_chico" src="iconos/usuario_editar.png" 
         				 title="Editar <c:out value="${usuario.getNombre()}"/>">
       				 </a>
	    		</td>
	    		<td class="tabla_centrado">
	    			<a href="usuario_eliminar.jsp?id=<c:out value="${usuario.getId()}"/>">
        			 <img class="icono_chico" src="iconos/borrar.png" 
         				 title="Eliminar <c:out value="${usuario.getNombre()}"/>">
      			    </a>
	    		</td>
    		</tr>    	
    	</c:forEach>
  
	</table>
	<%@ include file="footer.jsp" %>
</body>
</html>