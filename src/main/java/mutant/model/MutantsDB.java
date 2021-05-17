package mutant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import mutant.controller.DNA;
/*
 * Class to perform queries and database insertion
 * */

public class MutantsDB {
	
	PreparedStatement ps;
	ConnectionDB db = new ConnectionDB();
	Connection con;
	
	// Query to determine if the record to be consult, already exists in the database 
	public boolean existDna(DNA dna) {
		boolean exist = false;
		try {
			con = db.conectar();
			String dnaConcat = String.join(" ", dna.getDna()).replace(" ", "").toUpperCase();

			String sql = "SELECT DNA FROM DNA WHERE DNA = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dnaConcat);
			
			int rows = ps.executeUpdate();
			
			if(rows>0) {
				exist = true;
			}
			
		} catch(Exception e) {
			
		}	
		
		return exist;
	}
	
	//Query to insert the record to the data base
	public DNA saveDNA(DNA dna, boolean mutant) {
		try {
			con = db.conectar();
			// converts the dna object to a string (this is the data type in the database)	
			String s = String.join(" ", dna.getDna()).replace(" ", "").toUpperCase();
			
			int isMutant = 0;
			if(mutant) {
				isMutant = 1;
			}
			
			String sql = "INSERT INTO DNA (dna, mutant) VALUES (?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, isMutant);
			
			int rows = ps.executeUpdate();
			
			if (rows > 0 ) {
				System.out.println("Row inserted");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return dna;	
	}
	
	/*Query to count how many mutants are registered, 
	if the dna is mutant it will be stored with a 1 in the database.*/
	public int countMutant() {
		int mutants = 0;
		try {
			con = db.conectar();			
			Statement stmt = con.createStatement();
			ResultSet mutant = stmt.executeQuery("SELECT COUNT(MUTANT) FROM DNA WHERE MUTANT=1");
			
			while (mutant.next()) {
				String mutantss = mutant.getString("COUNT(MUTANT)");
				mutants = Integer.parseInt(mutantss);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	return mutants; 
	}
	
	/*Query to count how many humans are registered, 
	if the dna is mutant it will be stored with a 0 in the database.*/
	public int countHumans() {
		int mutants = 0;
		try {
			con = db.conectar();

			Statement stmt = con.createStatement();
			ResultSet mutant = stmt.executeQuery("SELECT COUNT(MUTANT) FROM DNA WHERE MUTANT=0");
			
			while (mutant.next()) {
				String mutantss = mutant.getString("COUNT(MUTANT)");
				mutants = Integer.parseInt(mutantss);
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	return mutants; 
	}

}
