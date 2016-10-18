package co.ejercicios.e32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class prueba2 {

	public static void main(String[] args) {

		String linea = "<a href=\"http://www.wiley.com/WileyCDA/WileyTitle/productCd-0470887990.html\"><img SRC=\"Cloud-Book.jpg\" border=0 height=150></a>";

		//
		// if ( linea.contains("href") ) {
		//
		// StringTokenizer st = new StringTokenizer(linea, "href=");
		//
		// System.out.println("Token: "+st.nextToken());
		// System.out.println("Token: "+st.nextToken());
		// System.out.println("Token: "+st.nextToken());
		// System.out.println("Token: "+st.nextToken());
		//
		//
		// System.out.println(linea);
		// }
		//

		try {
			String nombreurl = "http://www.buyya.com/welcome/biography.html";
			URL url = new URL(nombreurl);
			URLConnection uc = url.openConnection();
			uc.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

			StringBuilder builder = new StringBuilder();
			String aux = "";

			while ((aux = in.readLine()) != null) {
				builder.append(aux);
			}

			String text = "";
			String h1Mostrar = "";

			text = builder.toString();
			StringBuffer sText = new StringBuffer(text);

			int h1Cuantos = 0;
			int h1Donde = 1;

			while (sText.indexOf("<a href=") != -1 || h1Cuantos < 2) {
				
				h1Donde = sText.indexOf("<a href=");
				
				sText = new StringBuffer(sText.substring(h1Donde + 1));
				
				System.out.println(sText.substring(h1Donde + 1));
				
				h1Cuantos++;
			}

			switch (h1Cuantos) {
			case 0:
				h1Mostrar = "Incorrecto: No existen elementos <a href=";
				break;
			case 1:
				h1Mostrar = "Correcto: Existe un elemento <a href=";
				break;
			default:
				h1Mostrar = "Incorrecto: Existen más de un elemento <a href=";
				break;
			}
			
			System.out.println(h1Cuantos);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
