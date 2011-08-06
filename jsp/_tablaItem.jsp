<table cellpadding="0" cellspacing="0" border="0">
   	<tr class="encabezado_tabla">
   		<td class="tabla_centrado" style="width:200px;">
   			Tipo
   		</td>
   		<td class="tabla_centrado">
   			Descripci&oacute;n
   		</td>
   		<td class="tabla_centrado">
   			Estado actual
   		</td>
   		<td class="tabla_centrado" style="width:80px;">
   			Prioridad
   		</td>
   		<td class="tabla_centrado" style="width:80px;">
   			Responsable
   		</td>
   		<td class="tabla_centrado" style="width:80px;">
   			Cambiar estado
   		</td>
   		<td class="tabla_centrado" style="width:80px;">
   			Historial
   		</td>
   	</tr>
   	<tr>
   		<td colspan="7">
   			<hr>
   		</td>
   	</tr>
   	<% int i = 0; %>
   	<c:forEach var="itemProyecto" items="${itemsProyecto}">
   	<%	i = i + 1; %>
   	<tr <%= i%2==0?"style=\"background-color:#eeeeee;\"":""%>>
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
    			<a href="item_cambiarEstado.jsp?id=<c:out value="${itemProyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/cambiar_estado.png" 
    					title="Cambiar estado de <c:out value="${itemProyecto.getDescripcion()}"/>">
    			</a>
    		</td>
    		<td class="tabla_centrado">
    			<a href="item_historial.jsp?id=<c:out value="${itemProyecto.getId()}"/>"> 
    				<img class="icono_chico" src="iconos/historial.png" 
    					title="Historial de <c:out value="${itemProyecto.getDescripcion()}"/>">
    			</a>
    		</td>
   		</tr>
   	</c:forEach>
   	
</table>