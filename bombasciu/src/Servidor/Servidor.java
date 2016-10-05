package Servidor;

/*
- Se inicia el servidor
- El servidor se mantiene escuchando cualquier petición de un cliente para conectarse.
- El servidor acepta al cliente.
- El servidor lanza un hilo de comunicación con el cliente.
- Por el hilo se envían y reciben mensajes a través del servidor entre todos los clientes.
- Si el cliente cierra la comunicación el hilo se rompe y se corta la comunicación con ese cliente.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import util.Comunicacion;

public class Servidor {

	final int PUERTO = 5000;
	ServerSocket sc;
	Socket so;
	DataOutputStream salida;
	String mensajeRecibido;
	
	Comunicacion com;

	public Servidor() {
		
		BufferedReader entrada;

		try {

			sc = new ServerSocket(PUERTO);
			so = new Socket();

			System.out.println("Esperando una conexión:");

			//Espera una conexion
			so = sc.accept();
			
			
			///Abrir aqui el hilo para cada cliente
			com.start();
			
			
			
			System.out.println("Un cliente se ha conectado.");

			// Canales de entrada y salida de datos
			entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
			salida = new DataOutputStream(so.getOutputStream());

			System.out.println("Confirmando conexion al cliente....");

			salida.writeUTF("Conexión exitosa...n envia un mensaje al cliente :D");

			// Recepcion de mensaje
			mensajeRecibido = entrada.readLine();

			System.out.println(mensajeRecibido);

			salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");
			salida.writeUTF("Gracias por conectarte, adios!");
			
			System.out.println("Cerrando conexión...");

			sc.close();// Aqui se cierra la conexión con el cliente

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
