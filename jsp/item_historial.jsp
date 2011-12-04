<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="item" value="${proyecto.getItemPorId(param.idI)}"/>
	
<body>	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Historial del item <c:out value="${item.getNombre()}"/></h2>
    
    <table cellpadding="0" cellspacing="0" border="0">
   	<tr class="encabezado_tabla">
   		<td class="tabla_centrado" style="width:200px;">
   			Estado
   		</td>
   		<td class="tabla_centrado" style="width:200px;">
   			Fecha de Inicio
   		</td>
   		<td class="tabla_centrado" style="width:200px;">
   			Fecha de Fin
   		</td>
   		<td class="tabla_centrado" style="width:200px;">
   			Responsable
   		</td>
   		<td class="tabla_centrado" style="width:200px;">
   			Rol
   		</td>
   		<td class="tabla_centrado" style="width:200px;">
   			Notas
   		</td>
   		
   	</tr>
   	<tr>
   		<td colspan="8">
   			<hr>
   		</td>
   	</tr>
   	
   	<c:forEach var="estadoItem" items="${item.getHistorialEstados().listar()}" varStatus="i">
   		<c:choose>  
			<c:when test="${(i.count) % 2 == 0}">  
				<tr style="background-color:#eeeeee;">  
			</c:when>  
			<c:otherwise>  
				<tr>  
			</c:otherwise>  
		</c:choose> 
		
   			<td class="tabla_centrado">
    			<c:out value="${estadoItem.getEstado().getDescripcion()}"/>
    		</td>
    		<td class="tabla_centrado">
    			<fmt:formatDate type="both" dateStyle="short" timeStyle="long" value="${estadoItem.getFechaInicio()}"/>
    			<!-- <c:out value="${estadoItem.getFechaInicio().toString()}"/> -->
    		</td>
    		<td class="tabla_centrado">
    			<fmt:formatDate type="both" dateStyle="short" timeStyle="long" value="${estadoItem.getFechaFin()}"/>
				<!-- <c:out value="${estadoItem.getFechaFin().toString()}"/> -->
    		</td>
    		<td class="tabla_centrado">
				<c:out value="${estadoItem.getResponsable().getUsuario().getNombre()}"/>
    		</td>
    		<td class="tabla_centrado">
				<c:out value="${estadoItem.getResponsable().getRole().getNombre()}"/>
    		</td>
    		<td class="tabla_centrado">
				<c:out value="${estadoItem.getFichaDeTrabajo()}"/>
    		</td>
    	</tr>	
   	</c:forEach>	

	</table>
</body>
</html:html>