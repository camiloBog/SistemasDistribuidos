package negocio;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ClasificacionDao;
import Dao.ServicioDao;

public class NegocioServicios {

	/*
	 * Consulta registros ofrecidos, y devuelve un select html
	 */
	public void consultaServicios(HttpServletResponse response
			) throws IOException {
		
		ClasificacionDao clasificacion = new ClasificacionDao();
		String respuesta = clasificacion.buscarClasificaciones();
		response.setContentType("text/html");
		response.getWriter().write(respuesta);
	}

	public void registraConsumo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String num_reg = request.getParameter("num_reg");
		String id_clas = request.getParameter("id_clas");
		String nom_serv = request.getParameter("nom_serv");
		String unidad_medida = request.getParameter("unidad_medida");
		String val_serv = request.getParameter("val_serv");
		
		ServicioDao servicio = new ServicioDao();
		int id = servicio.generaId(); 
		String respuesta = servicio.grabarServicio(id, num_reg, 
				id_clas, nom_serv, unidad_medida, val_serv);
		
		response.setContentType("text/text");
		response.getWriter().write(respuesta);
	}

	public void consultaConsumo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String cod_serv = request.getParameter("cod_serv");
		String num_reg = request.getParameter("num_reg");
		String id_clas = request.getParameter("id_clas");
		String nom_serv = request.getParameter("nom_serv");
		String unidad_medida = request.getParameter("unidad_medida");
		String val_serv = request.getParameter("val_serv");
		
		ServicioDao servicio = new ServicioDao();
		String respuesta = servicio.consultaServicio(cod_serv, num_reg, 
				id_clas, nom_serv, unidad_medida, val_serv);

		response.setContentType("text/html");
		response.getWriter().write(respuesta);
		
	}

	public void registraServicio(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		String nomServ = request.getParameter("nomServ");
		
		ClasificacionDao clasificacion = new ClasificacionDao();
		int id = clasificacion.generaId(); 
		String respuesta = clasificacion.grabarClasificacion(id, nomServ);
		
		response.setContentType("text/text");
		response.getWriter().write(respuesta);
	}

	public void consultaServicio(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		String numReg = ""+request.getParameter("numReg");
		String nomServ = ""+request.getParameter("nomServ");
		
		ClasificacionDao clasificacion = new ClasificacionDao();
		String respuesta = clasificacion.buscarClasificacion(numReg, nomServ);
		
		response.setContentType("text/html");
		response.getWriter().write(respuesta);
	}

	public void modificaServicio(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String cod_serv = request.getParameter("cod_serv");
		String nom_serv = request.getParameter("nom_serv");
		String val_serv = request.getParameter("val_serv");
		
		ServicioDao servicio = new ServicioDao();
		String respuesta = servicio.modificaServicio(cod_serv, nom_serv, val_serv);

		response.setContentType("text/text");
		response.getWriter().write(respuesta);
		
	}

	public void eliminaServicio(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String cod_serv = request.getParameter("cod_serv");
		
		ServicioDao servicio = new ServicioDao();
		String respuesta = servicio.eliminaServicio(cod_serv);

		response.setContentType("text/text");
		response.getWriter().write(respuesta);
	}

}
