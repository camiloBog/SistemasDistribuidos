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
<body id="bodyRegConsumos" >
	<h3>REGISTRO DE CONSUMOS</h3>

	<table>
		<tr>
			<td>Consumo #</td>
			<td><input type="text" id="cod_serv"></td>
			<td>#Reg. Cliente</td>
			<td><select id="num_reg"></select></td>
		</tr>
		
		<tr>
			<td>Cod. Servicio</td>			
			<td><select id="id_clas"></select></td>
			<td>Servicio</td>
			<td><input type="text" id="nom_serv"></td>
		</tr>
		
		<tr>
			<td>Unidad Medida</td>
			<td><input type="text" id="unidad_medida"></td>
			<td>Valor Unidad</td>
			<td><input type="text" id="val_serv"></td>
		</tr>

	</table>
	
	<input type="button" value="Registrar" id="registraConsumo">
	<input type="button" value="Consultar" id="consultaConsumo">
	<input type="button" value="Limpiar Pantalla" id="limpiarPantallaConsumo">
	<br><br>
	<div id="respuesta"></div>
</body>
</html>