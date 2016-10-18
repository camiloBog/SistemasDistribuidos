package co.ejercicios.e31;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * Write an online dictionary application, users are able 
 * to search online dictionary and get the meaning of the words. 
 * The online dictionary should maintain its indexing in order 
 * to enhance the performance and scalability.
 * 
 * Escriba una aplicacion de diccionario en linea, los
 * usuarios podran buscar en linea y obtener el significado
 * de las palabras. 
 * 
 */

public class Servidor {
	
	static final int PUERTO = 6000;
	private String url = "./src/co/ejercicios/e31/diccionario.txt";
	private DataInputStream entradaDatos;
	private DataOutputStream salidaDatos;
	private ServerSocket skServidor;
	private Map <String,String> mp = new HashMap<String,String>();
	
	public Servidor() {

		// Cargar el diccionario
		leerArchivo();

		try {
			skServidor = new ServerSocket(PUERTO);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		System.out.println("SERVIDOR, Escucho el puerto " + PUERTO);

		while (true) {

			Socket skCliente = null;
			
			try {
				skCliente = skServidor.accept();
				entradaDatos = new DataInputStream(skCliente.getInputStream());
				salidaDatos = new DataOutputStream(skCliente.getOutputStream());

				String palabra = entradaDatos.readUTF();

				String significado;
				try {
					significado = mp.get(palabra.toLowerCase());
					salidaDatos.writeUTF(significado);
				} catch (Exception e) {
					salidaDatos.writeUTF("No se encontro esta palabra en el diccionario.");
				}
			} catch (IOException e) {
				try {
					skCliente.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
	
	
	private void leerArchivo() {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {

			archivo = new File(url);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;

			while ((linea = br.readLine()) != null){
				
				StringTokenizer st = new StringTokenizer(linea, "=");
				String palabra = st.nextToken();
				String significado = st.nextToken();
				
				mp.put(palabra.toLowerCase(), significado);
				
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}


	public static void main(String[] arg) {
		new Servidor();
	}

}
