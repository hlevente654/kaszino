package asztalok;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import alapPackage.javaSqlConnection;

public class newPlayer {
	public newPlayer() {
		
	}

	public void add( String Vezeték_Név, String Kereszt_Név,String Email, String LogIn, String Password, String Pénze) {
		
		String sql = "INSERT INTO `jatekos` (`personal_ID`, `Vezeték_Név`, `Kereszt_Név`, `Email`, `LogIn`, `Password`, `Pénze`) VALUES (NULL,?,?,?,?,?,?)";
		try(Connection conn = javaSqlConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, Vezeték_Név);
			stmt.setString(2, Kereszt_Név);
			stmt.setString(3, Email);
			stmt.setString(4, LogIn);
			stmt.setString(5, Password);
			stmt.setString(6, Pénze);
			stmt.execute();
			
		} catch (Exception e) {
			
		}
	}
}
