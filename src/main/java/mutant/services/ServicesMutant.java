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

@RestController
public class ServicesMutant {
	
	@PostMapping("/mutant")
	public ResponseEntity<String> isMutant(@RequestBody DNA dna) throws SQLException{
		
		MutantsDB db = new MutantsDB();
		
		Controller mutants = new Controller();

		boolean mutant = mutants.isMutant(dna.getDna());
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
	
	@RequestMapping("/stats")
	public CountDna statsMutants() {
		MutantsDB db = new MutantsDB();
		int mutants = db.countMutant();
		int humans = db.countHumans();
		double ratio = 0;
		if(humans == 0) {
			ratio = mutants  ;
		} else {
			ratio = mutants/humans;			
		}
		return new CountDna(mutants, humans , ratio);
	}

}
