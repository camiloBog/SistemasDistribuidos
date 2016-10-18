package co.ejercicios.e32;

/*
 * Write a simple crawler program that can parse certain 
 * HTML information and find out the hyperlinks within 
 * the HTML. (Use the URLConnection class to implement 
 * this application).
 * 
 * Escribir un programa rastreador simple que puede analizar 
 * ciertos datos HTML y descubrir los hipervínculos dentro 
 * del HTML. (Utilice la clase URLConnection para 
 * implementar esta aplicación).
 * 
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Ejercicio32 {	

	public static void main(String[] args) {
		
		String consultaPagina = JOptionPane.showInputDialog("Ingrese la página a consultar");

		try {
			
			URL url = new URL(consultaPagina);
			URLConnection urlConnection = url.openConnection();
			
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String linea = null;
			
			while ((linea = br.readLine()) != null){
				encuentra(linea);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void encuentra(String linea) {

		if (linea.contains("href")) {
			String[] href = linea.split("href");
			for (int i = 1; i < href.length; i++) {
				StringTokenizer st = new StringTokenizer(href[i], "\"");
				st.nextElement();
				try {
					System.out.println(st.nextElement());
				} catch (Exception e) {
				}
			}
		}
	}

}