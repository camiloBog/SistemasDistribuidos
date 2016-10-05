package Servidor;

/*
- Se inicia el servidor
- El servidor se mantiene escuchando cualquier petici�n de un cliente para conectarse.
- El servidor acepta al cliente.
- El servidor lanza un hilo de comunicaci�n con el cliente.
- Por el hilo se env�an y reciben mensajes a trav�s del servidor entre todos los clientes.
- Si el cliente cierra la comunicaci�n el hilo se rompe y se corta la comunicaci�n con ese cliente.
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

			System.out.println("Esperando una conexi�n:");

			//Espera una conexion
			so = sc.accept();
			
			
			///Abrir aqui el hilo para cada cliente
			com.start();
			
			
			
			System.out.println("Un cliente se ha conectado.");

			// Canales de entrada y salida de datos
			entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
			salida = new DataOutputStream(so.getOutputStream());

			System.out.println("Confirmando conexion al cliente....");

			salida.writeUTF("Conexi�n exitosa...n envia un mensaje al cliente :D");

			// Recepcion de mensaje
			mensajeRecibido = entrada.readLine();

			System.out.println(mensajeRecibido);

			salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");
			salida.writeUTF("Gracias por conectarte, adios!");
			
			System.out.println("Cerrando conexi�n...");

			sc.close();// Aqui se cierra la conexi�n con el cliente

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
