package mutant;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mutant.controller.Controller;
import mutant.controller.DNA;
import mutant.model.CountDna;
import mutant.model.MutantsDB;
import mutant.services.ServicesMutant;

@SpringBootTest
class MutantApplicationTests {

	@Test
	void contextLoads() {
	}
	
private final Controller mutants = new Controller();
	
	@Test
	public void testHorizontal() {
		String[] adn = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean mutant = mutants.horizontal(adn);
		boolean expected = true; 
		
		assertEquals(mutant, expected);
	}
	
	@Test
	public void testVertical() {
		String[] adn = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCACAA","TCACTG"};
		boolean mutant = mutants.vertical(adn);
		boolean expected = true;
		
		assertEquals(mutant, expected);
	}
	
	@Test
	public void testOblique() {
		
		String[] adn = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCAA","TCACTG"};
		boolean mutant = mutants.oblique(adn);
		boolean expected = true;
		
		assertEquals(mutant, expected);
	}
	
	
	@Test 
	public void testSave() {	  
	  String[] adn = {"ATGCGA","CAGTGC","TTGTGT","AGGAGG"}; 
  
	  DNA dna = new DNA(adn);
	  MutantsDB mutantsDb = new MutantsDB();
	  
	  DNA bd = mutantsDb.saveDNA(dna, true); 
	  DNA expected = dna;
  
	  assertEquals(bd, expected); 
  
  }
	  
  @Test 
  public void testExist() { 
	  
	  String[] adn = {"ATGCGA","CAGTGC","TTGTGT","AGGAGG"}; 
  
	  DNA dna = new DNA(adn);
	  MutantsDB mutantsDb = new MutantsDB();
	  
	  boolean exist = mutantsDb.existDna(dna); 
	  
	  boolean expected = false;
  
	  assertEquals(exist, expected); 
  
  }
	  
	  @Test
	  public void testCountMutant() {
		  
		  MutantsDB mutantsDb = new MutantsDB();
		  CountDna expected = new CountDna(3, 2, (3/2));
		  int mutants = mutantsDb.countMutant();
		  
		  //assertEquals(mutants, expected.getCount_mutant_dna() );
	  }
	  
	  @Test
	  public void testCountHuman() {
		  
		  MutantsDB mutantsDb = new MutantsDB();
		  CountDna expected = new CountDna(3, 2, (3/2));
		  int humans = mutantsDb.countHumans();
		  
		  // assertEquals(humans, expected.getCount_human_dna());
	  }
	  
	  @Test 
	  public void testRatio() {
		  
		  MutantsDB mutantsDb = new MutantsDB();
		  CountDna countDna = new CountDna(3,5,1.66);
		  
		  int humans = mutantsDb.countHumans();
		  int mutants = mutantsDb.countMutant();
		  double ratio; 
		  
		  if( humans == 0) {
			  ratio = mutants;
		  } else {
			  ratio = mutants/humans;
		  }
		  
		  assertEquals(countDna.getRatio(), ratio, 10);
	  }
	  
	@Test
	public void testServiceMutant() throws SQLException {
		String[] adn = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		DNA dna = new DNA();
		dna.setDna(adn);
		ServicesMutant servicesMutant = new ServicesMutant();
		
		ResponseEntity<String> actual;
		actual = servicesMutant.isMutant(dna);
		
		ResponseEntity<String> expected;
		expected = new ResponseEntity<>(HttpStatus.OK + "", HttpStatus.OK);
		
		assertEquals(expected, actual);
	}

}
