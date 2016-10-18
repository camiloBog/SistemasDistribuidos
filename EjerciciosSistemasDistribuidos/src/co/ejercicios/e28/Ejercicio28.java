package co.ejercicios.e28;

/*
 * Write a URL-based program that pulls content from www.buyya.com.
 * 
 * Escriba un programa basado en URL que muestre contenido
 * de una pagina web
 * 
 */

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Ejercicio28 {

	public static void main(String[] args) {
		
		String consultaPagina = JOptionPane.showInputDialog("Ingrese la página a consultar");
		
		try {
			
			URL url = new URL(consultaPagina);
			URLConnection urlConnection = url.openConnection();
			
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String linea = null;
			String pagina = "";
			
			while ((linea = br.readLine()) != null)
				pagina += linea + "\n";
			
			JOptionPane.showMessageDialog(null, pagina);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}