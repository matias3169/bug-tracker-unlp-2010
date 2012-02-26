<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.id)}"/>
	<c:set var="tiposItem" value="${proyecto.getTiposItems()}"/>
	<c:set var="miembrosProyecto" value="${proyecto.getMiembros()}"/>
	
	<script type="text/javascript">
		function cargarCombo()
		{
			var xmlhttp;
			var valorTipoItem = document.getElementById("comboindependiente").value;
			var valorProyecto = document.getElementById("proy").value;
		    var fragment_url = 'combodependiente.jsp?TipoItemId='+valorTipoItem+'&Proyecto='+valorProyecto;
		    
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
	<html:javascript formName="agregarItemForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar item a <c:out value="${proyecto.getNombre()}"/></h2>
    
    <html:form method="POST" action="/agregar_item">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    <input type=hidden id="proy" name="proy" value="${proyecto.getNombre()}"/>
    
    <table>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_nombre">Nombre: </div>
				</td>
				<td class="tabla_input">
					<input type="text" name="nombre_item">
				</td>
			</tr>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_descripcion">Descripcion: </div>
				</td>
				<td class="tabla_input">
					<input type="text" name="descripcion_item">
				</td>
			</tr>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_tipo">Tipo de item</div>
				</td>
				<td class="tabla_input">
					<select id="comboindependiente" name="tipo_item" onchange="javascript:cargarCombo()">
						<option value="null">&nbsp;</option>
	    					<c:forEach var="tipo" items="${tiposItem}">
	    						<c:choose>
									<c:when test="${tipo.getEstadoInicial() != null}">
		    		   					<option><c:out value="${tipo.getDescripcion()}"/></option>
									</c:when> 
								</c:choose>
			         		</c:forEach>
	    			</select>		
				</td>
			</tr>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_prioridad">Prioridad</div>
				</td>
				<td class="tabla_input">
					<input type="text" name="prioridad_item">
				</td>
			</tr>
			<tr>	
				<td class="tabla_etiqueta">
					<div id="etiqueta_responsable">Responsable</div>
				</td>
				
				<td class="tabla_input">
					<div id="div_combodependiente">
						<select name="responsable_item" id="responsable_item"></select>	
	    			</div>	
				</td>
			</tr>
			
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregar_item" value="Agregar item">
				</td>
			</tr>
		</table>
	
	</html:form>
	<%@ include file="footer.jsp" %>
</body>
</html:html>