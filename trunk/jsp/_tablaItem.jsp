<table cellpadding="0" cellspacing="0" border="0">
   	<tr class="encabezado_tabla">
   		<td class="tabla_centrado" style="width:170px;">
   			Tipo
   		</td>
   		<td class="tabla_centrado">
   			Descripci&oacute;n
   		</td>
   		<td class="tabla_centrado">
   			Estado actual
   		</td>
   		<td class="tabla_centrado" style="width:90px;">
   			Prioridad
   		</td>
   		<td class="tabla_centrado" style="width:90px;">
   			Responsable
   		</td>
   		<td class="tabla_centrado" style="width:60px;">
   			Editar item
   		</td>
   		<td class="tabla_centrado" style="width:70px;">
   			Cambiar estado
   		</td>
   		<td class="tabla_centrado" style="width:70px;">
   			Historial
   		</td>
   		<td class="tabla_centrado" style="width:60px;">
   			Eliminar item
   		</td>
   	</tr>
   	<tr>
   		<td colspan="9">
   			<hr>
   		</td>
   	</tr>
   	<c:forEach var="itemProyecto" items="${itemsProyecto}"  varStatus="i">

   		<c:choose>  
			<c:when test="${(i.count) % 2 == 0}">  
				<tr style="background-color:#eeeeee;">  
			</c:when>  
			<c:otherwise>  
				<tr>  
			</c:otherwise>  
		</c:choose> 
   		
   		
    		<td class="tabla_centrado">
    			<c:out value="${itemProyecto.getTipoItem().getDescripcion()}"/>
    		</td>
    		<td>
    			<c:out value="${itemProyecto.getDescripcion()}"/>
    		</td>
    		<td class="tabla_centrado">
				<c:out value="${itemProyecto.verEstadoActual()}"/>
    		</td>
    		<td class="tabla_centrado">
				<c:out value="${itemProyecto.getPrioridad()}"/>
    		</td>
    		<td class="tabla_centrado">
				<c:out value="${itemProyecto.responsableActual().getNombre()}"/>
    		</td>
    		<td class="tabla_centrado">
    			<a href="item_editar.jsp?idI=<c:out value="${itemProyecto.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/proyecto_editar.png" 
    					title="Editar <c:out value="${itemProyecto.getDescripcion()}"/>">
    			</a>
    		</td>
    		<td class="tabla_centrado">
    			<a href="item_cambiarEstado.jsp?idI=<c:out value="${itemProyecto.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/cambiar_estado.png" 
    					title="Cambiar estado de <c:out value="${itemProyecto.getDescripcion()}"/>">
    			</a>
    		</td>
    		<td class="tabla_centrado">
    			<a href="item_historial.jsp?idI=<c:out value="${itemProyecto.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/historial.png" 
    					title="Historial de <c:out value="${itemProyecto.getDescripcion()}"/>">
    			</a>
    		</td>
    		<td class="tabla_centrado">
    			<a href="item_eliminar.jsp?idI=<c:out value="${itemProyecto.getId()}"/>&idP=<c:out value="${proyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/borrar.png" 
    					title="Eliminar <c:out value="${itemProyecto.getDescripcion()}"/>">
    			</a>
    		</td>
   		</tr>
   	</c:forEach>
   	<tr>
   		<td colspan="9">
   			<hr>
   		</td>
   	</tr>
   	<tr>
   		<td colspan="7">
   			<table cellpadding="0" cellspacing="0" border="0">
   				<tr>
	   				<td class="tabla_centrado" colspan="5" style="font-style:italic;">
	   					Agregar nuevo item&nbsp;&nbsp;
	   				</td>
	   				<td class="tabla_centrado" colspan="2" style="font-style:italic;">
	   					<a href="item_agregar.jsp"> 
	    					<img class="icono_chico" src="iconos/agregar_item.png" 
	    						title="Agregar nuevo item al proyecto">
	    				</a>
	   				</td>
	   			</tr>
   			</table>
   		</td>
   	</tr>
</table>
