
$(document).ready(function() {
	
	//Campos de Fecha
	$(function() {
	    $( "#fingreso" ).datepicker();
	    $( "#fsalida" ).datepicker();
	});
	
	//Envia desde el Home a los diferentes formularios
	$("#BtnRegCliente").click(function() {
		window.parent.document.getElementById('marco').src = "RegCliente.jsp";	
	});
	
	$("#BtnRegServOfr").click(function() {
		window.parent.document.getElementById('marco').src = "RegServOfre.jsp";	
	});
	
	$("#BtnRegServCon").click(function() {
		window.parent.document.getElementById('marco').src = "RegServConsu.jsp";	
	});
	
	/*
	 * Registra un cliente en la BD
	 */
	$("#registraCliente").click(function() {
		
		document.getElementById('respuesta').innerHTML = "";
		
		var par = 'opc=registra'+
			'&cedula='+document.getElementById('cedula').value+
			'&nombre='+document.getElementById('nombre').value+
			'&apellido='+document.getElementById('apellido').value+
			'&acomp='+document.getElementById('acomp').value+
			'&fingreso='+document.getElementById('fingreso').value+
			'&fsalida='+document.getElementById('fsalida').value;
		
		$.ajax({
			url: "Clientes",
			type: "POST",
			data: par
		})
		.done(function( msg ) { //Se ejecuta al terminar la funcion
			//Se inserta la respuesta en este componente
			document.getElementById('numReg').value = msg;
			alert("REGISTRO EXITOSO!  \n Registro #: "+msg);
		})
		.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
		});
	});
	
	/*
	 * Consulta los clientes en la BD
	 */
	$("#consultaRegistro").click(function() {
		
		document.getElementById('respuesta').innerHTML = "";

		var par = 'opc=consulta'+
			'&numReg='+document.getElementById('numReg').value+
			'&cedula='+document.getElementById('cedula').value+
			'&nombre='+document.getElementById('nombre').value+
			'&apellido='+document.getElementById('apellido').value+
			'&acomp='+document.getElementById('acomp').value+
			'&fingreso='+document.getElementById('fingreso').value+
			'&fsalida='+document.getElementById('fsalida').value;
		
		$.ajax({
			url: "Clientes",
			type: "POST",
			data: par
		})
		.done(function( msg ) { //Se ejecuta al terminar la funcion
			//Se inserta la respuesta en este componente
			document.getElementById('respuesta').innerHTML = msg;
		})
		.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
		});
	});
	
	/*
	 * Registra en la BD los servicios ofrecidos
	 */
	$("#registraServicio").click(function() {
				
		document.getElementById('respuesta').innerHTML = "";

		var par = 'opc=registraServicio'+
			'&nomServ='+document.getElementById('nomServ').value;
		
		var nom = document.getElementById('nomServ').value;
		if(nom=="" || nom==null) par = "";
		
		$.ajax({
			url: "Servicios",
			type: "POST",
			data: par
		})
		.done(function( msg ) { //Se ejecuta al terminar la funcion
			//Se inserta la respuesta en este componente
			
			document.getElementById('numReg').value = msg;
			alert("REGISTRO EXITOSO!  \n Registro #: "+msg);
			
		})
		.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
		});
	});
	
	/*
	 * Consulta en la BD los servicios ofrecidos
	 */
	$("#consultaServicio").click(function() {
		
		document.getElementById('respuesta').innerHTML = "";
		
		var par = 'opc=consultaServicio'+
		'&numReg='+document.getElementById('numReg').value+
		'&nomServ='+document.getElementById('nomServ').value;
		
		$.ajax({
			url: "Servicios",
			type: "POST",
			data: par
		})
		.done(function( msg ) { //Se ejecuta al terminar la funcion
			//Se inserta la respuesta en este componente
			document.getElementById('respuesta').innerHTML = msg;
		})
		.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
		});
	});
	
	/*
	 * Registra en la BD un consumo realizado  (FALTA ARREGLAR)
	 */
	$("#registraConsumo").click(function() {
		
		document.getElementById('respuesta').innerHTML = "";
		
		var par = 'opc=registraConsumo'
			+'&num_reg='+document.getElementById('num_reg').value
			+'&id_clas='+document.getElementById('id_clas').value
			+'&nom_serv='+document.getElementById('nom_serv').value
			+'&unidad_medida='+document.getElementById('unidad_medida').value
			+'&val_serv='+document.getElementById('val_serv').value;
		
		$.ajax({
			url: "Servicios",
			type: "POST",
			data: par,
		}).done(function( msg ) { //Se ejecuta al terminar la funcion
			//Se inserta la respuesta en este componente
			document.getElementById('cod_serv').value = msg;
			alert("REGISTRO EXITOSO!  \n Registro #: "+msg);
			
		}).fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
		});
	});
	
	
	/*
	 * Registra en la BD un consumo realizado (FALTA ARREGLAR)
	 */
	$("#consultaConsumo").click(function() {
		
		document.getElementById('respuesta').innerHTML = "";
		
		var par = 'opc=consultaConsumo'
			+'&cod_serv='+document.getElementById('cod_serv').value
			+'&num_reg='+document.getElementById('num_reg').value
			+'&id_clas='+document.getElementById('id_clas').value
			+'&nom_serv='+document.getElementById('nom_serv').value
			+'&unidad_medida='+document.getElementById('unidad_medida').value
			+'&val_serv='+document.getElementById('val_serv').value;
		
		$.ajax({
			url: "Servicios",
			type: "POST",
			data: par,
		}).done(function( msg ) { //Se ejecuta al terminar la funcion
			//Se inserta la respuesta en este componente
			document.getElementById('respuesta').innerHTML = msg;
		}).fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
		});
	});
	
	/*
	 * Limpia los formularios
	 */
	$("#limpiarPantallaServicio").click(function() {
		document.getElementById('respuesta').innerHTML = "";
		document.getElementById('numReg').value = "";
		document.getElementById('nomServ').value = "";
	});
	
	$("#limpiarPantallaConsumo").click(function() {
		document.getElementById('respuesta').innerHTML = "";
		document.getElementById('cod_serv').value = "";
		document.getElementById('num_reg').value = "";
		document.getElementById('id_clas').value = "";
		document.getElementById('nom_serv').value = "";
		document.getElementById('unidad_medida').value = "";
		document.getElementById('val_serv').value = "";
	});
	
	$("#limpiarPantalla").click(function() {
		document.getElementById('respuesta').innerHTML = "";
		document.getElementById('numReg').value = "";
		document.getElementById('nombre').value = "";
		document.getElementById('apellido').value = "";
		document.getElementById('cedula').value = "";
		document.getElementById('acomp').value = "";
		document.getElementById('fingreso').value = "";
		document.getElementById('fsalida').value = "";
	});
	
});

