package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioClientes;

@WebServlet("/Clientes")
public class Clientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Clientes() {

    }


	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opc");
		NegocioClientes cliente = new NegocioClientes();

		if (opcion.equals("registra")) cliente.registraCliente(request, response);
		if (opcion.equals("consulta")) cliente.consultaCliente(request, response);
		if (opcion.equals("consultaRegistros")) cliente.consultaRegistros(response);
		
	}
}
