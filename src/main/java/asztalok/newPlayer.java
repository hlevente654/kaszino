package asztalok;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import alapPackage.javaSqlConnection;

public class newPlayer {
	public newPlayer() {
		
	}

	public void add( String Vezet�k_N�v, String Kereszt_N�v,String Email, String LogIn, String Password, String P�nze) {
		
		String sql = "INSERT INTO `jatekos` (`personal_ID`, `Vezet�k_N�v`, `Kereszt_N�v`, `Email`, `LogIn`, `Password`, `P�nze`) VALUES (NULL,?,?,?,?,?,?)";
		try(Connection conn = javaSqlConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, Vezet�k_N�v);
			stmt.setString(2, Kereszt_N�v);
			stmt.setString(3, Email);
			stmt.setString(4, LogIn);
			stmt.setString(5, Password);
			stmt.setString(6, P�nze);
			stmt.execute();
			
		} catch (Exception e) {
			
		}
	}
}
