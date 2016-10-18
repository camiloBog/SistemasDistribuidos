package co.ejercicios.e33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Write a Socket-based Java server program that responds 
 * to client messages as follows: When it receives a message 
 * from a client, it simply converts the message into all 
 * uppercase letters and sends back the same to the client. 
 * Write both client and server programs demonstrating this.
 * 
 * Escriba un servidor que responda a los mensajes del
 * cliente con el mismo mensaje recibido pero convertido
 * a mayusculas
 * 
 */

public class Servidor {
	
	static final int PUERTO = 6000;
	private DataInputStream entradaDatos;
	private DataOutputStream salidaDatos;
	private ServerSocket skServidor;
	
	public Servidor() {

		try {

			skServidor = new ServerSocket(PUERTO);
			System.out.println("SERVIDOR, Escucho el puerto " + PUERTO);

			Socket skCliente = skServidor.accept();
			entradaDatos = new DataInputStream(skCliente.getInputStream());
			salidaDatos = new DataOutputStream(skCliente.getOutputStream());

			while(true){
				String mensaje = entradaDatos.readUTF();
				salidaDatos.writeUTF( mensaje.toUpperCase() );	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				skServidor.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void main(String[] arg) {
		new Servidor();
	}

}
