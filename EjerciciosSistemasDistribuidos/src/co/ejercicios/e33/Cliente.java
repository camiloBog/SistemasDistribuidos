package co.ejercicios.e33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;


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

public class Cliente {
	
	static final String HOST = "localhost";
	static final int Puerto = 6000;
	private DataInputStream entradaDatos;
	private DataOutputStream salidaDatos;
	private Socket skCliente;

	public Cliente() {
		
		try {
			
			System.out.println("Programa cliente.");
			
			skCliente = new Socket(HOST, Puerto);

			entradaDatos = new DataInputStream(skCliente.getInputStream());
			salidaDatos = new DataOutputStream(skCliente.getOutputStream());

			String mensaje = "Ingrese un mensaje...";
			
			while(true){
				
				mensaje = JOptionPane.showInputDialog(mensaje);
				
				salidaDatos.writeUTF( mensaje );	
				
				mensaje = "Mensaje recibido: ";
				mensaje += entradaDatos.readUTF();
				mensaje += "\nIngrese un nuevo mensaje...";
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				skCliente.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		new Cliente();
	}

}
