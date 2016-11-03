
$(document).ready(function() {
	
	//Llena un Select con la informacion de los servicios ofrecidos
	$("#id_clas").ready(function() {
				
		$.ajax({
			url: "Servicios",
			type: "POST",
			data: "opc=consultaServicios"
		})
		.done(function( msg ) { //Se ejecuta al terminar la funcion
			document.getElementById('id_clas').innerHTML = msg; 
		})
		.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			document.getElementById('id_clas').innerHTML = 
				"<option value=''>Seleccione</option>";
		});
		
	});
	
	//Llena un Select con los clientes actuales
	$("#num_reg").ready(function() {
		
		$.ajax({
			url: "Clientes",
			type: "POST",
			data: "opc=consultaRegistros"
		})
		.done(function( msg ) { //Se ejecuta al terminar la funcion
			document.getElementById('num_reg').innerHTML = msg; 
		})
		.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			document.getElementById('num_reg').innerHTML = 
				"<option value=''>Seleccione</option>";
		});
		
	});
	
});