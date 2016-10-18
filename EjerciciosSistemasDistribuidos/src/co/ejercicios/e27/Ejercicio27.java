package co.ejercicios.e27;

/*
 * Write a simple program that can read a host 
 * name and convert it to an IP address.
 * 
 * Escriba un programa simple que pueda leer un 
 * host name y convertirlo en una direccion IP
 * 
 */

import java.net.InetAddress;
import javax.swing.JOptionPane;

public class Ejercicio27 {
	public static void main(String[] args) {
		
		String hostName = JOptionPane.showInputDialog("Favor ingrese el host name");
		
		try {
			
			InetAddress address = InetAddress.getByName(hostName);
			String ip = address.getHostAddress();
			
			JOptionPane.showMessageDialog(null, "Host Name: " + hostName + "\nIP: " + ip);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
