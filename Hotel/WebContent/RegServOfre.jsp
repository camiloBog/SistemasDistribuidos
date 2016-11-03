<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
	<script type="text/javascript" src="js/js.js"></script>
 
    <link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Servicio Registro Hotel</title>
</head>
<body id="bodyRegServicios">
	<h3>SERVICIOS OFRECIDOS</h3>

	<table>
		<tr>
			<td>#Servicio</td>
			<td><input type="text" id="numReg"></td>
		</tr>
		<tr>	
			<td>Nombre</td>
			<td><input type="text" id="nomServ"></td>
		</tr>
	</table>
	
	<input type="button" value="Registrar" id="registraServicio">
	<input type="button" value="Consultar" id="consultaServicio">
	<input type="button" value="Limpiar Pantalla" id="limpiarPantallaServicio">
	<br><br>
	<div id="respuesta"></div>
</body>
</html>