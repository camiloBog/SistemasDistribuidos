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
<body id="bodyRegClientes">
	<h3>REGISTRO DE CLIENTES</h3>

	<table>
		<tr>
			<td>Registro #</td>
			<td><input type="text" id="numReg"></td>
		</tr>
		
		<tr>
			<td>Nombre</td>
			<td><input type="text" id="nombre"></td>
			<td>Apellido</td>
			<td><input type="text" id="apellido"></td>
		</tr>
		
		<tr>
			<td>cedula</td>
			<td><input type="text" id="cedula"></td>
			<td>Acompañantes</td>
			<td><input type="text" id="acomp"></td>
		</tr>
		
		<tr>
			<td>Fecha Ingreso</td>
			<td><input type="text" id="fingreso"></td>
			<td>Fecha Salida</td>
			<td><input type="text" id="fsalida"></td>
		</tr>

	</table>
	
	<input type="button" value="Registrar" id="registraCliente">
	<input type="button" value="Consultar" id="consultaRegistro">
	<input type="button" value="Limpiar Pantalla" id="limpiarPantalla">
	<br><br>
	<div id="respuesta"></div>
</body>
</html>