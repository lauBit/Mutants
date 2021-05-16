package mutant.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
public static boolean mutant = false;
	
	Connection con;
	String dbURL = "jdbc:oracle:thin:@database-dna.cnoexmoiktwb.us-east-2.rds.amazonaws.com:1521:DATABASE";
	String user = "MAGNETO";
	String pass = "ADMIN123";
	
	public Connection conectar() {
		try {
			con = DriverManager.getConnection(dbURL, user, pass);
			System.out.println("Connected");
		} catch (SQLException e) {
			System.out.println("Opps.. Error");
			System.out.println(e);
			e.printStackTrace();
		}
		
		return con;
	}

}
