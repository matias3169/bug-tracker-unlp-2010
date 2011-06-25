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
   	<%
   	/* ----- codigo de prueba */
   	
   	String[] tiposTest={"Bug","Ampliación","Mejora","Nuevo requerimiento","I18n"};
   	String[] estadosTest={"Creado","Desarrollo","Validación","Terminado"};
   	String[] responsablesTest={"Hugo","Paco","Luis"};
   	java.util.Random azar = new java.util.Random();
   	
   	for(int i=1;i<=10;i++)
   	{
   		%><tr <%= i%2==0?"style=\"background-color:#eeeeee;\"":""%>>
    		<td class="tabla_centrado">
    			<%= tiposTest[azar.nextInt(5)] %>
    		</td>
    		<td>
    			Alguna descripcion <%= i %>
    		</td>
    		<td class="tabla_centrado">
    			<%= estadosTest[azar.nextInt(4)] %>
    		</td>
    		<td class="tabla_centrado">
    			<%= azar.nextInt(10)+1 %>
    		</td>
    		<td class="tabla_centrado">
    			<%= responsablesTest[azar.nextInt(3)] %>
    		</td>
    		<td class="tabla_centrado">
    			<a href="item_cambiarEstado.jsp?id=<%= i %>"> 
    				<img class="icono_chico" src="iconos/cambiar_estado.png" 
    					title="Trabajar en _proyectoNombre<%= i %>_">
    			</a>
    		</td>
    		<td class="tabla_centrado">
    			<a href="item_historial.jsp?id=<%= i %>"> 
    				<img class="icono_chico" src="iconos/historial.png" 
    					title="Trabajar en _proyectoNombre<%= i %>_">
    			</a>
    		</td>
   		</tr><% 
   	}
   		
   	%>
   	
</table>