<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
	<%@ include file="html_head.jsp" %>
	<c:set var="proyecto" value="${sessionScope.sistema.getProyecto(param.id)}"/>
	
<body>
	<html:javascript formName="agregarTipoItemForm" />
	
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar tipo de item a <c:out value="${proyecto.getNombre()}"/></h2>
    <h3><font color="red"><html:errors/></font></h3>
    <html:form method="POST" action="/agregar_tipoItem"  onsubmit="return validate(this);">
    
    <html:hidden property="nombreProyecto" value="${proyecto.getNombre()}" />
    
     <table>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_nombre">Descripcion: </div>
				</td>
				<td class="tabla_input">
					<input type="text" name="descripcion_tipoItem">
				</td>
			</tr>
			
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregar_tipoItem" value="Agregar tipo de item">
				</td>
			</tr>
		</table>

	</html:form>
	<%@ include file="footer.jsp" %>
</body>
</html:html>