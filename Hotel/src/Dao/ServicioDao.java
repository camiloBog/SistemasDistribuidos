package Dao;

import java.sql.SQLException;

public class ServicioDao extends BaseDatos{

	public ServicioDao() {
		
	}

	public int generaId() {

		int x = 0;
		conectar();

		try {
			rs = st.executeQuery(
					"select * from servicio where cod_serv = (select max(cod_serv) from servicio);");
			
			while (rs.next()) 
				x = Integer.parseInt(rs.getString(1));

		} catch (Exception e1) {
			System.out.println("Error al generar un ID, Servicio");
			return 1;
		}finally{
			desconectar();
		}
		
		return x+1;
	}

	public String grabarServicio(int id, String num_reg, String id_clas,
			String nom_serv, String unidad_medida, String val_serv) {
		
		String sql = "insert into servicio(cod_serv,nom_serv,val_serv,unidad_medida,id_clas,num_reg) "
				+ "values("+id+",'"+nom_serv+"',"+val_serv+",'"+unidad_medida+"',"+id_clas+","+num_reg+");";
	
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
	
	public String consultaServicio(String cod_serv, String num_reg,
			String id_clas, String nom_serv, String unidad_medida,
			String val_serv){
		
		String html = "<table width='80'>"
				
				+"<th size='4' disabled='disabled'>Registro#</th>"
				+"<th size='20' disabled='disabled'>Nombre</th>"
				+"<th size='4' disabled='disabled'>Servicio#</th>"
				+"<th size='40' disabled='disabled'>Descripcion</th>"
				+"<th size='7' disabled='disabled'>Valor $</th>";
				
				
		String sql = "select s.cod_serv, r.nombre, r.apellido, c.id_clas, "+
			"c.nom_clas, s.nom_serv, s.val_serv "+
			"from servicio s "+
			"inner join clasificacion c on c.id_clas = s.id_clas "+
			"inner join registro r on r.num_reg = s.num_reg where ";
			
		if(!"".equals(cod_serv) && null!=cod_serv) sql += "s.cod_serv ="+cod_serv+" and ";
		if(!"".equals(num_reg) && null!=num_reg && !"0".equals(num_reg)) sql += "s.num_reg ="+num_reg+" and ";
		if(!"".equals(id_clas) && null!=id_clas && !"0".equals(id_clas)) sql += "s.id_clas="+id_clas+" and ";
		if(!"".equals(nom_serv) && null!=nom_serv) sql += "s.nom_serv like '%"+nom_serv+"%' and ";
		if(!"".equals(unidad_medida) && null!=unidad_medida) sql += "s.unidad_medida like '%"+unidad_medida+"%' and ";
		if(!"".equals(val_serv) && null!=val_serv) sql += "s.val_serv="+val_serv+" and ";

		sql +=" 1=1";
		conectar();
		try {
			
			int fila = 1;
			rs = st.executeQuery(sql);
			while(rs.next()){
				html += "<tr>";
				html += "<td><input id='reg"+fila+"' type='text' disabled='disabled' size='4' value='"+rs.getString(1)+"'></input></td>";
				html += "<td><input id='nom"+fila+"' type='text' disabled='disabled' size='20' value='"+rs.getString(2)+" "+rs.getString(3)+"'></input></td>";
				html += "<td><input id='ser"+fila+"' type='text' disabled='disabled' size='4' value='"+rs.getString(4)+"'></input></td>";
				html += "<td><input id='des"+fila+"' type='text' disabled='disabled' size='40' value='"+rs.getString(5)+" - "+rs.getString(6)+"'></input></td>";
				html += "<td><input id='val"+fila+"' type='text' disabled='disabled' size='7' value='"+rs.getString(7)+"'></input></td>";
				
				html += "<td><input id='mod"+fila+"' type='button' value='Modificar' id='modi' onclick='modificar("+fila+");'></input></td>";
				html += "<td><input id='eli"+fila+"' type='button' value='Eliminar' id='elim' onclick='eliminar("+fila+");'></input></td>";
				
				html += "</tr>";
				fila++;
			}
			html += "</table>";

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			desconectar();
		}
		return html;
	}

	public String eliminaServicio(String cod_serv) {

		String sql = "delete from servicio where cod_serv="+cod_serv+";";
		conectar();
		
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al eliminaServicio");
			e.printStackTrace();
			return "HA OCURRIDO UN ERROR!!!";
		}finally{
			desconectar();
		}
		
		return "OK";
	}

	public String modificaServicio(String cod_serv, String nom_serv,
			String val_serv) {

		String sql = "update servicio set nom_serv='"+nom_serv+
				"', val_serv="+val_serv+" where cod_serv="+cod_serv+";";
		conectar();
		System.out.println("SQL == "+sql);
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al modificaServicio");
			e.printStackTrace();
			return "HA OCURRIDO UN ERROR!!!";
		}finally{
			desconectar();
		}
		
		return "OK";
		
	}

}
