package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioServicios;

@WebServlet("/Servicios")
public class Servicios extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servicios() {

    }

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String opcion = request.getParameter("opc");
		NegocioServicios servicio = new NegocioServicios();

		if (opcion.equals("registraConsumo")) servicio.registraConsumo(request, response);
		if (opcion.equals("consultaConsumo")) servicio.consultaConsumo(request, response);
		
		if (opcion.equals("registraServicio")) servicio.registraServicio(request, response);
		if (opcion.equals("consultaServicio")) servicio.consultaServicio(request, response);
		if (opcion.equals("modificaServicio")) servicio.modificaServicio(request, response);
		if (opcion.equals("eliminaServicio")) servicio.eliminaServicio(request, response);
		
		if (opcion.equals("consultaServicios")) servicio.consultaServicios(response);
		
	}

}