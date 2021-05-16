package mutant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import mutant.controller.DNA;
public class MutantsDB {
	
	PreparedStatement ps;
	ConnectionDB db = new ConnectionDB();
	Connection con;
	
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
	
	public DNA saveDNA(DNA dna, boolean mutant) {
		try {
			con = db.conectar();
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
	
	public int countMutant() {
		int mutants = 0;
		try {
			con = db.conectar();
			// String sql = "SELECT COUNT(MUTANT) FROM DNA WHERE MUTANT=1";
			//ps = con.prepareStatement(sql);
			
			System.out.println("ya se conect�");
			Statement stmt = con.createStatement();
			ResultSet mutant = stmt.executeQuery("SELECT COUNT(MUTANT) FROM DNA WHERE MUTANT=1");
			System.out.println("seconddd");
			
			while (mutant.next()) {
				System.out.println("entr� al while");
				String mutantss = mutant.getString("COUNT(MUTANT)");
				mutants = Integer.parseInt(mutantss);
				System.out.println(mutantss + "\n");
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	return mutants; 
	}
	
	public int countHumans() {
		int mutants = 0;
		try {
			con = db.conectar();
			// String sql = "SELECT COUNT(MUTANT) FROM DNA WHERE MUTANT=1";
			//ps = con.prepareStatement(sql);
			
			System.out.println("ya se conect�");
			Statement stmt = con.createStatement();
			ResultSet mutant = stmt.executeQuery("SELECT COUNT(MUTANT) FROM DNA WHERE MUTANT=0");
			System.out.println("seconddd");
			
			while (mutant.next()) {
				System.out.println("entr� al while");
				String mutantss = mutant.getString("COUNT(MUTANT)");
				mutants = Integer.parseInt(mutantss);
				System.out.println(mutantss + "\n");
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	return mutants; 
	}

}
