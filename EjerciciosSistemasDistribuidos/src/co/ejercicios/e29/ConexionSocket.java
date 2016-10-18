package co.ejercicios.e29;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConexionSocket extends Thread {

	private Socket socket;
	private DataInputStream entradaDatos;
	private DataOutputStream salidaDatos;
	
	private boolean conectado = true;
	private boolean hablar = true;
	
	private String rol;

	public ConexionSocket(Socket socket, String rol, boolean hablar) {

		this.socket = socket;
		this.hablar = hablar;
		this.rol = rol;

		try {
			entradaDatos = new DataInputStream(socket.getInputStream());
			salidaDatos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		while (conectado) {

			try {

				if (hablar) { // Enviar Mensaje

					salidaDatos.writeUTF( leerTeclado() );
					hablar = false;
					
				} else { // Escuchar
					
					System.out.println("Esperando respuesta...");
					System.out.println( "Msg Recibido: " + entradaDatos.readUTF() );
					hablar = true;
				}

				
			} catch (IOException e) {
				
				conectado = false;
				
				try {
					entradaDatos.close();
					salidaDatos.close();
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				System.out.println("Termino la comunicacion.");
			}

		}

	}
	
	private String leerTeclado(){
		
        System.out.println (rol + ", que mensaje vas a enviar?");
        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); 
        entradaTeclado = entradaEscaner.nextLine ();

        return entradaTeclado;
	}

}
