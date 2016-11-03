package Dao;

import java.sql.SQLException;

public class RegistroDao extends BaseDatos {

	public RegistroDao() {

	}
	
	public int generaId() {
		
		int x = 0;
		conectar();

		try {
			rs = st.executeQuery(
					"select * from registro where num_reg = (select max(num_reg) from registro);");
			
			while (rs.next()) 
				x = Integer.parseInt(rs.getString(1));

		} catch (Exception e1) {
			System.out.println("Error al generar un ID");
			return 1;
		}finally{
			desconectar();
		}
		
		return x+1;
	}

	public void guardarRegistro(int id, String cedula, String nombre, String apellido,
			String acomp, String fingreso, String fsalida) {
		
		String sql = "insert into registro(num_reg,cedula,nombre,"
				+ "apellido,num_acomp,fecha_i_reg,fecha_f_reg) values("
				+id+","+cedula+",'"+nombre+"','"+apellido+"',"+acomp+",'"
				+fingreso+"','"+fsalida+"');";

		conectar();
		try {
			
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al guardar registro de clientes");
			e.printStackTrace();
		}finally{
			desconectar();
		}
	}

	public String consultarRegistros(String numReg, String cedula, String nombre,
			String apellido, String acomp, String fingreso, String fsalida) {
		
		String html = "<table width='80'>"
				
				+"<th size='5' disabled='disabled'>Registro#</th>"
				+"<th size='10' disabled='disabled'>Cedula</th>"
				+"<th size='25' disabled='disabled'>Nombre</th>"
				+"<th size='9' disabled='disabled'>Acompanantes</th>"
				+"<th size='9' disabled='disabled'>Fecha Ingreso</th>"
				+"<th size='9' disabled='disabled'>Fechas Salida</th>";
				
		String sql = "select * from registro where ";
		
		if(!"".equals(numReg) && null!=numReg) sql += "num_reg like '%"+numReg+"%' and ";
		if(!"".equals(cedula) && null!=cedula) sql += "cedula like '%"+cedula+"%' and ";
		if(!"".equals(nombre) && null!=nombre) sql += "nombre like '%"+nombre+"%' and ";
		if(!"".equals(apellido) && null!=apellido) sql += "apellido like '%"+apellido+"%' and ";
		if(!"".equals(acomp) && null!=acomp) sql += "num_acomp like '%"+acomp+"%' and ";
		if(!"".equals(fingreso) && null!=fingreso) sql += "fecha_i_reg = '"+fingreso+"' and ";
		if(!"".equals(fsalida) && null!=fsalida) sql += "fecha_f_reg = '"+fsalida+"' and ";
		
		sql +=" 1=1";
		conectar();
		
		try {
		
			rs = st.executeQuery(sql);
			while(rs.next()){
				html += "<tr>";
				html += "<td><input type='text' disabled='disabled' size='6' value='"+rs.getString(1)+"'></input></td>";
				html += "<td><input type='text' disabled='disabled' size='11' value='"+rs.getString(4)+"'></input></td>";
				html += "<td><input type='text' disabled='disabled' size='30' value='"+rs.getString(2)+" "+rs.getString(3)+"'></input></td>";
				html += "<td><input type='text' disabled='disabled' size='10' value='"+rs.getString(5)+"'></input></td>";
				html += "<td><input type='text' disabled='disabled' size='11' value='"+rs.getString(7)+"'></input></td>";
				html += "<td><input type='text' disabled='disabled' size='11' value='"+rs.getString(6)+"'></input></td>";
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

	public String consultaRegistros() {
		String sql = "select * from registro where fecha_f_reg > GETDATE()-1;";
		//String sql = "select * from registro;";
		String html = "<option value='0'>Seleccione</option>";
		
		conectar();
		
		try {
		
			rs = st.executeQuery(sql);
			while(rs.next())
				html += "<option value='"+rs.getString(1)+"'>"+
						rs.getString(1)+" - "+rs.getString(2)+" "+rs.getString(3)+"</option>";

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			desconectar();
		}
		
		return html;
	}

}
