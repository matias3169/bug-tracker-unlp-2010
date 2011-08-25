<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table cellpadding="0" cellspacing="0" border="0">
   	<tr class="encabezado_tabla">
   		<td class="tabla_centrado">
   			Usuario
   		</td>
   		<td class="tabla_centrado">
   			Rol
   		</td>
   	</tr>
   	<tr>
   		<td colspan="2">
   			<hr>
   		</td>
   	</tr>
   	
   	<c:forEach var="miembroProyecto" items="${miembrosProyecto}" varStatus="i">
   	<c:choose>  
		<c:when test="${(i.count) % 2 == 0}">  
			<tr style="background-color:#eeeeee;">  
		</c:when>  
		<c:otherwise>  
			<tr>  
		</c:otherwise>  
	</c:choose> 
		   		<td class="tabla_centrado">
	   				<c:out value="${miembroProyecto.getUsuario().getNombre()}"/>
	   			</td>
	   			<td>
	   				<c:out value="${miembroProyecto.getUsuario().getRole().getNombre()}"/>
	   			</td>
   			</tr>
   	</c:forEach>
   	<tr>
   		<td colspan="2">
   			<hr>
   		</td>
   	</tr>
   	<tr>
   		<td colspan="2">
   			<table cellpadding="0" cellspacing="0" border="0">
   				<tr>
	   				<td class="tabla_centrado" colspan="5" style="font-style:italic;">
	   					Agregar miembro al proyecto &nbsp;&nbsp;
	   				</td>
	   				<td class="tabla_centrado" colspan="2" style="font-style:italic;">
	   					<a href="agregar_miembro.jsp?id=<c:out value="${param.id}"/>"> 
	    					<img class="icono_chico" src="iconos/agregar_usuario.png" 
	    						title="Asignar miembro al proyecto">
	    				</a>
	   				</td>
	   			</tr>
   			</table>
   		</td>
   	</tr>
</table>
