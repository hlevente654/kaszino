package asztalok;

import java.sql.ResultSet;
import java.sql.SQLException;

public class jatekos {

	public static void getJatekos(ResultSet rs) throws SQLException {
		
		while(rs.next()) {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append(rs.getInt("personal_ID")+" ");	
			System.out.println(buffer.toString());
		}
	}
}
