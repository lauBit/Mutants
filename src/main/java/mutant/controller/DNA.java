package mutant.controller;

public class DNA {
	
	private String[] dna;
	private Integer mutant;

	public DNA() {
		this.dna = null;
	}


	public DNA(String[] dna) {
		this.dna = dna;
	}

	public Integer getMutant() {
		return mutant;
	}


	public void setMutant(Integer mutant) {
		this.mutant = mutant;
	}


	public String[] getDna() {
		return dna;
	}


	public void setDna(String[] dna) {
		this.dna = dna;
	}


}
