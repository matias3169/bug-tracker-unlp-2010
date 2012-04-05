<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>
	
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="item" value="${sessionScope.sistema.getItemId(param.idI)}"/>

<body>
	<html:javascript formName="editarItemForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar <c:out value="${item.getNombre()}"/></h2>
    
    <html:form action="/editarItem"  method="post">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    <html:hidden property="nombreItem" value="${item.getNombre()}" />

    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:90px;">
    			Descripcion
 		   </td>
 		   <td class="tabla_centrado" style="width:90px;">
    			Responsable
 		   </td>
    	   <td class="tabla_centrado" style="width:90px;">
    			Estado Actual
		   </td>
   		   <td class="tabla_centrado" style="width:90px;">
			    Prioridad 
   		   </td>
   		   <td class="tabla_centrado" style="width:90px;">
			    Ficha de Trabajo 
   		   </td>
   		</tr>
   		<tr>
    		<td colspan="5">
    			<hr>
    		</td>
    	</tr>      				 
    
  		<tr style='background-color:#eeeeee;'>
  			<td class="tabla_input">
    		    <input type="text" name="descripcion" id="descripcion" value="${item.getDescripcion()}">
    		</td>
    		<td>
	    		<select name="nomNuevoResponsable">
	    			<c:forEach var="responsable" items="${sessionScope.sistema.getMiembrosEstado(item.getEstadoActual().getEstado())}">
	    				<c:choose>
							<c:when test="${responsable.equals(item.getEstadoActual().getResponsable())}">
		    		   			<option selected="selected"><c:out value="${responsable.getUsuario().getNombre()}"/></option>
							</c:when> 
							<c:otherwise>
								<option><c:out value="${responsable.getUsuario().getNombre()}"/></option>
							</c:otherwise>
						</c:choose>
	         		</c:forEach>
	    		</select>
	    	</td>    	
    		<td>
    		    <c:out value="${item.getEstadoActual().getEstado().getDescripcion()}"/>
    		</td>
	    	<td class="tabla_input">
    		    <input type="text" name="prioridad" id="prioridad" value="${item.getPrioridad()}" size="5">
    		</td>
	    	<td class="tabla_input">
    		    <input type="text" name="fichaTrabajoItem" value="${item.getEstadoActual().getFichaDeTrabajo()}"> 
    		</td>
    	</tr> 
    	<tr height="4"></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="5">
				<input type="submit" name="editarItem" value="Guardar cambios">
			</td>
   		</tr>
   	</table>
	</html:form>
   	<%@ include file="footer.jsp" %> 
    
</body>
</html:html>