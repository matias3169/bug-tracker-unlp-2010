<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
..:: Bug Tracker BD UNLP 2010 ::..
</title>
<link rel="stylesheet" href="estilos.css" type="text/css" media="screen" />
</head>
<body>
		<h1>
			Bug Tracker BD UNLP 2010 - Bienvenido <bean:write name="user" property="nombre"/>
		</h1>
		<form method="POST" action="">
			<table>
				<tr>
					<td class="acciones_usuario">
						Usuarios:
					</td>
					<td class="tabla_centrado">
						<img alt="Listar usuarios" src="iconos/listado.png">
					</td>
					<td class="tabla_centrado">
						<img alt="Agregar un usuario" src="iconos/nuevo.png">
					</td>
					<td class="tabla_centrado">
						<img alt="Modificar un usuario" src="iconos/editar.png">
					</td>
					<td class="tabla_centrado">
						<img alt="Borrar un usuario" src="iconos/borrar.png">
					</td>
				</tr>
				
				<tr>
					<td class="acciones_usuario">
						Proyectos
					</td>
					<td class="tabla_centrado">
						<img alt="Listar usuarios" src="iconos/listado.png">
					</td>
					<td class="tabla_centrado">
						<img alt="Agregar un usuario" src="iconos/nuevo.png">
					</td>
					<td class="tabla_centrado">
						<img alt="Modificar un usuario" src="iconos/editar.png">
					</td>
					<td class="tabla_centrado">
						<img alt="Borrar un usuario" src="iconos/borrar.png">
					</td>
				</tr>
				
				<!--
				<tr>
					<td class="tabla_centrado" colspan="2">
						<input type="button" name="boton_login" value="Cerrar sesi&oacute;n" onClick="enviarLogout()">
					</td>
				</tr>
				-->
			</table>
		</form>
		<br><br><br>
		<table>
				<tr>
					<td class="footer">
						Base de datos - M.I.S. - UNLP 2010 - Anti&ntilde;anco; Becker; Fazzini
					</td>
				</tr>
				
			</table>
</body>
</html>