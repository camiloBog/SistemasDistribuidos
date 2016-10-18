package co.ejercicios.e29;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;


/*
 * Write a ping-pong client and server application. 
 * When a client sends a ping message to the server,
 * the server will respond with a pong message. 
 * Other messages sent by the client can be safely
 * dropped by the server.
 * 
 * Escriba un "ping-pong" cliente/servidor, cuando
 * el cliente envie un mensaje al servidor, el
 * servidor debera responder con un mensaje.
 * 
 */

public class Cliente {
	
	static final String HOST = "localhost";
	static final int Puerto = 6000;

	public Cliente() {
		
		try {
			
			System.out.println("Programa cliente.");
			
			Socket skCliente = new Socket(HOST, Puerto);
			ConexionSocket cn = new ConexionSocket(skCliente, this.getClass().getSimpleName(), false);
			cn.start();
			
		} catch (Exception e) {

		}
	}

	public static void main(String args[]) {
		new Cliente();
	}

}
