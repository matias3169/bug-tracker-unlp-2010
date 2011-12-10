<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

	<%@ include file="html_head.jsp" %>
	
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="tipoItem" value="${proyecto.getTipoItemPorId(param.idTI)}"/>

<body>
	<html:javascript formName="editarTipoItemForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Editar <c:out value="${tipoItem.getDescripcion()}"/></h2>
    <h3><font color="red"><html:errors/></font></h3>
    <html:form action="/editarTipoItem"  method="post" onsubmit="return validate(this);">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    <html:hidden property="descripcion_inicial" value="${tipoItem.getDescripcion()}" />
    
    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:90px;">
    			Descripcion
 		   </td>
    	   <td class="tabla_centrado" style="width:90px;">
    			Estado Inicial
		   </td>
   		</tr>
   		<tr>
    		<td colspan="5">
    			<hr>
    		</td>
    	</tr>
    	
    	<tr style='background-color:#eeeeee;'>
  			<td class="tabla_centrado">
    		    <input type="text" name="descripcion_nueva" id="descripcion_nueva" value="${tipoItem.getDescripcion()}">
    		</td>
    		<td class="tabla_centrado">
	    		<select name="nuevoEstadoInicial">
	    			<c:forEach var="estado" items="${tipoItem.getEstadosPosibles()}">
	    				<c:choose>
							<c:when test="${estado.getDescripcion() == tipoItem.getEstadoInicial().getDescripcion()}"> 
								<!-- muestra por default el estado inicial actual -->
		    		   			<option selected="selected"><c:out value="${estado.getDescripcion()}"/></option>
							</c:when> 
							<c:otherwise>
								<option><c:out value="${estado.getDescripcion()}"/></option>
							</c:otherwise>
						</c:choose>
	         		</c:forEach>
	    		</select>
	    	</td>
	   	<tr height="4"></tr>
	    
	    <!-- Sección Estados posibles -->
	    <tr><td> <br><br><br> </td></tr>	    	
	    
	    <tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:180px;">
    			Estados posibles
 			</td>
 			<!-- icono para agregar un estado posible nuevo -->
 			<td class="tabla_centrado">
    			<a href="tipoItem_agregarEstado.jsp?idTI=<c:out value="${tipoItem.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/agregar_proyecto.png" 
    					title="Agregar estado a <c:out value="${tipoItem.getDescripcion()}"/>">
    			</a>
    		</td>
 			<!-- icono para borrar un estado posible -->
    		<td class="tabla_centrado">
    			<a href="tipoItem_eliminarEstado.jsp?idTI=<c:out value="${tipoItem.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/borrar.png" 
    					title="Eliminar estado de <c:out value="${tipoItem.getDescripcion()}"/>">
    			</a>
    		</td>
   		</tr>
   		<tr>
    		<td colspan="5">
    			<hr>
    		</td>
    	</tr>
	    
	    <tr>
			<c:forEach var="estadoPosible" items="${tipoItem.getEstadosPosibles()}" varStatus="j">
				<c:choose>  
					<c:when test="${(j.count) % 2 == 0}">  
						<tr style="background-color:#eeeeee;">  
					</c:when>  
					<c:otherwise>  
						<tr>  
					</c:otherwise>  
				</c:choose> 
					
				<td class="tabla_centrado">
					<c:out value="${estadoPosible.getDescripcion()}"/>
				</td>
				
				<td class="tabla_centrado">
					Estados siguientes:
				</td>
				
				<td class="tabla_centrado">
		    		<select name="estadoSiguiente1">
		    			<c:forEach var="estadoSiguiente" items="${estadoPosible.getEstadosSiguientes()}">
		    				<option><c:out value="${estadoSiguiente.getDescripcion()}"/></option>
		         		</c:forEach>
		    		</select>
		    	</td>
				<!-- icono para agregar un estado siguiente nuevo -->
				<td class="tabla_centrado">
	    			<a href="estado_agregarEstadoSiguiente.jsp?idE=<c:out value="${estadoPosible.getId()}"/>&idTI=<c:out value="${tipoItem.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
	    				<img class="icono_chico" src="iconos/agregar_proyecto.png" 
	    					title="Agregar estado siguiente a <c:out value="${estadoPosible.getDescripcion()}"/>">
	    			</a>
	    		</td>
				<!-- icono para borrar un estado siguiente -->
	    		<td class="tabla_centrado">
	    			<a href="estado_eliminarEstadoSiguiente.jsp?idE=<c:out value="${estadoPosible.getId()}"/>&idTI=<c:out value="${tipoItem.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
	    				<img class="icono_chico" src="iconos/borrar.png" 
	    					title="Eliminar estado siguiente de <c:out value="${estadoPosible.getDescripcion()}"/>">
	    			</a>
	    		</td>
			</c:forEach>		    		
		</tr>
		<tr>
    		<td colspan="5">
    			<hr>
    		</td>
    	</tr>
    	<!-- Fin sección Estadis posibles -->
    	
    	<!-- Sección Miembros dispobibles -->
    	  <tr><td> <br><br><br> </td></tr>	    	
	    
	    <tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:180px;">
    			Miembros disponibles
 			</td>
 			<!-- icono para agregar un miembro disponible nuevo -->
 			<td class="tabla_centrado">
    			<a href="tipoItem_agregarMiembroDisponible.jsp?idTI=<c:out value="${tipoItem.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/agregar_proyecto.png" 
    					title="Agregar miembro disponible a <c:out value="${tipoItem.getDescripcion()}"/>">
    			</a>
    		</td>
 			<!-- icono para borrar un miembro disponible -->
    		<td class="tabla_centrado">
    			<a href="tipoItem_eliminarMiembroDisponible.jsp?idTI=<c:out value="${tipoItem.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/borrar.png" 
    					title="Eliminar miembro disponible de <c:out value="${tipoItem.getDescripcion()}"/>">
    			</a>
    		</td>
   		</tr>
   		<tr>
    		<td colspan="5">
    			<hr>
    		</td>
    	</tr>
	     <tr class="encabezado_tabla">
	     <td>Usuario:</td>
	     <td>Rol:</td>
	     </tr>
	    <tr>
			<c:forEach var="miembroDisponible" items="${tipoItem.getMiembrosDisponibles()}" varStatus="j">
				<c:choose>  
					<c:when test="${(j.count) % 2 == 0}">  
						<tr style="background-color:#eeeeee;">  
					</c:when>  
					<c:otherwise>  
						<tr>  
					</c:otherwise>  
				</c:choose> 
					
				<td class="tabla_centrado">
					<c:out value="${miembroDisponible.getUsuario().getNombre()}"/>
				</td>
				
				<td class="tabla_centrado">
	 				<c:out value="${miembroDisponible.getRole().getNombre()}"/>
				</td>
				
			</c:forEach>		    		
		</tr>
		<tr>
    		<td colspan="5">
    			<hr>
    		</td>
    	</tr>    	
    	<!-- Fin sección miembros disponibles -->
    	
    	<tr><td> <br><br> </td></tr>
    	<tr> 
    		<td class="tabla_centrado" colspan="4">
				<input type="submit" name="editarTipoItem" value="Guardar cambios">
			</td>
   		</tr>
   	</table>
	</html:form>
   	<%@ include file="footer.jsp" %> 
    
</body>
</html:html>