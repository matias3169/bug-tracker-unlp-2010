<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="html_head.jsp" %>
<body>
	<h1><%= BTUNLP_Titulo %></h1>
    <h2>Agregar nuevo usuario</h2>
	<form method="POST" action="usuario_agregar.jsp">
		<table>
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_usuario">Usuario:</div>
				</td>
				<td class="tabla_input">
					<input type="text" name="nombre_usuario">
				</td>
			</tr>
			
			<!-- Por si hace falta agregar contraseña al usuario 
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_clave">Contrase&ntilde;a:</div>
				</td>
				<td class="tabla_input">
					<input type="password" name="clave_usuario">
				</td>
			</tr>
			 -->
			<tr>
				<td class="tabla_etiqueta">
					<div id="etiqueta_clave">Rol:</div>
				</td>
				<td class="tabla_input">
					<%
   					/* ----- codigo de prueba */
   				   	String[] rolesTest={"Administrador", "Líder de proyecto", "Programador", "Tester", "Analista"};
					%>
					<select name="rol_usuario">
						<option value="null">&nbsp;</option>
						<%
						 for (String s : rolesTest) 
						 {
							%><option value="<%= s %>">
								<%= s %>
							</option><%
						 }
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tabla_centrado" colspan="2">
					<input type="submit" name="agregar_usuario" value="Agregar usuario">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="footer.jsp" %>
</body>
</html>