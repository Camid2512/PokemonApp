package co.edu.unbosque.pokemonapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.pokemonapp.model.Pokemon;
import co.edu.unbosque.pokemonapp.service.PokemonService;

@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	private PokemonService pokeServ;

	public PokemonController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser() {
		try {

			pokeServ.createAllPokemon();

			return new ResponseEntity<>("Pokemon data stored", HttpStatus.CREATED);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("ERROR Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/generateteam")
	public ResponseEntity<String> generateTeam() {

		try {
			Pokemon[] teamMember = pokeServ.getRandomTeam();

			String member1 = teamMember[0].getName();
			String member2 = teamMember[1].getName();
			String member3 = teamMember[2].getName();
			String member4 = teamMember[3].getName();
			String member5 = teamMember[4].getName();
			String member6 = teamMember[5].getName();

			System.out.println("Se ha generado el team:\n" + "Miembro 1: " + member1 + "\n" + "Miembro 2: " + member2
					+ "\n" + "Miembro 3: " + member3 + "\n" + "Miembro 4: " + member4 + "\n" + "Miembro 5: " + member5
					+ "\n" + "Miembro 6: " + member6 + "\n");
			return new ResponseEntity<>("Se ha generado el team:\n" + "Miembro 1: " + member1 + "\n" + "Miembro 2: "
					+ member2 + "\n" + "Miembro 3: " + member3 + "\n" + "Miembro 4: " + member4 + "\n" + "Miembro 5: "
					+ member5 + "\n" + "Miembro 6: " + member6 + "\n", HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<>("ERROR Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getpokemonpokeid")
	ResponseEntity<Pokemon> getPokePokeId(@RequestParam int pokeId) {

		return new ResponseEntity<>(pokeServ.getPokeInfo(pokeId), HttpStatus.ACCEPTED);

	}

}
