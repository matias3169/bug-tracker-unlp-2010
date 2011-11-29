<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table cellpadding="0" cellspacing="0" border="0">
   	<tr class="encabezado_tabla">
   		<td class="tabla_centrado">
   			Usuario
   		</td>
   		<td class="tabla_centrado">
   			Rol
   		</td>
   		<td class="tabla_centrado">
   			Editar
   		</td>   		
   	</tr>
   	<tr>
   	
   	<td colspan="3">
   		<hr>
   	</td>
   	</tr>
   	
   	<tr>
   	<c:forEach var="miembroProyecto" items="${miembrosProyecto}" varStatus="j">
   	<c:choose>  
		<c:when test="${(j.count) % 2 == 0}">  
			<tr style="background-color:#eeeeee;">  
		</c:when>  
		<c:otherwise>  
			<tr>  
		</c:otherwise>  
	</c:choose> 
		   		
	<td class="tabla_centrado">
		<c:out value="${miembroProyecto.getUsuario().getNombre()}"/>
	 </td>
	<td class="tabla_centrado">
	 	<c:out value="${miembroProyecto.getRole().getNombre()}"/>
	</td>
	<td class="tabla_centrado">
		<c:if test='${miembroProyecto.getRole().getNombre() != "Lider"}'>
	    	<a href="miembro_editarMiembro.jsp?idP=<c:out value="${miembroProyecto.getProyecto().getNombre()}"/>&idN=<c:out value="${miembroProyecto.getUsuario().getNombre()}"/>&idR=<c:out value="${miembroProyecto.getRole().getNombre()}"/>"> 
	    		<img class="icono_chico" src="iconos/proyecto_editar.png" 
	    		title="Editar Miembro <c:out value="${miembroProyecto.getUsuario().getNombre()}"/>">
	    	</a>
	   	</c:if> 
    </td>

   	</c:forEach>
   	
   	</tr>
   	<tr>
   		<td colspan="3">
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
