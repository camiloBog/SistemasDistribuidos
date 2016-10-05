package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {

	final String HOST = "localhost";
	final int PUERTO = 5000;

	Socket sc;
	DataOutputStream mensaje;
	DataInputStream entrada;

	// Cliente
	public Cliente() {

		try {

			//Se conecta al servidor, en el puerto indicado
			sc = new Socket(HOST, PUERTO); 

			// creamos el flujo de datos por el que se enviara un mensaje
			mensaje = new DataOutputStream(sc.getOutputStream());

			// enviamos el mensaje
			mensaje.writeUTF("hola que tal, desde el cliente!!");

			// Se cierra la conexión
			sc.close();

		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}

	}

}
