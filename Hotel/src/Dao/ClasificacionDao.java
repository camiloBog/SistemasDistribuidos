package Dao;

import java.sql.SQLException;

public class ClasificacionDao extends BaseDatos{

	public ClasificacionDao() {

	}
	
	public int generaId() {
		
		int x = 0;
		conectar();

		try {
			rs = st.executeQuery(
					"select * from clasificacion where id_clas = (select max(id_clas) from clasificacion);");
			
			while (rs.next()) 
				x = Integer.parseInt(rs.getString(1));

		} catch (Exception e1) {
			System.out.println("Error al generar un ID, clasificacion");
			return 1;
		}finally{
			desconectar();
		}
		
		return x+1;
	}

	public String grabarClasificacion(int id, String nomServ) {
					
		String sql = "insert into clasificacion (id_clas, nom_clas)"
				+ "values("+id+",'"+nomServ+"');";

		conectar();
		try {
			
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al guardar clasificacion");
			e.printStackTrace();
			return "HA OCURRIDO UN ERROR!!!";
		}finally{
			desconectar();
		}
		
		return ""+id;
	}

	public String buscarClasificaciones() {
		
		String sql = "select * from clasificacion;";
		String html = "<option value='0'>Seleccione</option>";
		
		conectar();
		
		try {
		
			rs = st.executeQuery(sql);
			while(rs.next())
				html += "<option value='"+rs.getString(1)+"'>"+
						rs.getString(1)+" - "+rs.getString(2)+"</option>";

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			desconectar();
		}
		
		return html;
	}

	public String buscarClasificacion(String numReg, String nomServ) {
		
		String html = "<table width='80'>"
				+"<th size='6' disabled='disabled'>Registro#</th>"
				+"<th size='20' disabled='disabled'>Servicio</th>";
				
				
		String sql = "select * from clasificacion where ";
		
		if(!"".equals(numReg) && null!=numReg) sql += "id_clas like '%"+numReg+"%' and ";
		if(!"".equals(nomServ) && null!=nomServ) sql += "nom_clas like '%"+nomServ+"%' and ";
		
		sql +=" 1=1";
		conectar();
		
		try {
		
			rs = st.executeQuery(sql);
			while(rs.next()){
				html += "<tr>";
				html += "<td><input type='text' disabled='disabled' size='5' value='"+rs.getString(1)+"'></input></td>";
				html += "<td><input type='text' disabled='disabled' size='20' value='"+rs.getString(2)+"'></input></td>";
				html += "</tr>";
			}
			html += "</table>";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			desconectar();
		}
		return html;
	}

}
