<table cellpadding="0" cellspacing="0" border="0">
   	<tr class="encabezado_tabla">
   		<td class="tabla_centrado" style="width:350px;">
   			Descripci&oacute;n
   		</td>
   		<td class="tabla_centrado" style="width:300px;">
   			Estado inicial
   		</td>
   		<td class="tabla_centrado">
   			Estados posibles
   		</td>
   	</tr>
   	<tr>
   		<td colspan="3">
   			<hr>
   		</td>
   	</tr>
   	<c:forEach var="tipoItem" items="${tiposItem}"  varStatus="k">

   		<c:choose>  
			<c:when test="${(k.count) % 2 == 0}">  
				<tr style="background-color:#eeeeee;">  
			</c:when>  
			<c:otherwise>  
				<tr>  
			</c:otherwise>  
		</c:choose> 
   		
   		
    		<td class="tabla_centrado">
    			<c:out value="${tipoItem.getDescripcion()}"/>
    		</td>
    		<td class="tabla_centrado">
    			<c:out value="${tipoItem.getEstadoInicial().getDescripcion()}"/>
    		</td>
    		<td class="tabla_centrado">
				<c:set var="estadosPosibles" value="${tipoItem.getEstadosPosibles()}" />
				<select>
					<c:forEach var="estadoPosible" items="${estadosPosibles}"  varStatus="m">
						<option>
							<c:out value="${estadoPosible.getDescripcion()}"/>
						</option>
					</c:forEach>
				</select>
    		</td>
   		</tr>
   	</c:forEach>
   	<tr>
   		<td colspan="3">
   			<hr>
   		</td>
   	</tr>
   	<tr>
   		<td colspan="3">
   			<table cellpadding="0" cellspacing="0" border="0">
   				<tr>
	   				<td class="tabla_centrado" colspan="5" style="font-style:italic;">
	   					Agregar nuevo tipo de item&nbsp;&nbsp;
	   				</td>
	   				<td class="tabla_centrado" colspan="2" style="font-style:italic;">
	   					<a href="tipo_item_agregar.jsp"> 
	    					<img class="icono_chico" src="iconos/agregar_item.png" 
	    						title="Agregar nuevo tipo de item al proyecto">
	    				</a>
	   				</td>
	   			</tr>
   			</table>
   		</td>
   	</tr>
</table>
