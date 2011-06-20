<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<head>
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<title>
			..:: Bug Tracker BD UNLP 2010 ::..
		</title>
		<link rel="stylesheet" href="estilos.css" type="text/css" media="screen" />
	</head>
	
	<body>
	<html:javascript formName="loginForm" />
	<html:form action="login"  method="post" focus="login" onsubmit="return validateLoginForm(this);">
		<h1>
			Bug Tracker BD UNLP 2010
		</h1>
		<h2>
			Log in
		</h2>
		<table>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_usuario">Usuario: <html:text property="nombre"/><html:errors property="login" prefix="" suffix="" header="" footer=""/></div>
				</td>
			</tr>
			
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_clave">Contrase&ntilde;a: <html:text property="clave"/><html:errors property="login" prefix="" suffix="" header="" footer=""/></div>
				</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<html:submit value="Ingresar"/>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td class="footer">
					Base de datos - M.I.S. - UNLP 2010 - Anti&ntilde;anco; Becker; Fazzini
				</td>
			</tr>		
		</table>
	</html:form>
	<%-- En caso de que existan errores, los mostramos --%>
	   <logic:messagesPresent>
	   	<div class="errores">
			<html:errors/>
		</div>	        
	   </logic:messagesPresent>
	</body>
</html:html>