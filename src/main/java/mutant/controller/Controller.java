package mutant.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Class to perform the calculations to determine 
 * if the dna belongs to a mutant or a human.
 * */

public class Controller {
	
public boolean mutant = false;
	
	public boolean isMutant(String[] dna) {
		
		// regular expression is used to only accept the letters A,T,C,G
		String s = String.join(" ", dna).replace(" ", "").toUpperCase();
		Pattern pat = Pattern.compile("(A|C|G|T)+");
		Matcher mat = pat.matcher(s);
		
		// Returns true if by any of the methods (horizontal, vertical or oblique) the dna is mutant.
		if( mat.matches() ) {
			mutant = horizontal(dna);
			
			if(!mutant) {
				mutant = vertical(dna);
			}
			
			if (!mutant) {
				mutant = oblique(dna);
			}
			
		} else {
			return false;
		}
		
		return mutant;
	}
	
	public boolean horizontal(String[] dna) {
		int cont =0;
		for(int i=0; i<dna.length; i++) { //Row
			for(int j=0; j<dna[i].length(); j++) { //Column
			  if(j+1 < dna[i].length()-1) {
				  String nextColumn = dna[i];
				  if (nextColumn.charAt(j) == nextColumn.charAt(j+1)) {
					 cont++;
					 if(cont == 3) {
						 System.out.println("Mutante horizontal");
						 return true;
					 }
				  } else {
					  cont=0;
				  }
			  }
			}
		}
		
		return false;
	}
	
	public boolean vertical(String[] dna) {
		int cont =0;
		for(int i=0; i<dna.length; i++) {
			for(int j=0; j<dna[i].length(); j++) {
				if(j+1 < dna[i].length()-1) {
					String column = dna[j];
					String nextColumn = dna[j+1];
					
					if (column.charAt(i) == nextColumn.charAt(i)) {
						 cont++;
						 if(cont == 3) {
							 System.out.println("Mutante vertical");
							 return true;
						 }
					  } else {
						  cont=0;
					  }
				}
			}
		}
		return false;
	}
	
	public boolean oblique(String[] dna) {
		int cont =0;
		for(int i=0; i<dna.length; i++) {
			for(int j=0; j<dna[i].length(); j++) {
				if(i+1 < dna.length-1 && j+1 < dna[i].length()-1) {
					String column = dna[j];
					String nextColumn = dna[j+1];
					  if (column.charAt(j) == nextColumn.charAt(j+1)) {
						 cont++;
						 if(cont == 3) {
							 System.out.println("Mutante diagonal");
							 return true;
						 }
					  } else {
						  cont=0;
					  }
				  }
			}
		}
	
		return false;
	}

}
