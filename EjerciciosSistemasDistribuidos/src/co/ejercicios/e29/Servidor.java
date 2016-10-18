package co.ejercicios.e29;

import java.net.ServerSocket;
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
 * servidor debera responder con un mensaje. O
 * 
 */

public class Servidor {
	static final int PUERTO = 6000;

	public Servidor() {
		try {
			
			System.out.println("SERVIDOR, Escucho el puerto " + PUERTO);

			ServerSocket skServidor = new ServerSocket(PUERTO);

			while(true){
				Socket skCliente = skServidor.accept();
				ConexionSocket cn = new ConexionSocket(skCliente, this.getClass().getSimpleName(), true);
				cn.start();
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		new Servidor();
	}

}