function modificar(num) {
	
	var nom = document.getElementById('mod'+num).value;
	
	if(nom=='Modificar'){
		document.getElementById('mod'+num).value='Guardar';
		
		document.getElementById('des'+num).disabled='';
		document.getElementById('val'+num).disabled='';
	}
	
	if(nom=='Guardar'){ 
		
		var par = 'opc=modificaServicio'
			+'&cod_serv='+document.getElementById('reg'+num).value
			+'&nom_serv='+document.getElementById('des'+num).value
			+'&val_serv='+document.getElementById('val'+num).value;

		$.ajax({
			url: "Servicios",
			type: "POST",
			data: par
		})
		.done(function( msg ) { //Se ejecuta al terminar la funcion
			//Se inserta la respuesta en este componente
			
			if(msg=='OK'){
				
				document.getElementById('mod'+num).value='Modificar';
				
				document.getElementById('des'+num).disabled='disabled';
				document.getElementById('val'+num).disabled='disabled';
					
				alert("Se ha Modificado Exitosamente el Registro!");
			}else alert("NO FUE POSIBLE LA TRANSACCION !!!");
		})
		.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
			alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
		});
	}
	
}

function eliminar(num) {
	
	var par = 'opc=eliminaServicio'
	+'&cod_serv='+document.getElementById('reg'+num).value;

	$.ajax({
		url: "Servicios",
		type: "POST",
		data: par
	})
	.done(function( msg ) { //Se ejecuta al terminar la funcion
		//Se inserta la respuesta en este componente
		
		if(msg=='OK'){
			document.getElementById('reg'+num).type='hidden';
			document.getElementById('nom'+num).type='hidden';
			document.getElementById('ser'+num).type='hidden';
			document.getElementById('des'+num).type='hidden';
			document.getElementById('val'+num).type='hidden';
			document.getElementById('mod'+num).type='hidden';
			document.getElementById('eli'+num).type='hidden';
			alert("Se ha Borrado Exitosamente el Registro!");
		}else alert("NO FUE POSIBLE LA TRANSACCION !!!");
	})
	.fail(function(jqXHR, textStatus) { //Se ejecuta si hay error
		alert(textStatus+"!!! NO FUE POSIBLE LA TRANSACCION !!!");
	});

}