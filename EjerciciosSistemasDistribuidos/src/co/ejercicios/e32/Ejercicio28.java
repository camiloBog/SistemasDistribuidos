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

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Ejercicio28 {

	public static void main(String[] args) {
		
		//String consultaPagina = JOptionPane.showInputDialog("Ingrese la página a consultar");
		String consultaPagina = "http://www.buyya.com/welcome/biography.html";
		
		try {
			
			URL url = new URL(consultaPagina);
			URLConnection urlConnection = url.openConnection();
			
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String linea = null;
			String pagina = "";
			
			while ((linea = br.readLine()) != null){
				pagina += linea + "\n";
				encuentra(linea);
			}
				
			
			System.out.println(pagina);
			
			JOptionPane.showMessageDialog(null, pagina);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void encuentra(String linea) {
		
		
		
		
	}
	
	

}