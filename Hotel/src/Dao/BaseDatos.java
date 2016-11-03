package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDatos{
	
	private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=Hotel";
	private final String user = "sa";
	private final String pass = "camiloandres";

	private Connection ct;
	protected Statement st;
	protected PreparedStatement pst;
	protected ResultSet rs;

	public BaseDatos() {

	}
	
	public void conectar() {
		try {
			
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, pass);
			st = ct.createStatement();
			
			System.out.println("Conexion Exitosa!!");
		} catch (Exception e) {
			System.out.println("Error al conectar a la base de datos");
			
			e.printStackTrace();
		}		
	}
	
	public void desconectar() {
		try {
			rs.close();
			pst.close();
			st.close();
			ct.close();
		} catch (Exception e) {
			System.out.println("Finaliza Conexion Exitosamente!");
		}
	}

}
