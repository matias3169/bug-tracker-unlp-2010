<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<%@ include file="html_head.jsp" %>
	
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.idP)}"/>
	<c:set var="item" value="${sessionScope.sistema.getItemId(param.idI)}"/>

	<script type="text/javascript">
		function cargarCombo()
		{
			var xmlhttp;
			var valorTipoItem = document.getElementById("tipoI").value;
			var valorProyecto = document.getElementById("proy").value;
			var valorEstado = document.getElementById("comboindependiente").value;
			
		    var fragment_url = 'combodependiente.jsp?TipoItemId='+valorTipoItem+'&Proyecto='+valorProyecto+'&Estado='+valorEstado;
		    
			if (window.XMLHttpRequest)
			{// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			}
			else
			{// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlhttp.open("GET",fragment_url);
			xmlhttp.onreadystatechange=function()
			{
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    document.getElementById("div_combodependiente").innerHTML=xmlhttp.responseText;
			    }
			};
			
			xmlhttp.send(null);
		}
	</script>
	
<body>
	<html:javascript formName="cambiarEstadoForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
	<h2>Cambiar estado <c:out value="${item.getNombre()}"/></h2>
    
    <html:form action="/cambiarEstado"  method="post">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    <html:hidden property="nombreItem" value="${item.getNombre()}" />
    <html:hidden property="tipoItem" value="${item.getTipoItem()}"/>
    
	<input type=hidden id="proy" name="proy" value="${proyecto.getNombre()}"/>
	<input type=hidden id="tipoI" name="tipoI" value="${item.getTipoItem().getDescripcion()}"/>

    <table cellpadding="0" cellspacing="0" border="0">
    	<tr class="encabezado_tabla">
			<td class="tabla_centrado" style="width:100px;">
    			Descripcion
 		   </td>
 		   <td class="tabla_centrado" style="width:90px;">
    			Estado Actual
		   </td>
   		   <td class="tabla_centrado" style="width:90px;">
			    Pasar a estado 
   		   </td>
   		   <td class="tabla_centrado" style="width:90px;">
    			Responsable
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
    		    <c:out value="${item.getEstadoActual().getEstado().getDescripcion()}"/>
    		</td>
    		
    		<td class="tabla_centrado" style="width:90px;">
	    		<select id="comboindependiente" name="descNuevoEstado" onchange="javascript:cargarCombo()">
	    			<option value="null">&nbsp;</option>
						<c:forEach var="estado" items="${sessionScope.sistema.getEstadosSiguientes(item.getEstadoActual().getEstado())}">
							<option><c:out value="${estado.getDescripcion()}"/></option>
						</c:forEach>
				</select>
	    	</td>
	    	
	    	<td class="tabla_centrado" style="width:90px;">
	    		<div id="div_combodependiente">
					<select name="responsable_item" id="responsable_item"></select>
				</div>
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