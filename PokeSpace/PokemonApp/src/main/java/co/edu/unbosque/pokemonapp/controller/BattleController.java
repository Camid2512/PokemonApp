package co.edu.unbosque.pokemonapp.controller;

import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.pokemonapp.model.Move;
import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.service.BattleService;

@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/battle")
public class BattleController {

	@Autowired
	private BattleService batServ;

	public BattleController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/fainted")
	ResponseEntity<Boolean> allFainted(Pokemon[] pokemonTeam) {

		boolean allFaint = batServ.allFainted(pokemonTeam);

		if (allFaint == true) {
			return new ResponseEntity<>(allFaint, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(allFaint, HttpStatus.PROCESSING);

		}
	}

	@GetMapping("/calculatedamage")
	ResponseEntity<String[]> calculateDmg(Pokemon attackerPokemon, Move attackerMove, Pokemon defenderPokemon) {

		String[] dmgProp = batServ.calculateAttackDmg(attackerPokemon, attackerMove, defenderPokemon).split(",");

		if (dmgProp != null) {

			return new ResponseEntity<>(dmgProp, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(dmgProp, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/firstattacker")
	ResponseEntity<Pokemon> firstAttacker(Pokemon pokemonA, Pokemon pokemonB) {
		Pokemon attacker = batServ.firstAttacker(pokemonA, pokemonB);
		if (attacker != null) {
			return new ResponseEntity<>(attacker, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(attacker, HttpStatus.NO_CONTENT);
		}
	}

}
