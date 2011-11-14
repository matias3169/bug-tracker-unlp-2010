<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>
	
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="item" value="${proyecto.getItemPorId(param.idI)}"/>

<body>
	<html:javascript formName="cambiarEstadoForm" src="popcalendar.js" />
	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Cambiar estado <c:out value="${item.getNombre()}"/></h2>
    
    <html:form action="/cambiarEstado"  method="post">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    <html:hidden property="nombreItem" value="${item.getNombre()}" />

    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:100px;">
    			Descripcion
 		   </td>
 		   <td class="tabla_centrado" style="width:90px;">
    			Responsable
 		   </td>
    	   <td class="tabla_centrado" style="width:90px;">
    			Estado Actual
		   </td>
   		   <td class="tabla_centrado" style="width:90px;">
			    Pasar a estado 
   		   </td>
   		   <td class="tabla_centrado" style="width:90px;">
			    Fecha 
   		   </td>
   		   <td class="tabla_centrado" style="width:90px;">
			    Ficha de Trabajo 
   		   </td>
   		</tr>
   		<tr>
    		<td colspan="6">
    			<hr>
    		</td>
    	</tr>      				 
    
  		<tr style='background-color:#eeeeee;'>
    		<td class="tabla_centrado">
    		    <c:out value="${item.getDescripcion()}"/>
    		</td>
    		<td class="tabla_centrado" style="width:90px;">
	    		<select name="nomNuevoResponsable">
	    			<c:forEach var="responsable" items="${proyecto.getMiembros()}">
	    				<c:choose>
							<c:when test="${responsable.getUsuario().getNombre() == item.getEstadoActual().getResponsable().getUsuario().getNombre()}">
		    		   			<option selected="selected"><c:out value="${responsable.getUsuario().getNombre()}"/></option>
							</c:when> 
							<c:otherwise>
								<option><c:out value="${responsable.getUsuario().getNombre()}"/></option>
							</c:otherwise>
						</c:choose>
	         		</c:forEach>
	    		</select>
	    	</td>
	    	
    		<td class="tabla_centrado" style="width:90px;">
    		    <c:out value="${item.getEstadoActual().getEstado().getDescripcion()}"/>
    		</td>
    		
    		<td class="tabla_centrado" style="width:90px;">
	    		<select name="descNuevoEstado">
					<c:forEach var="estado" items="${item.getEstadoActual().getEstado().getEstadosSiguientes()}">
						<option><c:out value="${estado.getDescripcion()}"/></option>
					</c:forEach>
				</select>
	    	</td>
	    	<td class="tabla_input">
    		    <input type="text" name="fechaEstado" id="fechaEstado" onclick="popUpCalendar(this,cambiarEstadoForm.fechaEstado, 'dd/mm/yyyy');" size="10">
    		</td>
	    	<td class="tabla_input">
    		    <input type="text" name="fichaTrabajoItem" >
    		</td>
    	</tr> 
    	<tr height="4"></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="6">
				<input type="submit" name="cambiarEstado" value="Guardar cambios">
			</td>
   		</tr>
   	</table>
	</html:form>
   	<%@ include file="footer.jsp" %> 
    
</body>
</html:html>