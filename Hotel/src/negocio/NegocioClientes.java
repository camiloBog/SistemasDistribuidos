package negocio;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.RegistroDao;

public class NegocioClientes {

	public NegocioClientes() {

	}

	public void registraCliente(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String cedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String acomp = request.getParameter("acomp");
		String fingreso = request.getParameter("fingreso");
		String fsalida = request.getParameter("fsalida");
		
		RegistroDao registro = new RegistroDao();
		int id = registro.generaId();
		registro.guardarRegistro(id, cedula, nombre, apellido, acomp, fingreso, fsalida);
		
		response.setContentType("text/text");
		response.getWriter().write(id+"");

	}

	public void consultaCliente(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String numReg = ""+request.getParameter("numReg");
		String cedula = ""+request.getParameter("cedula");
		String nombre = ""+request.getParameter("nombre");
		String apellido = ""+request.getParameter("apellido");
		String acomp = ""+request.getParameter("acomp");
		String fingreso = ""+request.getParameter("fingreso");
		String fsalida = ""+request.getParameter("fsalida");
		
		RegistroDao registro = new RegistroDao();
		String html = registro.consultarRegistros(
				numReg, cedula, nombre, apellido, acomp, fingreso, fsalida);
		
		response.setContentType("text/html");
		response.getWriter().write(html);
	}

	public void consultaRegistros(HttpServletResponse response
			) throws IOException {
		
		RegistroDao registro = new RegistroDao();
		String html = registro.consultaRegistros();
		
		response.setContentType("text/html");
		response.getWriter().write(html);
		
	}

}
