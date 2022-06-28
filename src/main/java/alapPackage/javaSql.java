package alapPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import asztalok.jatekos;
import asztalok.newPlayer;


public class javaSql {
		
	public static void sql() throws SQLException {
		
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
			
			while(rs.next()) {
				//System.out.println(rs.getString("personal_ID")+" "+rs.getString("Vezeték_Név"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getId(String nev) {
		String id="";
		
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
			
			id = "";
			while(rs.next()) {
				if(rs.getString("LogIn").equals(nev)) {
					id = rs.getString("personal_ID");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static String getGameLog(String id) {
		String adat = "";
		
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		
		String read = "SELECT *FROM gamelog";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
			adat = "";
			while(rs.next()) {
				//System.out.println(rs.getString("personal_ID")+" "+rs.getString("Vezeték_Név"));
				if(rs.getString("jatekos_id").equals(id)) {
					if(rs.getString("nyert").equals("1")) {
						adat = adat + "nyertél"+" "+ rs.getString("osszegValtozas")+"Ft-t;"+" ";
					}else {
						adat = adat + "vesztettél"+" "+ rs.getString("osszegValtozas")+"Ft-t;"+" ";
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adat;
	}
	public static void gameLog(String nev, String tet) {
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		String read = "SELECT *FROM jatekos";
		String insert = "INSERT INTO `gamelog` (`jatekos_id`, `nyert`, `osszegValtozas`) VALUES (?, ?, ?) ";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
				PreparedStatement stmt = conn.prepareStatement(insert);
			){
			rs = pr.executeQuery();
			String id = "";
			while(rs.next()) {
				if(rs.getString("LogIn").equals(nev)) {
					id = rs.getString("personal_ID");
				}
			}
			int nyert = 1;
			if(Integer.parseInt(tet)<0) {
				nyert = 0;
			}
			stmt.setString(1, id);
			stmt.setString(2, Integer.toString(nyert));
			stmt.setString(3, tet);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setMoney(String tet,String nev) {
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		
		String sql = "UPDATE `jatekos` SET `Pénze` = ? WHERE jatekos.LogIn LIKE ?";
		
		try(Connection conn = javaSqlConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				){
			
			stmt.setString(1, tet);
			stmt.setString(2, nev);
			stmt.execute();
			
		} catch (Exception e) {
		}
	}
	
	public static int getMoney(String nev) {
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		int eredmeny = 0;
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("LogIn").equals(nev)) {
					eredmeny = Integer.parseInt(rs.getString("Pénze")) ;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eredmeny;
	}
	
	public static String logIn (String nev,String password) {
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		String belepesiAdatok = "";
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
			
			while(rs.next()) {
				if(nev.equals(rs.getString("LogIn"))&&password.equals(rs.getString("Password"))) {
					belepesiAdatok ="true"+" "+ rs.getString("LogIn")+" "+rs.getString("Password") ;
				}
			}
			if(belepesiAdatok=="") {
				belepesiAdatok="false";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return belepesiAdatok;
	}
	
	public static void newPlayer (String Vezeték_Név, String Kereszt_Név, String Email, String LogIn, String Password)throws SQLException{
		
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		String read = "SELECT *FROM jatekos";
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		newPlayer newplayer=new newPlayer();
		newplayer.add( Vezeték_Név, Kereszt_Név, Email, LogIn, Password, "1000000");
		
	}
	
	public static boolean jelszo_ellenor(String jelszo) throws SQLException{
		boolean jo = true;
		
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
				
				if(jelszo.contains(" ")) {
					jo = false;
				}
				if(jelszo.length()<8) {
					jo = false;
				}
				while(rs.next()) {
					if(rs.getString("Password").equals(jelszo)) {
					jo = false;
				}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jo;
	}
	
	public static boolean nev_ellenor(String nev) throws SQLException{
		boolean jo = true;
		
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
			while(rs.next()) {
				if(rs.getString("LogIn").equals(nev)) {
					jo = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jo;
	}
	
	public static boolean ker_nev_ellenor(String ker_nev) throws SQLException{
		boolean jo = true;
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jo;
	}
	
	public static boolean vez_nev_ellenor(String vez_nev) throws SQLException{
		boolean jo = true;
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jo;
	}
	public static boolean email_ellenor(String email) throws SQLException{
		
		boolean jo = true;
		PreparedStatement preparedStatement = null;
		ResultSet rs;
		String read = "SELECT *FROM jatekos";
		
		try (Connection conn = javaSqlConnection.getConnection();
				PreparedStatement pr = conn.prepareStatement(read);
			){
			rs = pr.executeQuery();
			
			if(email.contains("@")&&email.contains(".")){
				while(rs.next()) {
					if(rs.getString("Email").equals(email)) {
					jo = false;
				}
				}
			}else{
				jo = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jo;
	}
}
