package mutant.services;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mutant.controller.Controller;
import mutant.controller.DNA;
import mutant.model.CountDna;
import mutant.model.MutantsDB;

/*
 * Service to receive dna and to know how many mutants and humans there are in total.
 * */
@RestController
public class ServicesMutant {
	
	/*
	 * Post method that receives a json with the dna and returns a 200 ok if it is mutant
	 * and a 403 if it is human or if it's not the requested format.
	 * */
	@PostMapping("/mutant")
	public ResponseEntity<String> isMutant(@RequestBody DNA dna) throws SQLException{
		
		MutantsDB db = new MutantsDB();
		
		Controller mutants = new Controller();

		boolean mutant = mutants.isMutant(dna.getDna());
		//calls the method that validates if the record exists
		boolean exist = db.existDna(dna);
		
		if(exist) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN +" This DNA already exist", HttpStatus.FORBIDDEN);
		} else {
			db.saveDNA(dna, mutant);
			
			if(mutant) {
				return new ResponseEntity<>(HttpStatus.OK + "", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN +" Error 403-Forbidden", HttpStatus.FORBIDDEN);
			}
		}
		
	}
	
	//Get method to return how many humans and mutants there are
	@RequestMapping("/stats")
	public CountDna statsMutants() {
		MutantsDB db = new MutantsDB();
		int mutants = db.countMutant();
		int humans = db.countHumans();
		double ratio = 0;
		if(humans == 0) {
			ratio = mutants;
		} else {
			ratio = mutants/humans;			
		}
		return new CountDna(mutants, humans , ratio);
	}

}
