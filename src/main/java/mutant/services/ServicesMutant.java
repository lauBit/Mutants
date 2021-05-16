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
		
		// DNADataBase db = new DNADataBase();
		MutantsDB db = new MutantsDB();
		
		Controller mutants = new Controller();

		boolean mutant = mutants.isMutant(dna.getDna());
		db.saveDNA(dna, mutant);
		
		if(mutant) {
			return new ResponseEntity<>(HttpStatus.OK + "", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN +" Error 403-Forbidden", HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping("/stats")
	public CountDna statsMutants() {
		MutantsDB db = new MutantsDB();
		int mutants = db.countMutant();
		int humans = db.countHumans();
		// double ratio = mutants/humans;
		return new CountDna(mutants, humans , 1);
	}

}
